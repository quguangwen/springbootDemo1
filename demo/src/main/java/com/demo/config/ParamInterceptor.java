package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 拦截器测试demo。
 * 两种方式实现拦截器
 *  1.通过实现HandlerInterceptor接口类，实现PreHandle方法，PostHandler afterCompletion
 *  2.通过继承HandlerInterceptor类，重写需要的处理方法。
 *
 * */


public class ParamInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String param1 = request.getContextPath();
        String param2 = request.getMethod();
        String param3 = request.getQueryString();
        String param4 = request.getContentType();
        System.out.println(param1 + " ," + param2 + ", " + param3 + ", " + param4);
        return true;
    }

}
