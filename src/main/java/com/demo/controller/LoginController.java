package com.demo.controller;

import com.demo.dao.UserMapper;
import com.demo.model.User;
import com.demo.service.impl.UserServiceImpl;
import com.demo.utils.Md5;
import com.demo.utils.VerifyCode;
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
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * @author 苏若墨
 */
@Controller
public class LoginController {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserServiceImpl userService;
    /**
     * 验证码
     */
    public static final String verifyCode="verifyCode";

    @GetMapping(value = {"/","index"})
    public String hello() {
        return "login";
    }

    @GetMapping("/verifycode")
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

        // 设置相应类型,告诉浏览器输出的内容为图片
        response.setContentType("image/jpeg");
        // 不缓存此内容
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        try {
            HttpSession session = request.getSession();
            VerifyCode tool = new VerifyCode();
            StringBuffer code = new StringBuffer();
            BufferedImage image = tool.genRandomCodeImage(code);
            session.removeAttribute(verifyCode);
            session.setAttribute(verifyCode, code.toString());
            // 将内存中的图片通过流动形式输出到客户端
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @PostMapping(value = {"/doLogin"})
    public String doLogin(@RequestParam("username") String userId, @RequestParam("password") String password,
                          @RequestParam("verifycode") String verifycode,
                         HttpServletRequest request,HttpServletResponse response,Model model) {
        //校验验证码
        //session中的验证码
        String sessionCaptcha = (String) SecurityUtils.getSubject().getSession().getAttribute(verifyCode);
        if (null == verifycode || !verifycode.equalsIgnoreCase(sessionCaptcha)) {
            model.addAttribute("msg","验证码错误！");
            return "login";
        }

        /**
         * 使用Shiro编写认证操作
         */
        //1.获取Subject
        Subject subject= SecurityUtils.getSubject();
        //2.获取MD5加密后密码，封装用户数据
        password= new Md5().endode(password);
        boolean rm=true;
        if(request.getParameter("rememberMe")==null){
            rm=false;
        }
        UsernamePasswordToken token=new UsernamePasswordToken(userId,password,rm);
        System.out.println("rememberMe:"+token);
        try{
            //若当前用户没有授权，进行验证
            if(!subject.isAuthenticated()){
                subject.login(token);
            }
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
        return "redirect:/main";
    }

    @GetMapping(value = {"/main"})
    public String index(HttpServletRequest request,Model model) {
        User user= SecurityUtils.getSubject().getPrincipals().oneByType(User.class);
        System.out.println("name:"+user.getUserName());
        System.out.println("id:"+user.getUserId());
        request.getSession().setAttribute("user",user);
        return "main";
    }

    @GetMapping(value = {"/user/exit"})
    public String doLogout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "login";
    }
}
