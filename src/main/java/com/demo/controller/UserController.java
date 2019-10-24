package com.demo.controller;

import com.demo.dao.UserMapper;
import com.demo.model.User;
import com.demo.service.UserService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 苏若墨
 */
@Controller
public class UserController {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;


    /**
     * 查询所有用户返回列表页面
     */
    @GetMapping("/users")
    public String list() {
        return "user/list";
    }

    /**
     * 查询所有用户返回数据
     */
    @ResponseBody
    @GetMapping("/usersData")
    public Map<String, Object> list(@RequestParam("page") int page, @RequestParam("limit") int limit) throws JsonProcessingException {
        page = (page - 1) * limit;
        List<User> users = userMapper.getAll(page, limit);
        int count = userMapper.getCount();
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

    /**
     * 来到员工添加页面
     */
    @GetMapping("/user")
    public String toAddPage() {
        return "user/add";
    }

    /**
     * 员工添加操作
     * SpringMVC自动将请求参数和入参对象的属性进行一一绑定；要求请求参数的名字和javaBean入参的对象里面的属性名是一样的
     */
    @PostMapping("/user")
    public String addUser(User user) {
        //保存员工，默认密码为1
        user.setPassword("xMpCOKC5I4INzFCab3WEmw==");
        userMapper.insert(user);
        // redirect: 表示重定向到一个地址  /代表当前项目路径
        // forward: 表示转发到一个地址
        return "redirect:/users";
    }

    /**
     * 来到修改页面，查出当前员工，在页面回显
     */
    @GetMapping("/user/{id}")
    public String toEditPage(@PathVariable("id") String userId, Model model) {
        User user = userMapper.selectByPrimaryKey(userId);
        model.addAttribute("user", user);
        System.out.println(user);
        //回到修改页面(add是一个修改添加二合一的页面);
        return "user/add";
    }

    /**
     * 员工修改:表单需要提交员工id
     */
    @PutMapping("/user")
    public String updateUser(HttpServletRequest request, User user) {
        userMapper.updateByPrimaryKey(user);
        return "redirect:/users";
    }

    /**
     * 员工删除
     */
    @PostMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") String userId) {
        userMapper.deleteByPrimaryKey(userId);
        return "redirect:/users";
    }

    /**
     * 员工批量删除
     */
    @PostMapping("/user/delete")
    public String deleteMultiUser(@RequestParam("ids") String ids) {
        String id[] = ids.split("//");
        for (int i = 0; i < id.length; i++) {
            System.out.println(id[i]);
            userMapper.deleteByPrimaryKey(id[i]);
        }
        return "user/list";
    }

    /**
     * 员工批量导入模板下载
     */
    @GetMapping("/download/userTemplate")
    public void downloadUserTemplate(HttpServletResponse response) throws IOException {
        //获取输入流，原始模板位置
        String filePath = getClass().getResource("/templates/download/sys_user.xlsx").getPath();
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(filePath)));
        //假如以中文名下载的话，设置下载文件名称
        String filename = "人员信息导入模板.xls";
        //转码，免得文件名中文乱码
        filename = URLEncoder.encode(filename, "UTF-8");
        //设置文件下载头
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
        //设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        int len = 0;
        while ((len = bis.read()) != -1) {
            out.write(len);
            out.flush();
        }
        out.close();
    }

    /**
     * 员工批量导入
     *
     * @param file
     * @return
     */
    @PostMapping("/user/upload")
    @ResponseBody
    public int uploadPartMember(@RequestParam("file") MultipartFile file) {
        try {
            if (file != null) {
                //成功上传
                String fileName=file.getOriginalFilename();
                userService.uploadUser(file,fileName);
                return 1;
            } else {
                //文件为空
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            //上传出现异常，请稍后重试
            return 3;
        }
    }
}
