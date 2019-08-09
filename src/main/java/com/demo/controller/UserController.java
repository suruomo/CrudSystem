package com.demo.controller;

import com.demo.dao.UserDao;
import com.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserDao userDao;
    //查询所有用户返回列表页面
    @GetMapping("/users")
    public String  list(Model model){
        List<User> users = userDao.getAll();
        System.out.println(users);
        //放在请求域中
        model.addAttribute("users",users);
        // thymeleaf默认就会拼串
        // classpath:/templates/xxxx.html
        return "users/list";
    }
}
