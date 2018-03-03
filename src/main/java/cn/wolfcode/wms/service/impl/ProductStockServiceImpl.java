package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.*;
import cn.wolfcode.wms.mapper.ProductStockMapper;
import cn.wolfcode.wms.mapper.SaleAccountMapper;
import cn.wolfcode.wms.qo.QueryObject;
import cn.wolfcode.wms.service.IProductStockService;
import cn.wolfcode.wms.utils.LogicException;
import cn.wolfcode.wms.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class ProductStockServiceImpl implements IProductStockService {

    @Autowired
    private ProductStockMapper productStockMapper;
    @Autowired
    private SaleAccountMapper saleAccountMapper;

    //入库审核
    @Override
    public void stockIncome(StockIncomeBill currentBill) {
        List<StockIncomeBillItem> items = currentBill.getItems();
        for (StockIncomeBillItem item : items) {
            Long depotId = currentBill.getDepot().getId();
            Long productId = item.getProduct().getId();
            ProductStock ps = productStockMapper.selectByProductIdAndDepotId(productId, depotId);

            if (ps != null) {
                //设置总数量
                BigDecimal storeNumber = ps.getStoreNumber().add(item.getNumber());
                ps.setStoreNumber(storeNumber);
                //设置总额
                BigDecimal amount = ps.getAmount().add(item.getAmount());
                ps.setAmount(amount);
                //设置价格
                ps.setPrice(amount.divide(storeNumber,2,BigDecimal.ROUND_HALF_UP));
                //更新记录
                productStockMapper.updateByPrimaryKey(ps);
            } else {
                //保存新记录
                ps = new ProductStock();
                ps.setAmount(item.getAmount());
                ps.setStoreNumber(item.getNumber());
                ps.setPrice(item.getCostPrice());
                ps.setDepot(currentBill.getDepot());
                ps.setProduct(item.getProduct());
                productStockMapper.insert(ps);
            }
        }
    }

    //出库审核
    @Override
    public void stockOutcome(StockOutcomeBill currentBill) {
        List<StockOutcomeBillItem> items = currentBill.getItems();
        for (StockOutcomeBillItem item : items) {
            Long productId = item.getProduct().getId();
            Long depotId = currentBill.getDepot().getId();
            ProductStock ps = productStockMapper.selectByProductIdAndDepotId(productId, depotId);
            //库存不存在
            if (ps == null) {
                throw new LogicException("抱歉,[" + item.getProduct().getName() + "]商品不存在");
            }
            //库存数量不够
            if (item.getNumber().compareTo(ps.getStoreNumber()) > 0) {
                throw new LogicException("抱歉,[" + item.getProduct().getName() + "]商品的数量[" + ps.getStoreNumber() + "]不足[" + item.getNumber() + "]");
            }
            //符合条件,修改库存
            ps.setStoreNumber(ps.getStoreNumber().subtract(item.getNumber()));
            ps.setAmount(ps.getPrice().multiply(ps.getStoreNumber()));
            productStockMapper.insert(ps);

            //销售进账
            SaleAccount saleAccount = new SaleAccount();
            saleAccount.setProduct(item.getProduct());
            saleAccount.setClient(currentBill.getClient());
            saleAccount.setSaleman(currentBill.getInputUser());
            saleAccount.setVdate(new Date());
            saleAccount.setNumber(item.getNumber());
            saleAccount.setSalePrice(item.getSalePrice());
            saleAccount.setSaleAmount(item.getAmount());
            saleAccount.setCostPrice(ps.getPrice());
            saleAccount.setCostAmount(ps.getPrice().multiply(item.getNumber()));
            saleAccountMapper.insert(saleAccount);
        }
    }

    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = productStockMapper.queryForCount(qo);
        if (totalCount == 0){
            return PageResult.EMPTY_PAGE;
        }
        List<ProductStock> data = productStockMapper.queryForList(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),totalCount,data);
    }
}
