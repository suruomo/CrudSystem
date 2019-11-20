package com.demo.dao;

import com.demo.model.Aluminum;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author 苏若墨
 */
@Mapper
public interface AluminumMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aluminum
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aluminum
     *
     * @mbggenerated
     */
    int insert(Aluminum record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aluminum
     *
     * @mbggenerated
     */
    int insertSelective(Aluminum record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aluminum
     *
     * @mbggenerated
     */
    Aluminum selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aluminum
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Aluminum record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aluminum
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Aluminum record);

    List<Aluminum> getAll(int page, int limit);

    List<Aluminum> getCount();
}