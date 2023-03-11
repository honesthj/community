package life.joker.community.service;

import com.github.pagehelper.PageHelper;
import life.joker.community.dto.NotificationDTO;
import life.joker.community.dto.PaginationDTO;
import life.joker.community.enums.NotificationStatusEnum;
import life.joker.community.enums.NotificationTypeEnum;
import life.joker.community.exception.CustomizeErrorCode;
import life.joker.community.exception.CustomizeException;
import life.joker.community.mapper.CommentMapper;
import life.joker.community.mapper.NotificationMapper;
import life.joker.community.mapper.QuestionMapper;
import life.joker.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author joker
 * @date 2023/03/08 21:14
 **/
@Service
public class NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private CommentMapper commentMapper;

    public PaginationDTO list(Long loginId, Integer page, Integer size) {
        PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO<>();
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(loginId);
        Integer totalCount = (int) notificationMapper.countByExample(notificationExample);
        Integer totalPage = totalCount / size + (totalCount % size != 0 ? 1 : 0);
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage, page);
        PageHelper.startPage(page, size);
        notificationExample.setOrderByClause("status asc,gmt_create desc");
        List<Notification> notifications = notificationMapper.selectByExample(notificationExample);
        if (notifications.size() == 0) {
            return paginationDTO;
        }
        List<NotificationDTO> notificationDTOList = new ArrayList<>();
        for (Notification notification : notifications) {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification, notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDTOList.add(notificationDTO);
        }
        paginationDTO.setData(notificationDTOList);
        return paginationDTO;
    }

    public Long unreadCount(Long loginId) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(loginId).andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        long unreadCount = notificationMapper.countByExample(notificationExample);
        return unreadCount;
    }

    public NotificationDTO read(Long id, Login login) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        if (notification == null) {
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if (!notification.getReceiver().equals(login.getId())) {
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }
        //通知更改为已读
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKeySelective(notification);
        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification, notificationDTO);
        //如果是回复评论
        if (NotificationTypeEnum.REPLY_COMMENT.getType() == notification.getType()) {
            Comment dbComment = commentMapper.selectByPrimaryKey(notification.getOuterId());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
            }
            Question question = questionMapper.selectByPrimaryKey(dbComment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            notificationDTO.setQuestionId(question.getId());
        } else {
            notificationDTO.setQuestionId(notification.getOuterId());
        }
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        return notificationDTO;
    }
}