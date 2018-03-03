package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.qo.ChartQueryObject;
import cn.wolfcode.wms.service.IBrandService;
import cn.wolfcode.wms.service.IChartsService;
import cn.wolfcode.wms.service.IClientService;
import cn.wolfcode.wms.service.ISupplierService;
import cn.wolfcode.wms.utils.RequiredPermisson;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("chart")
public class ChartsController {

    @Autowired
    private IChartsService service;
    @Autowired
    private IBrandService brandService;
    @Autowired
    private ISupplierService supplierService;
    @Autowired
    private IClientService clientService;

    @RequestMapping("order")
    @RequiredPermisson("订货报表")
    public String order(Model model, @ModelAttribute("qo") ChartQueryObject qo) {
        model.addAttribute("charts", service.queryForOrder(qo));
        model.addAttribute("brands", brandService.selectAll());
        model.addAttribute("suppliers", supplierService.selectAll());
        model.addAttribute("groupByTypes", ChartQueryObject.groupByTypes);
        return "chart/order";
    }

    @RequestMapping("sale")
    @RequiredPermisson("销售报表")
    public String sale(Model model, @ModelAttribute("qo") ChartQueryObject qo) {
        model.addAttribute("charts", service.queryForSale(qo));
        model.addAttribute("brands", brandService.selectAll());
        model.addAttribute("clients", clientService.selectAll());
        model.addAttribute("groupBySaleTypes", ChartQueryObject.groupBySaleTypes);
        return "chart/sale";
    }

    @RequestMapping("saleChartsByBar")
    public String saleChartsByBar(Model model, @ModelAttribute("qo") ChartQueryObject qo) {
        List<Map<String, Object>> groupByTypes = service.queryForSale(qo);
        List<Object> saleAmonts = new ArrayList<>();
        List<String> groupSaleTypeNames = new ArrayList<>();
        for (Map<String, Object> groupByType : groupByTypes) {
            Object saleAmount = groupByType.get("totalAmount");
            String groupBySaleTypeName = groupByType.get("groupByTypes").toString();
            saleAmonts.add(saleAmount);
            groupSaleTypeNames.add(groupBySaleTypeName);
        }
        model.addAttribute("saleAmonts", JSON.toJSONString(saleAmonts));
        model.addAttribute("groupTypeNames",JSON.toJSONString(groupSaleTypeNames));
        model.addAttribute("groupTypeName",ChartQueryObject.groupBySaleTypes.get(qo.getGroupBySaleType()));
        return "chart/saleChartsByBar";
    }

    @RequestMapping("saleChartsByPie")
    public String saleChartsByPie(Model model, @ModelAttribute("qo") ChartQueryObject qo) {
        List<Map<String, Object>> groupByTypes = service.queryForSale(qo);
        List<String> groupSaleTypeNames = new ArrayList<>();
        List<Map<String,Object>> saleAmounts = new ArrayList<>();
        BigDecimal maxSaleAmount = BigDecimal.ZERO;
        for (Map<String, Object> groupByType : groupByTypes) {
            Object saleAmount = groupByType.get("totalAmount");
            String groupBySaleTypeName = groupByType.get("groupByTypes").toString();
            groupSaleTypeNames.add(groupBySaleTypeName);
            Map<String,Object> saleMap = new HashMap<>();
            saleMap.put("value",saleAmount);
            saleMap.put("name",groupBySaleTypeName);
            saleAmounts.add(saleMap);
            if (maxSaleAmount.compareTo(new BigDecimal(saleAmount.toString()))<0){
                maxSaleAmount = new BigDecimal(saleAmount.toString());
            }
        }
        model.addAttribute("maxSaleAmount",JSON.toJSONString(maxSaleAmount));
        model.addAttribute("saleAmounts",JSON.toJSONString(saleAmounts));
        model.addAttribute("groupTypeNames",JSON.toJSONString(groupSaleTypeNames));
        model.addAttribute("groupTypeName",ChartQueryObject.groupBySaleTypes.get(qo.getGroupBySaleType()));
        return "chart/saleChartsByPie";
    }
}
