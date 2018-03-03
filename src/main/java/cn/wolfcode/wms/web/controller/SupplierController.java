package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.Supplier;
import cn.wolfcode.wms.qo.QueryObject;
import cn.wolfcode.wms.service.ISupplierService;
import cn.wolfcode.wms.utils.JsonResult;
import cn.wolfcode.wms.utils.RequiredPermisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("supplier")
public class SupplierController {

    @Autowired
    private ISupplierService service;

    @RequestMapping("list")
    @RequiredPermisson("供应商列表")
    public String list(Model model, @ModelAttribute("qo") QueryObject qo) {
        model.addAttribute("result", service.query(qo));
        return "supplier/list";
    }

    @RequestMapping("delete")
    @ResponseBody
    @RequiredPermisson("供应商删除")
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
    @RequiredPermisson("供应商编辑")
    public String input(Long id, Model model) {
        if (id != null) {
            Supplier supplier = service.selectByPrimaryKey(id);
            model.addAttribute("supplier", supplier);
        }
        return "supplier/input";
    }

    @RequestMapping("saveOrUpdate")
    @RequiredPermisson("供应商保存或更新")
    @ResponseBody
    public JsonResult saveOrUpdate(Supplier supplier) {
        JsonResult result = new JsonResult();
        try {
            if (supplier.getId() != null) {
                service.updateByPrimaryKey(supplier);
            } else {
                service.insert(supplier);
            }
        }catch (Exception e){
            e.printStackTrace();
            result.mark("保存失败");
        }
        return result;
    }
}
