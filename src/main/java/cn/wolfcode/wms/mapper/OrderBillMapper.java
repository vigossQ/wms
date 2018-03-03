package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.OrderBill;
import cn.wolfcode.wms.qo.QueryObject;

import java.util.List;

public interface OrderBillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderBill record);

    OrderBill selectByPrimaryKey(Long id);

    List<OrderBill> selectAll();

    int updateByPrimaryKey(OrderBill record);

    int queryForCount(QueryObject qo);

    List<OrderBill> queryForList(QueryObject qo);

    void audit(OrderBill currentBill);
}