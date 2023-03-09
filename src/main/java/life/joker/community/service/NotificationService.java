package life.joker.community.service;

import com.github.pagehelper.PageHelper;
import life.joker.community.dto.NotificationDTO;
import life.joker.community.dto.PaginationDTO;
import life.joker.community.enums.NotificationTypeEnum;
import life.joker.community.mapper.LoginMapper;
import life.joker.community.mapper.NotificationMapper;
import life.joker.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author joker
 * @date 2023/03/08 21:14
 **/
@Service
public class NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private LoginMapper loginMapper;

    public PaginationDTO list(Long loginId, Integer page, Integer size) {
        PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO<>();
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(loginId);
        Integer totalcount = (int) notificationMapper.countByExample(notificationExample);
        Integer totalPage = totalcount / size + (totalcount % size != 0 ? 1 : 0);
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage, page);
        PageHelper.startPage(page, size);
        notificationExample.setOrderByClause("gmt_create desc");
        List<Notification> notifications = notificationMapper.selectByExample(notificationExample);
        if (notifications.size() == 0) {
            return paginationDTO;
        }
        List<NotificationDTO> notificationDTOList = new ArrayList<>();
        for (Notification notification : notifications) {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification, notificationDTO);
            notificationDTO.setType(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDTOList.add(notificationDTO);
        }
        paginationDTO.setData(notificationDTOList);
        return paginationDTO;
    }

    public Long unreadCount(Long loginId) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(loginId).andStatusEqualTo(0);
        long unreadCount = notificationMapper.countByExample(notificationExample);
        return unreadCount;
    }
}