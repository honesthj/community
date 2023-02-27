package life.joker.community.dto;

import life.joker.community.model.Login;
import lombok.Data;

/**
 * @author joker
 * @date 2023/02/25 11:59
 **/
@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private Login login;
}