package life.joker.community.mapper;

import life.joker.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title, description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from question")
    List<Question> list();

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator=#{loginId}")
    List<Question> listByLoginId(@Param("loginId") Integer loginId);

    @Select("select count(1) from question where creator=#{loginId}")
    Integer countByLoginId(@Param("loginId") Integer loginId);
}
