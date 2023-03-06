package life.joker.community.dto;

import lombok.Data;

/**
 * @author joker
 * @date 2023/03/03 10:53
 **/
@Data
public class CommentCreateDTO {
    private Long parentId;
    private String content;
    private Integer type;
}