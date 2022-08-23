package com.kkb.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @Author 何世鹏
 * @Date 2021/3/18 21:19
 * 登录过滤器:
 *      任何没有权限的访问都将会被转发到login.html,目的是为了做权限控制。
 *      注释后请勿提交!!!
 *      注：无需注释，拿一个数据库中存在的账号密码即可登录
 *      账号：hsp 密码：123123
 */
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("过滤器开始执行");

        //进行强制类型转成子类，这样可以调用子类中的方法
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //注释后请勿提交!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


        //获取请求地址
        String requestURI = req.getRequestURI();

        //打印方便调试
        System.out.println("URI:"+requestURI);
        HttpSession session = req.getSession();

        //以下地址不做权限要求,如有需请自行添加
        String[] URIS = {"login.html","/userLogin","/login","/json",".js",".css",".ico",".jpg",".png",".php"};
        boolean flag = false;
        for (String uris : URIS) {
            if (requestURI.indexOf(uris) != -1) {
                flag = true;
                break;
            }
        }

        if (flag) {
            //过滤器执行结束
            filterChain.doFilter(servletRequest ,servletResponse);
        }else {
            Integer id = (Integer) session.getAttribute("id");
            System.out.println("filter" + id);
            if (id != null) {
                System.out.println("正常登录");
                filterChain.doFilter(servletRequest ,servletResponse);
            }else {
                //转发
                resp.sendRedirect("/login.html");
            }

        }
//        filterChain.doFilter(servletRequest ,servletResponse);

    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁");
    }
}
