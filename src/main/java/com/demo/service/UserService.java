package com.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface UserService {
     boolean findUser(String loginName, String password);

     int importExcel(String name, MultipartFile file);
}
