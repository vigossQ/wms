package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.Brand;
import cn.wolfcode.wms.qo.QueryObject;
import cn.wolfcode.wms.service.IBrandService;
import cn.wolfcode.wms.utils.JsonResult;
import cn.wolfcode.wms.utils.RequiredPermisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private IBrandService service;

    @RequestMapping("list")
    @RequiredPermisson("品牌列表")
    public String list(Model model, @ModelAttribute("qo") QueryObject qo) {
        model.addAttribute("result", service.query(qo));
        return "brand/list";
    }

    @RequestMapping("delete")
    @ResponseBody
    @RequiredPermisson("品牌删除")
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
    @RequiredPermisson("品牌编辑")
    public String input(Long id, Model model) {
        if (id != null) {
            Brand brand = service.selectByPrimaryKey(id);
            model.addAttribute("brand", brand);
        }
        return "brand/input";
    }

    @RequestMapping("saveOrUpdate")
    @RequiredPermisson("品牌保存或更新")
    @ResponseBody
    public JsonResult saveOrUpdate(Brand brand) {
        JsonResult result = new JsonResult();
        try {
            if (brand.getId() != null) {
                service.updateByPrimaryKey(brand);
            } else {
                service.insert(brand);
            }
        }catch (Exception e){
            e.printStackTrace();
            result.mark("保存失败");
        }
        return result;
    }
}
