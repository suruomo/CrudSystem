package com.demo.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
     boolean findUser(Long id, String password);
}
