package com.nxg.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter({"/User/*","/Role/*","/page/index.html","/Resource/*","/registration/*","/medicine/*","/hospital/*","/doctor/*"})
public class AccessControllerFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req= (HttpServletRequest) request;
        HttpServletResponse resp= (HttpServletResponse) response;
        String name = (String) req.getSession().getAttribute("loginName");
        if (name==null){
            //resp.sendError(404,"很遗憾，权限不足！");
            resp.sendRedirect("/page/login.html");
        } else {
            chain.doFilter(request, response);
        }
    }
}
