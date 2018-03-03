package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.domain.Depot;
import cn.wolfcode.wms.qo.QueryObject;

import java.util.List;

public interface DepotMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Depot record);

    Depot selectByPrimaryKey(Long id);

    List<Depot> selectAll();

    int updateByPrimaryKey(Depot record);

    int queryForCount(QueryObject qo);

    List<Depot> queryForList(QueryObject qo);
}