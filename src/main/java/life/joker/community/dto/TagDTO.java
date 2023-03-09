package life.joker.community.dto;

import lombok.Data;

import java.util.List;

/**
 * @author joker
 * @date 2023/03/08 15:59
 **/
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}