package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.StockIncomeBill;
import cn.wolfcode.wms.qo.QueryObject;
import cn.wolfcode.wms.utils.PageResult;

import java.util.List;

public interface IStockIncomeBillService {
    int deleteByPrimaryKey(Long id);

    int insert(StockIncomeBill record);

    StockIncomeBill selectByPrimaryKey(Long id);

    List<StockIncomeBill> selectAll();

    int updateByPrimaryKey(StockIncomeBill record);

    PageResult query(QueryObject qo);

    void audit(Long id);
}
