package com.xzk.wx.filter;

import com.xzk.bean.User;
import com.xzk.util.UserUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/addExpress.html","/expressAssist.html","/expressList.html","/index.html","/personQRCode.html","/pickExpress.html","/wxIdCardUserInfoModify.html","/wxUserhome.html"})
///userCheckStart.html
public class WxAccessControllerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User userName = UserUtil.getWxUser(request.getSession());
        if (userName != null){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
           // response.sendError(404,"很遗憾,权限不足");
            response.sendRedirect("login.html");
        }
    }

    @Override
    public void destroy() {

    }

}
