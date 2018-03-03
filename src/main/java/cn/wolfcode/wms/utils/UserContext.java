package cn.wolfcode.wms.utils;

import cn.wolfcode.wms.domain.Employee;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Set;

abstract public class UserContext {
    public static final String EMPLOYEE_IN_SESSION = "employee_in_session";
    public static final String EXPERSSIONS_IN_SESSION = "experssions_in_session";

    public static HttpSession getSession() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) (RequestContextHolder.getRequestAttributes());
        return requestAttributes.getRequest().getSession();
    }

    public static void setEmployee(Employee employee) {
        getSession().setAttribute(EMPLOYEE_IN_SESSION, employee);
    }

    public static Employee getEmployee() {
        return (Employee) getSession().getAttribute(EMPLOYEE_IN_SESSION);
    }

    public static void setExpressions(Set<String> expressions) {
        getSession().setAttribute(EXPERSSIONS_IN_SESSION, expressions);
    }

    public static Set<String> getExpressions() {
        return (Set<String>) getSession().getAttribute(EXPERSSIONS_IN_SESSION);
    }
}
