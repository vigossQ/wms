package cn.wolfcode.wms.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderBillItem {
    private Long id;
    private BigDecimal costPrice;
    private BigDecimal number;
    private BigDecimal amount;
    private String remark;
    private Product product;
    private Long billId;//所属订单id
}
