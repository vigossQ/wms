package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.OrderBill;
import cn.wolfcode.wms.domain.OrderBillItem;
import cn.wolfcode.wms.mapper.OrderBillItemMapper;
import cn.wolfcode.wms.mapper.OrderBillMapper;
import cn.wolfcode.wms.qo.QueryObject;
import cn.wolfcode.wms.service.IOrderBillService;
import cn.wolfcode.wms.utils.PageResult;
import cn.wolfcode.wms.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderBillServiceImpl implements IOrderBillService {

    @Autowired
    private OrderBillMapper mapper;
    @Autowired
    private OrderBillItemMapper billItemMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        billItemMapper.deleteByBillId(id);
        mapper.deleteByPrimaryKey(id);
        return 0;
    }

    @Override
    public int insert(OrderBill record) {
        //设置状态值
        record.setStatus(OrderBill.STATUS_NORMAL);

        //总数量和总额
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalNumber = BigDecimal.ZERO;
        for (OrderBillItem item : record.getItems()) {
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
        for (OrderBillItem item : record.getItems()) {
            item.setBillId(record.getId());
            item.setAmount(item.getCostPrice().multiply(item.getNumber()));
            billItemMapper.insert(item);
        }

        return 0;
    }

    @Override
    public OrderBill selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<OrderBill> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(OrderBill record) {
        OrderBill currentBill = mapper.selectByPrimaryKey(record.getId());
        if (currentBill.getStatus() == OrderBill.STATUS_NORMAL) {
            //先根据订单id删除该订单所有明细
            billItemMapper.deleteByBillId(record.getId());

            //总数量和总额
            BigDecimal totalAmount = BigDecimal.ZERO;
            BigDecimal totalNumber = BigDecimal.ZERO;
            for (OrderBillItem item : record.getItems()) {
                totalAmount = totalAmount.add(item.getCostPrice().multiply(item.getNumber()));
                totalNumber = totalNumber.add(item.getNumber());
            }
            record.setTotalAmount(totalAmount);
            record.setTotalNumber(totalNumber);

            //保存明细
            for (OrderBillItem item : record.getItems()) {
                item.setBillId(record.getId());
                item.setAmount(item.getCostPrice().multiply(item.getNumber()));
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
        List<OrderBill> data = mapper.queryForList(qo);
        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), totalCount, data);
    }

    @Override
    public void audit(Long id) {
        OrderBill currentBill = mapper.selectByPrimaryKey(id);
        if (currentBill.getStatus() == OrderBill.STATUS_NORMAL) {
            //设置状态
            currentBill.setStatus(OrderBill.STATUS_AUDIT);
            //审核人
            currentBill.setAuditor(UserContext.getEmployee());
            mapper.audit(currentBill);
        }
    }
}
