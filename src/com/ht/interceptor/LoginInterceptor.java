package com.ht.interceptor;

import com.ht.service.llb.IRoleService;
import com.ht.vo.ModuleVO;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String uri = httpServletRequest.getRequestURI();
        System.out.println("链接"+uri+"进入拦截器");
        Object ob = httpServletRequest.getSession().getAttribute("emp");
        //如果未登录
        if (ob == null) {
            //httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/public/login");
            String url = httpServletRequest.getContextPath()+"/public/login";
            PrintWriter out = httpServletResponse.getWriter();
            out.println("<html>");
            out.println("<script>");
            out.println("window.open ('"+url+"','_top')");
            out.println("</script>");
            out.println("</html>");
            return false;
        } else {
            if ("/office/public/index".equals(uri) || "/office/public/welcome".equals(uri)||"/office/system/exit".equals(uri)){
                return true;
            }
            List<ModuleVO> allModule = (List<ModuleVO>) httpServletRequest.getSession().getAttribute("moduleList");
            for (ModuleVO moduleVO : allModule) {
                if (moduleVO.getParentId()!=0) {
                    if ((uri.split("/")[2]).equals(moduleVO.getUrl().split("/")[0])){
                        return true;
                    }
                }
            }
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/public/error");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
