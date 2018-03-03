package cn.wolfcode.wms.service;

import cn.wolfcode.wms.qo.QueryObject;

import java.util.List;
import java.util.Map;

public interface IChartsService {
    List<Map<String, Object>> queryForOrder(QueryObject qo);
    List<Map<String, Object>> queryForSale(QueryObject qo);
}
