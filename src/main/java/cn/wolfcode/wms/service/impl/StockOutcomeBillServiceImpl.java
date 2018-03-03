package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.StockIncomeBill;
import cn.wolfcode.wms.domain.StockOutcomeBill;
import cn.wolfcode.wms.domain.StockOutcomeBillItem;
import cn.wolfcode.wms.mapper.StockOutcomeBillItemMapper;
import cn.wolfcode.wms.mapper.StockOutcomeBillMapper;
import cn.wolfcode.wms.qo.QueryObject;
import cn.wolfcode.wms.service.IProductStockService;
import cn.wolfcode.wms.service.IStockOutcomeBillService;
import cn.wolfcode.wms.utils.PageResult;
import cn.wolfcode.wms.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StockOutcomeBillServiceImpl implements IStockOutcomeBillService {

    @Autowired
    private StockOutcomeBillMapper mapper;
    @Autowired
    private StockOutcomeBillItemMapper billItemMapper;
    @Autowired
    private IProductStockService productStockService;

    @Override
    public int deleteByPrimaryKey(Long id) {
        billItemMapper.deleteByBillId(id);
        mapper.deleteByPrimaryKey(id);
        return 0;
    }

    @Override
    public int insert(StockOutcomeBill record) {
        //设置状态值
        record.setStatus(StockOutcomeBill.STATUS_NORMAL);

        //总数量和总额
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalNumber = BigDecimal.ZERO;
        for (StockOutcomeBillItem item : record.getItems()) {
            totalAmount = totalAmount.add(item.getSalePrice().multiply(item.getNumber()));
            totalNumber = totalNumber.add(item.getNumber());
        }
        record.setTotalAmount(totalAmount);
        record.setTotalNumber(totalNumber);
        //录入时间在SQL中使用now()

        //当前用户
        record.setInputUser(UserContext.getEmployee());
        mapper.insert(record);

        //保存明细,执行在保存订单之后才能获取自动生成的主键
        for (StockOutcomeBillItem item : record.getItems()) {
            item.setBillId(record.getId());
            item.setAmount(item.getSalePrice().multiply(item.getNumber()));
            billItemMapper.insert(item);
        }

        return 0;
    }

    @Override
    public StockOutcomeBill selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<StockOutcomeBill> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(StockOutcomeBill record) {
        StockOutcomeBill currentBill = mapper.selectByPrimaryKey(record.getId());
        if (currentBill.getStatus() == StockIncomeBill.STATUS_NORMAL) {
            //先根据订单id删除该订单所有明细
            billItemMapper.deleteByBillId(record.getId());

            //总数量和总额
            BigDecimal totalAmount = BigDecimal.ZERO;
            BigDecimal totalNumber = BigDecimal.ZERO;
            for (StockOutcomeBillItem item : record.getItems()) {
                totalAmount = totalAmount.add(item.getSalePrice().multiply(item.getNumber()));
                totalNumber = totalNumber.add(item.getNumber());
            }
            record.setTotalAmount(totalAmount);
            record.setTotalNumber(totalNumber);

            //保存明细
            for (StockOutcomeBillItem item : record.getItems()) {
                item.setBillId(record.getId());
                item.setAmount(item.getSalePrice().multiply(item.getNumber()));
                billItemMapper.insert(item);
            }

            mapper.updateByPrimaryKey(record);
        }
        return 0;
    }

    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = mapper.queryForCount(qo);
        if (totalCount == 0) {
            return PageResult.EMPTY_PAGE;
        }
        List<StockOutcomeBill> data = mapper.queryForList(qo);
        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), totalCount, data);
    }

    @Override
    public void audit(Long id) {
        StockOutcomeBill currentBill = mapper.selectByPrimaryKey(id);
        if (currentBill.getStatus() == StockOutcomeBill.STATUS_NORMAL) {
            //设置状态
            currentBill.setStatus(StockOutcomeBill.STATUS_AUDIT);
            //审核人
            currentBill.setAuditor(UserContext.getEmployee());
            mapper.audit(currentBill);

            //修改库存操作
            productStockService.stockOutcome(currentBill);
        }
    }
}
