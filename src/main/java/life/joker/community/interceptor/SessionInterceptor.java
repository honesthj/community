package life.joker.community.interceptor;


import life.joker.community.mapper.LoginMapper;
import life.joker.community.model.Login;
import life.joker.community.model.LoginExample;
import life.joker.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author joker
 * @date 2023/02/28 10:31
 **/
@Service
public class SessionInterceptor implements HandlerInterceptor {
    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private NotificationService notificationService;
    @Value("${github.redirect_uri}")
    private String redirectUri;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //设置 context 级别的属性
        request.getServletContext().setAttribute("githubRedirectUri", redirectUri);
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    LoginExample loginExample = new LoginExample();
                    loginExample.createCriteria().andTokenEqualTo(token);
                    List<Login> logins = loginMapper.selectByExample(loginExample);
                    if (logins.size() != 0) {
                        request.getSession().setAttribute("login", logins.get(0));
                        Long unreadCount = notificationService.unreadCount(logins.get(0).getId());
                        request.getSession().setAttribute("unreadCount", unreadCount);
                    }
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}