package life.joker.community.controller;


import life.joker.community.dto.NotificationDTO;
import life.joker.community.enums.NotificationTypeEnum;
import life.joker.community.model.Login;
import life.joker.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpServletRequest;

/**
 * @author joker
 * @date 2023/03/09 11:40
 **/
@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String question(@PathVariable(name = "id") Long id, HttpServletRequest request) {
        Login login = (Login) request.getSession().getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }
        NotificationDTO notificationDTO = notificationService.read(id, login);
        if (NotificationTypeEnum.REPLY_QUESTION.getType() == notificationDTO.getType() ||
                NotificationTypeEnum.REPLY_COMMENT.getType() == notificationDTO.getType()) {
            return "redirect:/question/" + notificationDTO.getQuestionId();
        }
        return "redirect:/";
    }
}