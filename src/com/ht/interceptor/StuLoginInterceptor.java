package com.ht.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class StuLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String uri = httpServletRequest.getRequestURI();
        System.out.println("链接"+uri+"进入拦截器");
        Object ob = httpServletRequest.getSession().getAttribute("student");
        //如果未登录
        if (ob == null) {
            //httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/public/login");
            String url = httpServletRequest.getContextPath()+"/studentSide/login";
            PrintWriter out = httpServletResponse.getWriter();
            out.println("<html>");
            out.println("<script>");
            out.println("window.open ('"+url+"','_top')");
            out.println("</script>");
            out.println("</html>");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
