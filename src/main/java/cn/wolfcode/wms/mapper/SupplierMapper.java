package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.domain.Supplier;
import cn.wolfcode.wms.qo.QueryObject;

import java.util.List;

public interface SupplierMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Supplier record);

    Supplier selectByPrimaryKey(Long id);

    List<Supplier> selectAll();

    int updateByPrimaryKey(Supplier record);

    int queryForCount(QueryObject qo);

    List<Supplier> queryForList(QueryObject qo);
}