package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.ProductStock;
import cn.wolfcode.wms.qo.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductStockMapper {
    int insert(ProductStock record);

    int updateByPrimaryKey(ProductStock record);

    ProductStock selectByProductIdAndDepotId(@Param("productId") Long productId, @Param("depotId") Long depotId);

    int queryForCount(QueryObject qo);

    List<ProductStock> queryForList(QueryObject qo);
}