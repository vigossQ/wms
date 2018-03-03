package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.Depot;
import cn.wolfcode.wms.qo.QueryObject;
import cn.wolfcode.wms.service.IDepotService;
import cn.wolfcode.wms.utils.JsonResult;
import cn.wolfcode.wms.utils.RequiredPermisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("depot")
public class DepotController {

    @Autowired
    private IDepotService service;

    @RequestMapping("list")
    @RequiredPermisson("仓库列表")
    public String list(Model model, @ModelAttribute("qo") QueryObject qo) {
        model.addAttribute("result", service.query(qo));
        return "depot/list";
    }

    @RequestMapping("delete")
    @ResponseBody
    @RequiredPermisson("仓库删除")
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
    @RequiredPermisson("仓库编辑")
    public String input(Long id, Model model) {
        if (id != null) {
            Depot depot = service.selectByPrimaryKey(id);
            model.addAttribute("depot", depot);
        }
        return "depot/input";
    }

    @RequestMapping("saveOrUpdate")
    @RequiredPermisson("仓库保存或更新")
    @ResponseBody
    public JsonResult saveOrUpdate(Depot depot) {
        JsonResult result = new JsonResult();
        try {
            if (depot.getId() != null) {
                service.updateByPrimaryKey(depot);
            } else {
                service.insert(depot);
            }
        }catch (Exception e){
            e.printStackTrace();
            result.mark("保存失败");
        }
        return result;
    }
}
