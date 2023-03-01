package life.joker.community.mapper;

import life.joker.community.model.Login;
import org.apache.ibatis.annotations.*;


/**
 * @author joker
 * @date 2023/02/22 10:57
 **/
@Mapper
public interface LoginMapper {
    @Insert("INSERT INTO LOGIN (NAME,ACCOUNT_ID,TOKEN,GMT_CREATE,GMT_MODIFIED,avatar_url,bio) VALUES (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl},#{bio})")
    void insert(Login login);

    @Select("SELECT * FROM LOGIN WHERE token = #{token}")
    Login findByToken(@Param("token") String token);

    @Select("SELECT * FROM LOGIN WHERE account_id = #{accountId}")
    Login findByAccountId(@Param("accountId") String accountId);

    @Select("SELECT * FROM LOGIN WHERE id = #{id}")
    Login findById(@Param("id") Integer Id);

    @Update("UPDATE LOGIN SET NAME=#{name},bio=#{bio},token=#{token},gmt_modified=#{gmtModified},avatar_url=#{avatarUrl} WHERE id = #{id}")
    void update(Login login);
}