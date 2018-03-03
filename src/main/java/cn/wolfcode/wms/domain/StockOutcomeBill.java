package cn.wolfcode.wms.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StockOutcomeBill {
    //0:待审核   1:已审核
    public static final int STATUS_NORMAL = 0;
    public static final int STATUS_AUDIT = 1;

    private Long id;
    private String sn;//订单编码
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date vdate;//业务时间
    //状态
    private int status = STATUS_NORMAL;
    private BigDecimal totalAmount;//总金额
    private BigDecimal totalNumber;//总数量
    //审核时间
    private Date auditTime;
    //提交时间
    private Date inputTime;
    //录入人
    private Employee inputUser;
    //审核人
    private Employee auditor;
    //仓库
    private Depot depot;
    //客户
    private Client client;
    //关联明细,一对多
    private List<StockOutcomeBillItem> items = new ArrayList<>();
}
