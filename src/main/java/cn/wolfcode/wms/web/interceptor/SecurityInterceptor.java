package cn.wolfcode.wms.web.interceptor;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.utils.NoPermissionException;
import cn.wolfcode.wms.utils.PermissionUtils;
import cn.wolfcode.wms.utils.RequiredPermisson;
import cn.wolfcode.wms.utils.UserContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Set;

public class SecurityInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Employee currentEmp = UserContext.getEmployee();
        //如果是超级管理员,放行
        if (currentEmp.isAdmin()) {
            return true;
        }
        //如果方法上没有贴指定注解,放行
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //这里通过handler拿到的方法对象就是将要访问的.do
        Method method = handlerMethod.getMethod();
        if (!method.isAnnotationPresent(RequiredPermisson.class)) {
            return true;
        }
        //用户拥有对应权限,放行
        Set<String> expressions = UserContext.getExpressions();
        if (expressions.contains(PermissionUtils.getExpression(method))) {
            return true;
        }
        throw new NoPermissionException("抱歉,没有权限访问");
//        request.getRequestDispatcher("/WEB-INF/views/nopermission.jsp").forward(request, response);
//        return false;
    }
}

