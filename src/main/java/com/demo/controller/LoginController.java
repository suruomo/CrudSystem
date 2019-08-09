package com.demo.controller;

import com.demo.dao.UserDao;
import com.demo.model.User;
import com.demo.service.impl.UserServiceImpl;
import com.demo.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Controller
public class LoginController {
    @Resource
    private UserDao userDao;
    @Resource
    private UserServiceImpl userService;
    @GetMapping(value = {"/","/index"})
    public String hello(){
        return "login";
    }

    @PostMapping(value = {"/user/login"})
    public String doLogin(@RequestParam("username")Integer id, @RequestParam("password")String password,
                          HttpServletRequest request, Model model){
       if(userService.findUser(id,password)){
           User user=userDao.findUserById(id);
           request.getSession().setAttribute("user",user);
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
