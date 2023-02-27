package life.joker.community.dto;

import lombok.Data;

/**
 * @author joker
 * @date 2023/02/18 12:09
 **/
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;


}