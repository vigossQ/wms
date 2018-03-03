package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.StockIncomeBill;
import cn.wolfcode.wms.domain.StockIncomeBillItem;
import cn.wolfcode.wms.mapper.StockIncomeBillItemMapper;
import cn.wolfcode.wms.mapper.StockIncomeBillMapper;
import cn.wolfcode.wms.qo.QueryObject;
import cn.wolfcode.wms.service.IProductStockService;
import cn.wolfcode.wms.service.IStockIncomeBillService;
import cn.wolfcode.wms.utils.PageResult;
import cn.wolfcode.wms.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StockIncomeBillServiceImpl implements IStockIncomeBillService {

    @Autowired
    private StockIncomeBillMapper mapper;
    @Autowired
    private StockIncomeBillItemMapper billItemMapper;
    @Autowired
    private IProductStockService productStockService;

    @Override
    public int deleteByPrimaryKey(Long id) {
        billItemMapper.deleteByBillId(id);
        mapper.deleteByPrimaryKey(id);
        return 0;
    }

    @Override
    public int insert(StockIncomeBill record) {
        //设置状态值
        record.setStatus(StockIncomeBill.STATUS_NORMAL);

        //总数量和总额
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalNumber = BigDecimal.ZERO;
        for (StockIncomeBillItem item : record.getItems()) {
            totalAmount = totalAmount.add(item.getCostPrice().multiply(item.getNumber()));
            totalNumber = totalNumber.add(item.getNumber());
        }
        record.setTotalAmount(totalAmount);
        record.setTotalNumber(totalNumber);
        //录入时间在SQL中使用now()

        //当前用户
        record.setInputUser(UserContext.getEmployee());
        mapper.insert(record);

        //保存明细,执行在保存订单之后才能获取自动生成的主键
        for (StockIncomeBillItem item : record.getItems()) {
            item.setBillId(record.getId());
            item.setAmount(item.getCostPrice().multiply(item.getNumber()));
            billItemMapper.insert(item);
        }

        return 0;
    }

    @Override
    public StockIncomeBill selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<StockIncomeBill> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(StockIncomeBill record) {
        StockIncomeBill currentBill = mapper.selectByPrimaryKey(record.getId());
        if (currentBill.getStatus() == StockIncomeBill.STATUS_NORMAL) {
            //先根据订单id删除该订单所有明细
            billItemMapper.deleteByBillId(record.getId());

            //总数量和总额
            BigDecimal totalAmount = BigDecimal.ZERO;
            BigDecimal totalNumber = BigDecimal.ZERO;
            for (StockIncomeBillItem item : record.getItems()) {
                totalAmount = totalAmount.add(item.getCostPrice().multiply(item.getNumber()));
                totalNumber = totalNumber.add(item.getNumber());
            }
            record.setTotalAmount(totalAmount);
            record.setTotalNumber(totalNumber);

            //保存明细
            for (StockIncomeBillItem item : record.getItems()) {
                item.setBillId(record.getId());
                item.setAmount(item.getCostPrice().multiply(item.getNumber()));
                billItemMapper.insert(item);
            }
        }
        mapper.updateByPrimaryKey(record);
        return 0;
    }

    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = mapper.queryForCount(qo);
        if (totalCount == 0) {
            return PageResult.EMPTY_PAGE;
        }
        List<StockIncomeBill> data = mapper.queryForList(qo);
        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), totalCount, data);
    }

    @Override
    public void audit(Long id) {
        StockIncomeBill currentBill = mapper.selectByPrimaryKey(id);
        if (currentBill.getStatus() == StockIncomeBill.STATUS_NORMAL) {
            //设置状态
            currentBill.setStatus(StockIncomeBill.STATUS_AUDIT);
            //审核人
            currentBill.setAuditor(UserContext.getEmployee());
            mapper.audit(currentBill);

            //设置库存信息
            productStockService.stockIncome(currentBill);
        }
    }
}
