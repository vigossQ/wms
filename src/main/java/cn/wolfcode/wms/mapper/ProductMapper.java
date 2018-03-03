package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.Product;
import cn.wolfcode.wms.qo.QueryObject;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    Product selectByPrimaryKey(Long id);

    List<Product> selectAll();

    int updateByPrimaryKey(Product record);

    int queryForCount(QueryObject qo);

    List<Product> queryForList(QueryObject qo);
}