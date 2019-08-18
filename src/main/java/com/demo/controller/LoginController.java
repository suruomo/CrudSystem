package com.demo.controller;

import com.demo.dao.UserMapper;
import com.demo.model.User;
import com.demo.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Controller
public class LoginController {
    @Resource
    private UserMapper userMApper;
    @Resource
    private UserServiceImpl userService;
    @GetMapping(value = {"/","/index"})
    public String hello(){
        return "login";
    }

    @PostMapping(value = {"/user/login"})
    public String doLogin(@RequestParam("username")String loginName, @RequestParam("password")String password,
                          HttpServletRequest request, Model model){
       if(userService.findUser(loginName,password)){
           User user=userMApper.selectByPrimaryKey(loginName);
           request.getSession().setAttribute("user",user);
           request.getSession().setAttribute("loginName",user.getLoginName());
           return "redirect:/main.html";
       }
       else{
           model.addAttribute("msg","用户名或密码错误，请重新登录");
       }
        return "login";
    }

    @GetMapping(value = {"/user/exit"})
    public String doLogout(HttpServletRequest request){
        request.getSession().invalidate();
        return "login";
    }
}
