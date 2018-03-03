package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.qo.ProductStockQueryObject;
import cn.wolfcode.wms.service.IBrandService;
import cn.wolfcode.wms.service.IDepotService;
import cn.wolfcode.wms.service.IProductStockService;
import cn.wolfcode.wms.utils.RequiredPermisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("productStock")
public class ProductStockController {

    @Autowired
    private IProductStockService service;
    @Autowired
    private IDepotService depotService;
    @Autowired
    private IBrandService brandService;

    @RequestMapping("list")
    @RequiredPermisson("即时库存报表")
    public String list(Model model, @ModelAttribute("qo") ProductStockQueryObject qo) {
        model.addAttribute("result", service.query(qo));
        model.addAttribute("depots", depotService.selectAll());
        model.addAttribute("brands", brandService.selectAll());
        return "productStock/list";
    }
}
