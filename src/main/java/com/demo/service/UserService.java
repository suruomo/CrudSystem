package com.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author 苏若墨
 */
@Service
public interface UserService {
    /**
     * 批量上传用户信息
     * @param file 上传文件
     * @param fileName 上传文件名称
     * @throws IOException
     */
    void uploadUser(MultipartFile file, String fileName) throws IOException;
}
