package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Depot;
import cn.wolfcode.wms.qo.QueryObject;
import cn.wolfcode.wms.utils.PageResult;

import java.util.List;

public interface IDepotService {
    int deleteByPrimaryKey(Long id);

    int insert(Depot record);

    Depot selectByPrimaryKey(Long id);

    List<Depot> selectAll();

    int updateByPrimaryKey(Depot record);

    PageResult query(QueryObject qo);
}
