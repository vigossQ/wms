package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Supplier;
import cn.wolfcode.wms.qo.QueryObject;
import cn.wolfcode.wms.utils.PageResult;

import java.util.List;

public interface ISupplierService {
    int deleteByPrimaryKey(Long id);

    int insert(Supplier record);

    Supplier selectByPrimaryKey(Long id);

    List<Supplier> selectAll();

    int updateByPrimaryKey(Supplier record);

    PageResult query(QueryObject qo);
}
