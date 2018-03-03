package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.domain.SystemMenu;
import cn.wolfcode.wms.mapper.SystemMenuMapper;
import cn.wolfcode.wms.qo.SystemMenuQueryObject;
import cn.wolfcode.wms.service.ISystemMenuService;
import cn.wolfcode.wms.utils.PageResult;
import cn.wolfcode.wms.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SystemMenuServiceImpl implements ISystemMenuService {

    @Autowired
    private SystemMenuMapper mapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);
        return 0;
    }

    @Override
    public int insert(SystemMenu record) {
        mapper.insert(record);
        return 0;
    }

    @Override
    public SystemMenu selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SystemMenu> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(SystemMenu record) {
        mapper.updateByPrimaryKey(record);
        return 0;
    }

    @Override
    public PageResult query(SystemMenuQueryObject qo) {
        int totalCount = mapper.queryForCount(qo);
        if (totalCount == 0) {
            return PageResult.EMPTY_PAGE;
        }
        List<SystemMenu> data = mapper.queryForList(qo);
        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), totalCount, data);
    }

    @Override
    public List<Map<String, Object>> loadMenuByParentSn(String parentSn) {
        Employee currentEmp = UserContext.getEmployee();
        if (currentEmp.isAdmin()) {
            return mapper.loadMenuByParentSn(parentSn);
        } else {
            return mapper.loadMenuByParentSnAndEmpId(parentSn,currentEmp.getId());
        }
    }
}
