package com.demo.controller;

import com.demo.aop.SystemLog;
import com.demo.dao.LogMapper;
import com.demo.model.Log;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 苏若墨
 */
@Controller
public class LogController {
    @Resource
    private LogMapper logMapper;

    /**
     * 进入日志列表
     * @return
     */
    @GetMapping("/logs")
    @SystemLog(module = "操作：查看日志数据")
    public String toListPage(){
        return "log/list";
    }
    /**
     * 查询所有日志数据
     */
    @SystemLog(module = "数据：返回日志数据")
    @ResponseBody
    @GetMapping("/logsData")
    public Map<String, Object> list(@RequestParam("page") int page, @RequestParam("limit") int limit) throws JsonProcessingException {
        page = (page - 1) * limit;
        List<Log> logs = logMapper.getAll(page, limit);
        int count = logs.size();
        Map<String, Object> map = new HashMap();
        //返回Json
        ObjectMapper mapper = new ObjectMapper();
        //json内对象不为空
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String data = mapper.writeValueAsString(logs);
        JSONArray json = JSONArray.fromObject(data);
        map.put("code", 0);
        map.put("msg", "");
        map.put("data", json);
        map.put("count", count);
        return map;
    }

}
