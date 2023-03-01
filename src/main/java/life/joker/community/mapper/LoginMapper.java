package life.joker.community.mapper;

import java.util.List;
import life.joker.community.model.Login;
import life.joker.community.model.LoginExample;
import org.apache.ibatis.annotations.Param;

public interface LoginMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOGIN
     *
     * @mbg.generated Wed Mar 01 19:36:36 CST 2023
     */
    long countByExample(LoginExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOGIN
     *
     * @mbg.generated Wed Mar 01 19:36:36 CST 2023
     */
    int deleteByExample(LoginExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOGIN
     *
     * @mbg.generated Wed Mar 01 19:36:36 CST 2023
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOGIN
     *
     * @mbg.generated Wed Mar 01 19:36:36 CST 2023
     */
    int insert(Login row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOGIN
     *
     * @mbg.generated Wed Mar 01 19:36:36 CST 2023
     */
    int insertSelective(Login row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOGIN
     *
     * @mbg.generated Wed Mar 01 19:36:36 CST 2023
     */
    List<Login> selectByExample(LoginExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOGIN
     *
     * @mbg.generated Wed Mar 01 19:36:36 CST 2023
     */
    Login selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOGIN
     *
     * @mbg.generated Wed Mar 01 19:36:36 CST 2023
     */
    int updateByExampleSelective(@Param("row") Login row, @Param("example") LoginExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOGIN
     *
     * @mbg.generated Wed Mar 01 19:36:36 CST 2023
     */
    int updateByExample(@Param("row") Login row, @Param("example") LoginExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOGIN
     *
     * @mbg.generated Wed Mar 01 19:36:36 CST 2023
     */
    int updateByPrimaryKeySelective(Login row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOGIN
     *
     * @mbg.generated Wed Mar 01 19:36:36 CST 2023
     */
    int updateByPrimaryKey(Login row);
}