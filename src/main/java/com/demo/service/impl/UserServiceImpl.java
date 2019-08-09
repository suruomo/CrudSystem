package com.demo.service.impl;

import com.demo.dao.UserDao;
import com.demo.service.UserService;
import com.demo.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    public boolean findUser(Integer id, String password) {
        String s=new Md5().endode(password);
        if(userDao.findUserById(id).getPassword().equals(s)){
            return true;
        }
        else{
            return false;
        }
    }
}
