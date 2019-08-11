package com.demo.service.impl;

import com.demo.dao.UserMapper;
import com.demo.service.UserService;
import com.demo.utils.Md5;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    public boolean findUser(Long id, String password) {
        String s=new Md5().endode(password);
        if(userMapper.selectByPrimaryKey(id).getPassword().equals(s)){
            return true;
        }
        else{
            return false;
        }
    }
}
