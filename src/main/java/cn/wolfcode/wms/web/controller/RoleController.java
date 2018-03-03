package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.Role;
import cn.wolfcode.wms.qo.QueryObject;
import cn.wolfcode.wms.service.IPermissionService;
import cn.wolfcode.wms.service.IRoleService;
import cn.wolfcode.wms.service.ISystemMenuService;
import cn.wolfcode.wms.utils.JsonResult;
import cn.wolfcode.wms.utils.RequiredPermisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private IRoleService service;
    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private ISystemMenuService systemMenuService;

    @RequestMapping("list")
    @RequiredPermisson("角色列表")
    public String list(Model model, @ModelAttribute("qo") QueryObject qo) {
        model.addAttribute("result", service.query(qo));
        return "role/list";
    }

    @RequestMapping("delete")
    @ResponseBody
    @RequiredPermisson("角色删除")
    public JsonResult delete(Long id) {
        JsonResult result = new JsonResult();
        try {
            if (id != null) {
                service.deleteByPrimaryKey(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("删除错误");
        }
        return result;
    }

    @RequestMapping("input")
    @RequiredPermisson("角色编辑")
    public String input(Long id, Model model) {
        if (id != null) {
            Role role = service.selectByPrimaryKey(id);
            model.addAttribute("role", role);
        }
        model.addAttribute("permissions", permissionService.selectAll());
        model.addAttribute("systemMenus", systemMenuService.selectAll());
        return "role/input";
    }

    @RequestMapping("saveOrUpdate")
    @RequiredPermisson("角色保存或更新")
    @ResponseBody
    public JsonResult saveOrUpdate(Role dept, Long[] permissionIds, Long[] systemMenuIds) {
        JsonResult result = new JsonResult();
        try {
            if (dept.getId() != null) {
                service.updateByPrimaryKey(dept, permissionIds, systemMenuIds);
            } else {
                service.insert(dept, permissionIds, systemMenuIds);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("保存失败");
        }
        return result;
    }
}
