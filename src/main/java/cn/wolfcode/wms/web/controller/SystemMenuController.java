package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.SystemMenu;
import cn.wolfcode.wms.qo.SystemMenuQueryObject;
import cn.wolfcode.wms.service.ISystemMenuService;
import cn.wolfcode.wms.utils.JsonResult;
import cn.wolfcode.wms.utils.RequiredPermisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("systemMenu")
public class SystemMenuController {

    @Autowired
    private ISystemMenuService service;

    @RequestMapping("list")
    @RequiredPermisson("系统菜单列表")
    public String list(Model model, @ModelAttribute("qo") SystemMenuQueryObject qo) {
        List<Map<String, Object>> parents = new ArrayList<>();
        SystemMenu parent = service.selectByPrimaryKey(qo.getParentId());
        while (parent != null) {//判断条件应该是parent是否为空的
            Map<String, Object> parentMap = new HashMap<>();
            parentMap.put("id", parent.getId());
            parentMap.put("name", parent.getName());
            parents.add(parentMap);
            parent = parent.getParent();
        }
        model.addAttribute("result", service.query(qo));
        Collections.reverse(parents);
        model.addAttribute("parents", parents);
        return "systemMenu/list";
    }

    @RequestMapping("delete")
    @ResponseBody
    @RequiredPermisson("系统菜单删除")
    public JsonResult delete(Long id) {
        JsonResult result = new JsonResult();
        try {
            if (id != null) {
                service.deleteByPrimaryKey(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("请先删除子菜单");
        }
        return result;
    }

    @RequestMapping("input")
    @RequiredPermisson("系统菜单编辑")
    public String input(Long id, Model model) {
//        if (parentId==null){
//            model.addAttribute("parentId", null);
//            model.addAttribute("parentName", "根目录");
//        }
//        else {
//            SystemMenu parent = service.selectByPrimaryKey(parentId);
//            model.addAttribute("parentId", parent.getId());
//            model.addAttribute("parentName", parent.getName());
//        }
        if (id != null) {
            SystemMenu systemMenu = service.selectByPrimaryKey(id);
            model.addAttribute("systemMenu", systemMenu);
        }
        return "systemMenu/input";
    }

    @RequestMapping("saveOrUpdate")
    @RequiredPermisson("系统菜单保存或更新")
    @ResponseBody
    public JsonResult saveOrUpdate(SystemMenu systemMenu) {
        JsonResult result = new JsonResult();
        try {
            if (systemMenu.getId() != null) {
                service.updateByPrimaryKey(systemMenu);
            } else {
                service.insert(systemMenu);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("保存失败");
        }
        return result;
    }

    @RequestMapping("loadMenuByParentSn")
    @ResponseBody
    public List<Map<String, Object>> loadMenuByParentSn(String parentSn) {
        List<Map<String, Object>> map = service.loadMenuByParentSn(parentSn);
        return map;
    }
}
