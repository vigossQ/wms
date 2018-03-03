package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.StockOutcomeBill;
import cn.wolfcode.wms.qo.QueryObject;
import cn.wolfcode.wms.utils.PageResult;

import java.util.List;

public interface IStockOutcomeBillService {
    int deleteByPrimaryKey(Long id);

    int insert(StockOutcomeBill record);

    StockOutcomeBill selectByPrimaryKey(Long id);

    List<StockOutcomeBill> selectAll();

    int updateByPrimaryKey(StockOutcomeBill record);

    PageResult query(QueryObject qo);

    void audit(Long id);
}
