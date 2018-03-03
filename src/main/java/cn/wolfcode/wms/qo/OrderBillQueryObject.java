package cn.wolfcode.wms.qo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderBillQueryObject extends BillQueryObject {
    private long supplierId = -1L;
}
