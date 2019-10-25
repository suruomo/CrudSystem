package com.demo.controller;

import com.demo.dao.AluminumMapper;
import com.demo.model.Aluminum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class AluminumController {

    @Resource
    AluminumMapper aluminumMapper;



    /**
     * 跳转所有铝数据列表页面
     * @return
     */
    @GetMapping("/aluminums")
    public String list() {
        return "aluminum/list";
    }


    /**
     * 跳转数据添加页面
     * @return
     */
    @GetMapping("/aluminum/add")
    public String add() {
        return "aluminum/add";
    }


    /**
     * 跳转至导出MAT1页面
     * @return
     */

    @GetMapping("/metal/export/mat1/{csmmbrName}")
    public String card(@PathVariable String csmmbrName, Model model) {
        //demo导出数据1
        Aluminum aluminum=aluminumMapper.selectByPrimaryKey(1);
        model.addAttribute("metal",aluminum);
        return "aluminum/mat1";
    }



    /**
     * 返回所有铝数据
     * @param page
     * @param limit
     * @return
     * @throws JsonProcessingException
     */
    @ResponseBody
    @GetMapping("/aluminumData")
    public Map<String, Object> list(@RequestParam("page") int page, @RequestParam("limit") int limit) throws JsonProcessingException {
        page = (page - 1) * limit;
        List<Aluminum> users = aluminumMapper.getAll(page, limit);
        int count = aluminumMapper.getCount().size();
        HashMap<String, Object> map = new HashMap();
        //返回Json
        ObjectMapper mapper = new ObjectMapper();
        //json内对象不为空
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String data = mapper.writeValueAsString(users);
        JSONArray json = JSONArray.fromObject(data);
        map.put("code", 0);
        map.put("msg", "");
        map.put("data", json);
        map.put("count", count);
        return map;
    }

}
