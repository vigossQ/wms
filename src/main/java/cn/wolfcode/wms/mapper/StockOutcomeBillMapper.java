package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.StockOutcomeBill;
import cn.wolfcode.wms.qo.QueryObject;

import java.util.List;

public interface StockOutcomeBillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StockOutcomeBill record);

    StockOutcomeBill selectByPrimaryKey(Long id);

    List<StockOutcomeBill> selectAll();

    int updateByPrimaryKey(StockOutcomeBill record);

    int queryForCount(QueryObject qo);

    List<StockOutcomeBill> queryForList(QueryObject qo);

    void audit(StockOutcomeBill currentBill);
}