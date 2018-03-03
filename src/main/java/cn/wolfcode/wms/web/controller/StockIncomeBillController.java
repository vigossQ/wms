package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.StockIncomeBill;
import cn.wolfcode.wms.qo.StockIncomeBillQueryObject;
import cn.wolfcode.wms.service.IStockIncomeBillService;
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
@RequestMapping("stockIncomeBill")
public class StockIncomeBillController {

    @Autowired
    private IStockIncomeBillService service;
    @Autowired
    private IDepotService depotService;

    @RequestMapping("list")
    @RequiredPermisson("入库单列表")
    public String list(Model model, @ModelAttribute("qo") StockIncomeBillQueryObject qo) {
        model.addAttribute("result", service.query(qo));
        model.addAttribute("depots", depotService.selectAll());
        return "stockIncomeBill/list";
    }

    @RequestMapping("delete")
    @ResponseBody
    @RequiredPermisson("入库单删除")
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
    @RequiredPermisson("入库单编辑")
    public String input(Long id, Model model) {
        if (id != null) {
            StockIncomeBill stockIncomeBill = service.selectByPrimaryKey(id);
            model.addAttribute("stockIncomeBill", stockIncomeBill);
        }
        model.addAttribute("depots", depotService.selectAll());
        return "stockIncomeBill/input";
    }

    @RequestMapping("saveOrUpdate")
    @RequiredPermisson("入库单保存或更新")
    @ResponseBody
    public JsonResult saveOrUpdate(StockIncomeBill stockIncomeBill) {
        JsonResult result = new JsonResult();
        try {
            if (stockIncomeBill.getId() != null) {
                service.updateByPrimaryKey(stockIncomeBill);
            } else {
                service.insert(stockIncomeBill);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("保存失败");
        }
        return result;
    }

    @RequestMapping("audit")
    @ResponseBody
    @RequiredPermisson("入库单审核")
    public JsonResult audit(Long id) {
        JsonResult result = new JsonResult();
        try {
            if (id != null) {
                service.audit(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("审核错误");
        }
        return result;
    }

    @RequestMapping("viewBill")
    public String viewBill(Long id, Model model) {
        if (id != null) {
            StockIncomeBill stockIncomeBill = service.selectByPrimaryKey(id);
            model.addAttribute("stockIncomeBill", stockIncomeBill);
        }
        return "stockIncomeBill/viewList";
    }
}
