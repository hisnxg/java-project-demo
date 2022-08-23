package com.kkb.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * @Author 何世鹏
 * @Date 2021/3/18 15:57
 * 登录拦截器：
 *      执行流程简介：拦截器 -> controller -> 拦截器结束执行
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("登录拦截器开始执行");
        /**
         * 判断登录返回true(放行),未登录返回false
         *      该方案目前被过滤器替代，保留用于后续扩展
         */

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("登录拦截器结束执行");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("无论是否有异常都要执行");
    }
}
