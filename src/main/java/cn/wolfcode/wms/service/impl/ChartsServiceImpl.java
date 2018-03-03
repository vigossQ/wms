package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.mapper.ChartMapper;
import cn.wolfcode.wms.qo.QueryObject;
import cn.wolfcode.wms.service.IChartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ChartsServiceImpl implements IChartsService {
    @Autowired
    private ChartMapper mapper;

    @Override
    public List<Map<String, Object>> queryForOrder(QueryObject qo) {
        return mapper.queryForOrder(qo);
    }

    @Override
    public List<Map<String, Object>> queryForSale(QueryObject qo) {
        return mapper.queryForSale(qo);
    }
}
