package life.joker.community.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import life.joker.community.dto.AccessTokenDTO;
import life.joker.community.dto.GithubUser;
import life.joker.community.model.Login;
import life.joker.community.provider.GithubProvider;
import life.joker.community.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

/**
 * @author joker
 * @date 2023/02/18 11:55
 **/

@Controller
@Slf4j
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private LoginService loginService;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect_uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        if (user != null && user.getId() != null) {
            Login login = new Login();
            String token = UUID.randomUUID().toString();
            login.setToken(token);
            login.setName(user.getName());
            login.setAccountId(String.valueOf(user.getId()));
            login.setAvatarUrl(user.getAvatarUrl());
            login.setBio(user.getBio());
            loginService.createOrUpdate(login);
            response.addCookie(new Cookie("token", token));
            return "redirect:/";
        } else {
            //登录失败，重新登录
            log.error("callback get github error,{}", user);
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logOut(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("login");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}