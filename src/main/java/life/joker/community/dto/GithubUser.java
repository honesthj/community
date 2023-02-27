package life.joker.community.dto;

import lombok.Data;

/**
 * @author joker
 * @date 2023/02/18 19:30
 **/
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;

    private String avatarUrl;

}