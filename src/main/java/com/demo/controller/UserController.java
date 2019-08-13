package com.demo.controller;

import com.demo.dao.UserMapper;
import com.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserMapper userMapper;
    //查询所有用户返回列表页面
    @GetMapping("/users")
    public String  list(Model model){
        List<User> users = userMapper.getAll();
        System.out.println(users);
        //放在请求域中
        model.addAttribute("users",users);
        // thymeleaf默认就会拼串
        // classpath:/templates/xxxx.html
        return "users/list";
    }
    //来到员工添加页面
    @GetMapping("/user")
    public String toAddPage(){
        return "users/add";
    }
    //员工添加
    //SpringMVC自动将请求参数和入参对象的属性进行一一绑定；要求请求参数的名字和javaBean入参的对象里面的属性名是一样的
    @PostMapping("/user")
    public String addUser(User user){
        //来到员工列表页面
        System.out.println("保存的员工信息："+user.getEmail());
        //保存员工
        user.setPassword("xMpCOKC5I4INzFCab3WEmw==");   //默认密码为1
        userMapper.insert(user);
        // redirect: 表示重定向到一个地址  /代表当前项目路径
        // forward: 表示转发到一个地址
        return "redirect:/users";
    }
    //来到修改页面，查出当前员工，在页面回显
    @GetMapping("/user/{id}")
    public String toEditPage(@PathVariable("id") String loginName, Model model){
        User user = userMapper.selectByPrimaryKey(loginName);
        model.addAttribute("user",user);
        System.out.println(user);
        //回到修改页面(add是一个修改添加二合一的页面);
        return "users/add";
    }
    //员工修改；需要提交员工id；
    @PutMapping("/user")
    public String updateUser(User user){
        System.out.println("修改的员工数据："+user);
        userMapper.updateByPrimaryKey(user);
        return "redirect:/users";
    }
    //员工删除
    @PostMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") String loginName){
        userMapper.deleteByPrimaryKey(loginName);
        return "redirect:/users";
    }
}
