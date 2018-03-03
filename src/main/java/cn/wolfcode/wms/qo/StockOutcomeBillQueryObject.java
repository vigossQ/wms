package cn.wolfcode.wms.qo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockOutcomeBillQueryObject extends BillQueryObject {
    private long depotId = -1L;
    private long clientId = -1L;
}
