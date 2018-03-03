package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @Autowired
    private IEmployeeService service;

    @RequestMapping("login")
    public String saveOrUpdate(String name, String password, Model model) {
        try {
            service.checkLogin(name, password);
            return "redirect:/main.do";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMsg", e.getMessage());
            return "forward:/login.jsp";
        }
    }

    @RequestMapping("main")
    public String main() {
        return "main";
    }
}
