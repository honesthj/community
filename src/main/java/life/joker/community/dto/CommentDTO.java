package life.joker.community.dto;

import life.joker.community.model.Login;
import lombok.Data;

/**
 * @author joker
 * @date 2023/03/06 13:04
 **/
@Data
public class CommentDTO {
    private Long id;

    private Long parentId;

    private Integer type;

    private Long commentator;

    private Long gmtCreate;

    private Long gmtModified;

    private Long likeCount;

    private String content;

    private Login login;
    private Integer commentCount;
}