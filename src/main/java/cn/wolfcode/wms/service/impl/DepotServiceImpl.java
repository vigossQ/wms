package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Depot;
import cn.wolfcode.wms.mapper.DepotMapper;
import cn.wolfcode.wms.qo.QueryObject;
import cn.wolfcode.wms.service.IDepotService;
import cn.wolfcode.wms.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepotServiceImpl implements IDepotService {

    @Autowired
    private DepotMapper mapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);
        return 0;
    }

    @Override
    public int insert(Depot record) {
        mapper.insert(record);
        return 0;
    }

    @Override
    public Depot selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Depot> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Depot record) {
        mapper.updateByPrimaryKey(record);
        return 0;
    }

    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = mapper.queryForCount(qo);
        if (totalCount == 0){
            return PageResult.EMPTY_PAGE;
        }
        List<Depot> data = mapper.queryForList(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),totalCount,data);
    }
}
