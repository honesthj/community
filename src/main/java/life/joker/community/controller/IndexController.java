package life.joker.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author joker
 * @date 2023/02/16 11:24
 **/
@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}