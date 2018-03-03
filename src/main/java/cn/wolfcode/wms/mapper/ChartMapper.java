package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.qo.QueryObject;

import java.util.List;
import java.util.Map;

public interface ChartMapper {
    List<Map<String, Object>> queryForOrder(QueryObject qo);

    List<Map<String, Object>> queryForSale(QueryObject qo);
}