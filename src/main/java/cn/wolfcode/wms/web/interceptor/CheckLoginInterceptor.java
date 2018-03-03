package cn.wolfcode.wms.web.interceptor;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.utils.UserContext;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckLoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Employee currentEmp = UserContext.getEmployee();
        if (currentEmp == null) {
            response.sendRedirect("/login.jsp");
            //拦截
            return false;
        }
        //放行
        return true;
    }
}

