package life.joker.community.controller;

import jakarta.servlet.http.HttpServletRequest;
import life.joker.community.dto.CommentCreateDTO;
import life.joker.community.dto.ResultDTO;
import life.joker.community.exception.CustomizeErrorCode;
import life.joker.community.model.Comment;
import life.joker.community.model.Login;
import life.joker.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author joker
 * @date 2023/03/03 10:24
 **/
@Controller
public class CommentController {
    static final Long defaultLikeCount = 0L;
    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request) {
        Login login = (Login) request.getSession().getAttribute("login");
        if (login == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NOT_LOGIN);
        }
        if (commentCreateDTO == null || StringUtils.isBlank(commentCreateDTO.getContent())) {
            return ResultDTO.errorOf(CustomizeErrorCode.COMMENT_IS_EMPTY);
        }
        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentator(login.getId());
        comment.setLikeCount(defaultLikeCount);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }
}