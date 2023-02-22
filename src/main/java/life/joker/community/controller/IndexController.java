package life.joker.community.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import life.joker.community.mapper.LoginMapper;
import life.joker.community.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author joker
 * @date 2023/02/16 11:24
 **/
@Controller
public class IndexController {
    @Autowired
    private LoginMapper loginMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                Login login = loginMapper.findByToken(token);
                if (login != null) {
                    request.getSession().setAttribute("login", login);
                }
                break;
            }
        }
        return "index";
    }
}