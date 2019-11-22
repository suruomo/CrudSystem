package com.demo.service;

import com.demo.model.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
    List<User> getAll(int page, int limit);
}
