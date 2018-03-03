package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Product;
import cn.wolfcode.wms.qo.QueryObject;
import cn.wolfcode.wms.utils.PageResult;

import java.util.List;

public interface IProductService {
    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    Product selectByPrimaryKey(Long id);

    List<Product> selectAll();

    int updateByPrimaryKey(Product record);

    PageResult query(QueryObject qo);
}
