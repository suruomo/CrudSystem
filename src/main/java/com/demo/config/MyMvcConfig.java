package com.demo.config;


import com.demo.component.LoginHandlerInterceptor;
import com.demo.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
 *
 * @author 苏若墨
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    /**
     * 试图跳转控制器
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //增加视图映射
        registry.addViewController("/").setViewName("login");
    }

    /**
     * 添加静态资源文件，外部可以直接访问地址
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //需要配置1：----------- 需要告知系统，这是要被当成静态文件的！
        //第一个方法设置访问路径前缀，第二个方法设置资源路径
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    /**
     * 注册拦截器,实现登陆拦截
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //静态资源；  *.css , *.js
        //SpringBoot已经做好了静态资源映射
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/", "/index", "/user/login", "/static/**");
    }

    @Bean
    public LocaleResolver localeResolver() {

        return new MyLocaleResolver();
    }

}
