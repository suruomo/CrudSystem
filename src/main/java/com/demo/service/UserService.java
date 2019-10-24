package com.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface UserService {
    boolean findUser(String loginName, String password);

    void uploadUser(MultipartFile file, String fileName) throws IOException;
}
