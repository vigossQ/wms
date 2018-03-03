package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.StockOutcomeBill;
import cn.wolfcode.wms.qo.StockOutcomeBillQueryObject;
import cn.wolfcode.wms.service.IClientService;
import cn.wolfcode.wms.service.IDepotService;
import cn.wolfcode.wms.service.IStockOutcomeBillService;
import cn.wolfcode.wms.utils.JsonResult;
import cn.wolfcode.wms.utils.RequiredPermisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("stockOutcomeBill")
public class StockOutcomeBillController {

    @Autowired
    private IStockOutcomeBillService service;
    @Autowired
    private IDepotService depotService;
    @Autowired
    private IClientService clientService;

    @RequestMapping("list")
    @RequiredPermisson("出库单列表")
    public String list(Model model, @ModelAttribute("qo") StockOutcomeBillQueryObject qo) {
        model.addAttribute("result", service.query(qo));
        model.addAttribute("depots", depotService.selectAll());
        model.addAttribute("clients", clientService.selectAll());
        return "stockOutcomeBill/list";
    }

    @RequestMapping("delete")
    @ResponseBody
    @RequiredPermisson("出库单删除")
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
    @RequiredPermisson("出库单编辑")
    public String input(Long id, Model model) {
        if (id != null) {
            StockOutcomeBill stockOutcomeBill = service.selectByPrimaryKey(id);
            model.addAttribute("stockOutcomeBill", stockOutcomeBill);
        }
        model.addAttribute("depots", depotService.selectAll());
        model.addAttribute("clients", clientService.selectAll());
        return "stockOutcomeBill/input";
    }

    @RequestMapping("saveOrUpdate")
    @RequiredPermisson("出库单保存或更新")
    @ResponseBody
    public JsonResult saveOrUpdate(StockOutcomeBill stockOutcomeBill) {
        JsonResult result = new JsonResult();
        try {
            if (stockOutcomeBill.getId() != null) {
                service.updateByPrimaryKey(stockOutcomeBill);
            } else {
                service.insert(stockOutcomeBill);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("保存失败");
        }
        return result;
    }

    @RequestMapping("audit")
    @ResponseBody
    @RequiredPermisson("出库单审核")
    public JsonResult audit(Long id) {
        JsonResult result = new JsonResult();
        try {
            if (id != null) {
                service.audit(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.mark(e.getMessage());
        }
        return result;
    }

    @RequestMapping("viewBill")
    public String viewBill(Long id, Model model) {
        if (id != null) {
            StockOutcomeBill stockOutcomeBill = service.selectByPrimaryKey(id);
            model.addAttribute("stockOutcomeBill", stockOutcomeBill);
        }
        return "stockOutcomeBill/viewList";
    }
}
