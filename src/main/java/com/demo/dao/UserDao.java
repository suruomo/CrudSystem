package com.demo.dao;


import com.demo.model.User;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
     User findUserById(Integer id);
}

