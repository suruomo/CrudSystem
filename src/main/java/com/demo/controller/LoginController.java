package com.demo.controller;

import com.demo.dao.UserMapper;
import com.demo.model.User;
import com.demo.service.impl.UserServiceImpl;
import com.demo.utils.Md5;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @author 苏若墨
 */
@Controller
public class LoginController {
    @Resource
    private UserMapper userMApper;
    @Resource
    private UserServiceImpl userService;

    @GetMapping(value = {"/"})
    public String hello() {
        return "login";
    }

    @PostMapping(value = {"/doLogin"})
    public String doLogin(@RequestParam("username") String userId, @RequestParam("password") String password,
                          HttpServletRequest request,Model model) {
        /**
         * 使用Shiro编写认证操作
         */
        //1.获取Subject
        Subject subject= SecurityUtils.getSubject();
        //2.获取MD5加密后密码，封装用户数据
        password= new Md5().endode(password);
        UsernamePasswordToken token=new UsernamePasswordToken(userId,password);
        //3.执行登陆方法
        try{
            //登陆成功,跳转主页面
            subject.login(token);
            request.getSession().setAttribute("user",userMApper.selectByPrimaryKey(userId));
            return "redirect:/main";
        } catch (UnknownAccountException e) {
            //登陆失败：用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "login";
        }
        catch(IncorrectCredentialsException e){
            //登陆失败：密码错误
            model.addAttribute("msg","密码错误");
            return "login";
        }
        catch(LockedAccountException e){
            //登陆失败：账户被锁定
            model.addAttribute("msg","该账号已锁定，请联系管理员后登陆");
            return "login";
        }
    }

    @GetMapping(value = {"/main"})
    public String index() {
        return "main";
    }

    @GetMapping(value = {"/user/exit"})
    public String doLogout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "login";
    }
}
