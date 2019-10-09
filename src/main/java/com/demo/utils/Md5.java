package com.demo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Md5 {  //Md5加密
    public Md5() {
    }

    public String endode(String s) {
        String str = "";
        //MD5加密验证
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            //生成随机玩意，除非暴力破解否则不可逆
            byte[] bytes = md.digest(s.getBytes());
            str = Base64.getEncoder().encodeToString(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("错误");
        }
        return str;
    }

}
