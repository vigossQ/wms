package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Brand;
import cn.wolfcode.wms.domain.Product;
import cn.wolfcode.wms.mapper.BrandMapper;
import cn.wolfcode.wms.mapper.ProductMapper;
import cn.wolfcode.wms.qo.QueryObject;
import cn.wolfcode.wms.service.IProductService;
import cn.wolfcode.wms.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper mapper;
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);
        return 0;
    }

    @Override
    public int insert(Product record) {
        Brand brand = brandMapper.selectByPrimaryKey(record.getBrandId());
        record.setBrandName(brand.getName());
        mapper.insert(record);
        return 0;
    }

    @Override
    public Product selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Product> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Product record) {
        Brand brand = brandMapper.selectByPrimaryKey(record.getBrandId());
        record.setBrandName(brand.getName());
        mapper.updateByPrimaryKey(record);
        return 0;
    }

    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = mapper.queryForCount(qo);
        if (totalCount == 0){
            return PageResult.EMPTY_PAGE;
        }
        List<Product> data = mapper.queryForList(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),totalCount,data);
    }
}
