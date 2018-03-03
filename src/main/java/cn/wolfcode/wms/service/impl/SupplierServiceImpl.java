package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Supplier;
import cn.wolfcode.wms.mapper.SupplierMapper;
import cn.wolfcode.wms.qo.QueryObject;
import cn.wolfcode.wms.service.ISupplierService;
import cn.wolfcode.wms.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements ISupplierService {

    @Autowired
    private SupplierMapper mapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);
        return 0;
    }

    @Override
    public int insert(Supplier record) {
        mapper.insert(record);
        return 0;
    }

    @Override
    public Supplier selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Supplier> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Supplier record) {
        mapper.updateByPrimaryKey(record);
        return 0;
    }

    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = mapper.queryForCount(qo);
        if (totalCount == 0){
            return PageResult.EMPTY_PAGE;
        }
        List<Supplier> data = mapper.queryForList(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),totalCount,data);
    }
}
