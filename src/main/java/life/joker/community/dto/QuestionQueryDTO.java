package life.joker.community.dto;

import lombok.Data;

/**
 * @author joker
 * @date 2023/03/15 20:39
 **/
@Data
public class QuestionQueryDTO {
    private String search;
    private String tag;
}