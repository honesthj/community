package life.joker.community.controller;

import jakarta.servlet.http.HttpServletRequest;
import life.joker.community.dto.PaginationDTO;
import life.joker.community.model.Login;
import life.joker.community.service.NotificationService;
import life.joker.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author joker
 * @date 2023/02/27 18:02
 **/
@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action, Model model, HttpServletRequest request, @RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "size", defaultValue = "5") Integer size) {
        Login login = (Login) request.getSession().getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }
        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
            PaginationDTO pagination = questionService.list(login.getId(), page, size);
            model.addAttribute("pagination", pagination);
        } else if ("replies".equals(action)) {
            PaginationDTO pagination = notificationService.list(login.getId(), page, size);
            Long unreadCount = notificationService.unreadCount(login.getId());
            model.addAttribute("pagination", pagination);
            model.addAttribute("section", "replies");
            model.addAttribute("unreadCount", unreadCount);
            model.addAttribute("sectionName", "最新回复");
        }
        return "profile";
    }
}