package life.joker.community.service;

import life.joker.community.dto.CommentDTO;
import life.joker.community.enums.CommentTypeEnum;
import life.joker.community.enums.NotificationStatusEnum;
import life.joker.community.enums.NotificationTypeEnum;
import life.joker.community.exception.CustomizeErrorCode;
import life.joker.community.exception.CustomizeException;
import life.joker.community.mapper.*;
import life.joker.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author joker
 * @date 2023/03/03 17:00
 **/
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private CommentExtMapper commentExtMapper;
    @Autowired
    private NotificationMapper notificationMapper;

    static final Integer DEFAULT_COMMENT_COUNT_STEP = 1;

    @Transactional(rollbackFor = Exception.class)
    public void insert(Comment comment, Login commentator) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if (comment.getType().equals(CommentTypeEnum.COMMENT.getType())) {
            //回复评论,获得一级回复
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
            }
            //二级回复也需要给主问题增加评论数
            Question question = questionMapper.selectByPrimaryKey(dbComment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insertSelective(comment);
            question.setCommentCount(DEFAULT_COMMENT_COUNT_STEP);
            questionExtMapper.incCommentCount(question);
            //二级回复给一级回复增加评论数
            dbComment.setCommentCount(DEFAULT_COMMENT_COUNT_STEP);
            commentExtMapper.incCommentCount(dbComment);
            //创建通知(不通知问题创建者吧，避免过多通知)
            createNotify(comment, dbComment.getCommentator(), commentator.getName(), question.getTitle(), NotificationTypeEnum.REPLY_COMMENT.getType());
        } else {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insertSelective(comment);
            question.setCommentCount(DEFAULT_COMMENT_COUNT_STEP);
            questionExtMapper.incCommentCount(question);
            //创建通知
            createNotify(comment, question.getCreator(), commentator.getName(), question.getTitle(), NotificationTypeEnum.REPLY_QUESTION.getType());
        }
    }

    private void createNotify(Comment comment, Long receiver, String notifierName, String outerTitle, int type) {
        //自己回复自己的问题不应该通知
        if (receiver.equals(comment.getCommentator())) {
            return;
        }
        Notification notification = new Notification();
        notification.setGmtCreate(System.currentTimeMillis());
        notification.setType(type);
        notification.setOuterId(comment.getParentId());
        notification.setNotifier(comment.getCommentator());
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setReceiver(receiver);
        notification.setNotifierName(notifierName);
        notification.setOuterTitle(outerTitle);
        notificationMapper.insertSelective(notification);
    }

    public List<CommentDTO> listByTargetId(Long id, CommentTypeEnum type) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andParentIdEqualTo(id).andTypeEqualTo(type.getType());
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> commentList = commentMapper.selectByExample(commentExample);
        if (commentList.size() == 0) {
            return new ArrayList<>();
        }
        //获取去重的评论人
        Set<Long> commentators = commentList.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        //获取评论人并转换为Map
        LoginExample loginExample = new LoginExample();
        loginExample.createCriteria().andIdIn(commentators.stream().toList());
        List<Login> logins = loginMapper.selectByExample(loginExample);
        Map<Long, Login> loginMap = logins.stream().collect(Collectors.toMap(login -> login.getId(), login -> login));
        //转换comment为commentDTO
        List<CommentDTO> commentDTOS = commentList.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setLogin(loginMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOS;
    }
}