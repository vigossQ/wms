package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.OrderBillItem;

public interface OrderBillItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderBillItem record);

    void deleteByBillId(Long billId);
}