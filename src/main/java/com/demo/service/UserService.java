package com.demo.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
     boolean findUser(String loginName, String password);
}
