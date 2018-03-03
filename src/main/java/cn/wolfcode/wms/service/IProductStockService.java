package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.StockIncomeBill;
import cn.wolfcode.wms.domain.StockOutcomeBill;
import cn.wolfcode.wms.qo.QueryObject;
import cn.wolfcode.wms.utils.PageResult;

public interface IProductStockService {
    void stockIncome(StockIncomeBill currentBill);

    void stockOutcome(StockOutcomeBill currentBill);

    PageResult query(QueryObject qo);
}
