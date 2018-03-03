package cn.wolfcode.wms.qo;

import cn.wolfcode.wms.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
public class ChartQueryObject extends QueryObject {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private long supplierId = -1L;
    private long clientId = -1L;
    private long brandId = -1L;
    private String groupByType = "inputUser.name";
    private String groupBySaleType = "saleman.name";

    public static Map<String,String> groupByTypes = new LinkedHashMap<>();
    static {
        groupByTypes.put("inputUser.name","订货人员");
        groupByTypes.put("p.name","商品名称");
        groupByTypes.put("s.name","供应商");
        groupByTypes.put("p.brand_name","品牌");
        groupByTypes.put("DATE_FORMAT(bill.vdate, '%Y-%m')","订货日期(月)");
        groupByTypes.put("DATE_FORMAT(bill.vdate, '%Y-%m-%d')","订货日期(日)");
    }

    public static Map<String,String> groupBySaleTypes = new LinkedHashMap<>();
    static {
        groupBySaleTypes.put("saleman.name","销售人员");
        groupBySaleTypes.put("p.name","商品名称");
        groupBySaleTypes.put("c.name","客户");
        groupBySaleTypes.put("p.brand_name","品牌");
        groupBySaleTypes.put("DATE_FORMAT(sale.vdate, '%Y-%m')","销售日期(月)");
        groupBySaleTypes.put("DATE_FORMAT(sale.vdate, '%Y-%m-%d')","销售日期(日)");
    }

    public Date getEndDate() {
        if (endDate != null) {
            return DateUtil.getEndDate(endDate);
        } else {
            return null;
        }
    }

}
