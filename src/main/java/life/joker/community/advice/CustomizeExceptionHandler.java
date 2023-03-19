package life.joker.community.advice;

import com.alibaba.fastjson.JSON;
import life.joker.community.dto.ResultDTO;
import life.joker.community.exception.CustomizeErrorCode;
import life.joker.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author joker
 * @date 2023/03/02 15:49
 **/
@ControllerAdvice
public class CustomizeExceptionHandler {
    static final String CONTENT_TYPE = "application/json";

    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model, HttpServletRequest request, HttpServletResponse response) {
        String contentType = request.getContentType();
        if (CONTENT_TYPE.equals(contentType)) {
            ResultDTO resultDTO = null;
            //返回 JSON
            if (e instanceof CustomizeException) {
                resultDTO = ResultDTO.errorOf((CustomizeException) e);
            } else {
                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("UTF-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ioe) {
            }
            return null;
        } else {
            if (e instanceof CustomizeException) {
                model.addAttribute("message", e.getMessage());
            } else {
                model.addAttribute("message", ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR).getMessage());
            }
            return new ModelAndView("error");
        }
    }

}