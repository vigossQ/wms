package cn.wolfcode.wms.qo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockIncomeBillQueryObject extends BillQueryObject {

    private long depotId = -1L;
}
