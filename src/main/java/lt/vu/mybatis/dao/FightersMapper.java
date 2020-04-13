package lt.vu.mybatis.dao;

import lt.vu.mybatis.model.Fighter;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface FightersMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.fighters
     *
     * @mbg.generated Mon Apr 13 23:13:50 EEST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.fighters
     *
     * @mbg.generated Mon Apr 13 23:13:50 EEST 2020
     */
    int insert(Fighter record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.fighters
     *
     * @mbg.generated Mon Apr 13 23:13:50 EEST 2020
     */
    Fighter selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.fighters
     *
     * @mbg.generated Mon Apr 13 23:13:50 EEST 2020
     */
    List<Fighter> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.fighters
     *
     * @mbg.generated Mon Apr 13 23:13:50 EEST 2020
     */
    int updateByPrimaryKey(Fighter record);
}