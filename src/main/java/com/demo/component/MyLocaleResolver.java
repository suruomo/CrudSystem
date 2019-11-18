package com.demo.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 可以在连接上携带区域信息  切换中英文界面
 * LocaleResolver，其主要作用在于根据不同的用户区域展示不同的视图
 * @author 苏若墨
 */
public class MyLocaleResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {      //设置解析的语言代码
        String l = request.getParameter("l");
        Locale locale = Locale.getDefault();
        //若区域信息不为空
        if (!StringUtils.isEmpty(l)) {
            //使用下划线分割
            String[] split = l.split("_");
            //（语言代码，国家代码）
            locale = new Locale(split[0], split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
