package life.joker.community.dto;

import life.joker.community.model.Login;
import lombok.Data;

/**
 * @author joker
 * @date 2023/03/08 21:09
 **/
@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private Long questionId;
    private String typeName;
    private Integer type;
}