package life.joker.community.mapper;

import life.joker.community.model.Login;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author joker
 * @date 2023/02/22 10:57
 **/
@Mapper
public interface LoginMapper {
    @Insert("INSERT INTO LOGIN (NAME,ACCOUNT_ID,TOKEN,GMT_CREATE,GMT_MODIFIED) VALUES (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(Login login);

}