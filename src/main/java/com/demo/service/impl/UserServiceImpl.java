package com.demo.service.impl;

import com.demo.dao.UserDao;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    public boolean findUser(Integer id, String password) {
        if(userDao.findUserById(id).getPassword().equals(password)){
            return true;
        }
        else{
            return false;
        }
    }
}
