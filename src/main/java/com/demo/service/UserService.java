package com.demo.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
     boolean findUser(Integer id, String password);
}
