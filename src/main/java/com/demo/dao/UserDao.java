package com.demo.dao;


import com.demo.model.User;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserDao {
     User findUserById(Integer id);

     List<User> getAll();

     void addUser(User user);

     void updateUser(User user);

     void deleteUser(Integer id);
}

