package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.OrderBill;
import cn.wolfcode.wms.qo.OrderBillQueryObject;
import cn.wolfcode.wms.service.IOrderBillService;
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
@RequestMapping("orderBill")
public class OrderBillController {

    @Autowired
    private IOrderBillService service;
    @Autowired
    private ISupplierService supplierService;

    @RequestMapping("list")
    @RequiredPermisson("订单列表")
    public String list(Model model, @ModelAttribute("qo") OrderBillQueryObject qo) {
        model.addAttribute("result", service.query(qo));
        model.addAttribute("suppliers", supplierService.selectAll());
        return "orderBill/list";
    }

    @RequestMapping("delete")
    @ResponseBody
    @RequiredPermisson("订单删除")
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
    @RequiredPermisson("订单编辑")
    public String input(Long id, Model model) {
        if (id != null) {
            OrderBill orderBill = service.selectByPrimaryKey(id);
            model.addAttribute("orderBill", orderBill);
        }
        model.addAttribute("suppliers", supplierService.selectAll());
        return "orderBill/input";
    }

    @RequestMapping("saveOrUpdate")
    @RequiredPermisson("订单保存或更新")
    @ResponseBody
    public JsonResult saveOrUpdate(OrderBill orderBill) {
        JsonResult result = new JsonResult();
        try {
            if (orderBill.getId() != null) {
                service.updateByPrimaryKey(orderBill);
            } else {
                service.insert(orderBill);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("保存失败");
        }
        return result;
    }

    @RequestMapping("audit")
    @ResponseBody
    @RequiredPermisson("订单审核")
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
            OrderBill orderBill = service.selectByPrimaryKey(id);
            model.addAttribute("orderBill", orderBill);
        }
        return "orderBill/viewList";
    }
}
