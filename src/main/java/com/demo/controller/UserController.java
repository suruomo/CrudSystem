package com.demo.controller;

import com.demo.dao.UserMapper;
import com.demo.model.User;
import com.demo.service.UserService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;
    //查询所有用户返回列表页面
    @GetMapping("/users")
    public String list() {
        return "layui/list";
    }

    //查询所有用户返回列表页面
    @ResponseBody
    @GetMapping("/usersData")
    public Map<String, Object> list(@RequestParam("page") int page, @RequestParam("limit") int limit) throws JsonProcessingException {
        page=(page-1)*limit;
        List<User> users = userMapper.getAll(page,limit);
        int count=userMapper.getCount().size();
        Map<String, Object> map = new HashMap();
        //返回Json
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);  //json内对象不为空
        String data = mapper.writeValueAsString(users);
        JSONArray json = JSONArray.fromObject(data);
        map.put("code", 0);
        map.put("msg", "");
        map.put("data", json);
        map.put("count", count);
        return map;
    }

    //来到员工添加页面
    @GetMapping("/user")
    public String toAddPage() {
        return "layui/add";
    }

    //员工添加
    //SpringMVC自动将请求参数和入参对象的属性进行一一绑定；要求请求参数的名字和javaBean入参的对象里面的属性名是一样的
    @PostMapping("/user")
    public String addUser(User user) {
        //来到员工列表页面
        System.out.println("保存的员工信息：" + user.getEmail());
        //保存员工
        user.setPassword("xMpCOKC5I4INzFCab3WEmw==");   //默认密码为1
        userMapper.insert(user);
        // redirect: 表示重定向到一个地址  /代表当前项目路径
        // forward: 表示转发到一个地址
        return "redirect:/users";
    }

    //来到修改页面，查出当前员工，在页面回显
    @GetMapping("/user/{id}")
    public String toEditPage(@PathVariable("id") String loginName, Model model) {
        User user = userMapper.selectByPrimaryKey(loginName);
        model.addAttribute("user", user);
        System.out.println(user);
        //回到修改页面(add是一个修改添加二合一的页面);
        return "layui/add";
    }

    //员工修改；需要提交员工id；
    @PutMapping("/user")
    public String updateUser(HttpServletRequest request,User user) {
        userMapper.updateByPrimaryKey(user);
        return "redirect:/users";
    }

    //员工删除
    @PostMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") String loginName) {
        userMapper.deleteByPrimaryKey(loginName);
        return "redirect:/users";
    }

    //员工批量删除
    @PostMapping("/user/delete")
    public String deleteMultiUser(@RequestParam("ids") String ids) {
        String id[]=ids.split("//");
        for(int i=0;i<id.length;i++){
            System.out.println(id[i]);
            userMapper.deleteByPrimaryKey(id[i]);
        }
        return "layui/list";
    }

    //员工批量导入
    @PostMapping("/user/upload")
    @ResponseBody
    public int upload(@RequestParam MultipartFile file,
                         HttpServletRequest request, HttpServletResponse response) {
        int judge = 0;
        // 判断文件名是否为空
        if (file == null)
            return 2;
        Map<String, Object> map = new HashMap<String, Object>();
        // 获取文件名
        String name = file.getOriginalFilename();

        // 判断文件大小、即名称
        long size = file.getSize();
        if (name == null || ("").equals(name) && size == 0)
            return 2;
        try
        {
            // 把文件转换成字节流形式
            int i=1;
//            int i = userService.importExcel(name,file);
            if (i > 0)
            {
                judge=1;  //成功
            }
            else
            {
                String Msg = "批量导入EXCEL失败！"; //失败
                judge=2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return judge;

    }
}
