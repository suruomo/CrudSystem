package com.demo.controller;

import com.demo.dao.AluminumMapper;
import com.demo.model.Aluminum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AluminumController {
    @Autowired
    AluminumMapper aluminumMapper;


    //查询所有用户返回列表页面
    @GetMapping("/aluminum")
    public String list() {
        return "aluminum/list";
    }
    //数据添加页面
    @GetMapping("/aluminum/add")
    public String add() {
        return "aluminum/add";
    }
    //数据添加页面
    @GetMapping("/aluminum/card")
    public String card() {
        return "aluminum/card";
    }
    //查询所有用户返回列表页面
    @ResponseBody
    @GetMapping("/aluminumData")
    public Map<String, Object> list(@RequestParam("page") int page, @RequestParam("limit") int limit) throws JsonProcessingException {
        page=(page-1)*limit;
        List<Aluminum> users = aluminumMapper.getAll(page,limit);
        int count=aluminumMapper.getCount().size();
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

}
