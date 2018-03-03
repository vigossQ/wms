package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.OrderBill;
import cn.wolfcode.wms.qo.QueryObject;
import cn.wolfcode.wms.utils.PageResult;

import java.util.List;

public interface IOrderBillService {
    int deleteByPrimaryKey(Long id);

    int insert(OrderBill record);

    OrderBill selectByPrimaryKey(Long id);

    List<OrderBill> selectAll();

    int updateByPrimaryKey(OrderBill record);

    PageResult query(QueryObject qo);

    void audit(Long id);

}
