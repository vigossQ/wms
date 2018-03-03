package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.utils.UserContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

    @RequestMapping("logout")
    public String logout() {
        UserContext.setEmployee(null);
        return "redirect:/login.jsp";
    }
}
