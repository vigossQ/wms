package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.StockIncomeBill;
import cn.wolfcode.wms.qo.QueryObject;

import java.util.List;

public interface StockIncomeBillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StockIncomeBill record);

    StockIncomeBill selectByPrimaryKey(Long id);

    List<StockIncomeBill> selectAll();

    int updateByPrimaryKey(StockIncomeBill record);

    int queryForCount(QueryObject qo);

    List<StockIncomeBill> queryForList(QueryObject qo);

    void audit(StockIncomeBill currentBill);
}