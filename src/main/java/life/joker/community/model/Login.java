package life.joker.community.model;

import lombok.Data;

/**
 * @author joker
 * @date 2023/02/22 11:00
 **/
@Data
public class Login {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
    private String bio;
}
