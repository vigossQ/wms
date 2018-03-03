package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.mapper.EmployeeMapper;
import cn.wolfcode.wms.qo.EmployeeQueryObject;
import cn.wolfcode.wms.service.IEmployeeService;
import cn.wolfcode.wms.utils.LogicException;
import cn.wolfcode.wms.utils.MD5;
import cn.wolfcode.wms.utils.PageResult;
import cn.wolfcode.wms.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeMapper mapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);
        mapper.deleteEmployeeRoleRelation(id);
        return 0;
    }

    @Override
    public int insert(Employee record, Long[] roleIds) {
        String password = MD5.encode(record.getName() + record.getPassword());
        record.setPassword(password);
        mapper.insert(record);
        if (roleIds != null) {
            for (Long roleId : roleIds) {
                mapper.saveEmployeeRoleRelation(record.getId(), roleId);
            }
        }
        return 0;
    }

    @Override
    public Employee selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Employee> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Employee record, Long[] roleIds) {
        mapper.updateByPrimaryKey(record);
        mapper.deleteEmployeeRoleRelation(record.getId());
        if (roleIds != null) {
            for (Long roleId : roleIds) {
                mapper.saveEmployeeRoleRelation(record.getId(), roleId);
            }
        }
        return 0;
    }

    @Override
    public PageResult query(EmployeeQueryObject qo) {
        int totalCount = mapper.queryForCount(qo);
        if (totalCount == 0) {
            return PageResult.EMPTY_PAGE;
        }
        List<Employee> data = mapper.queryForList(qo);
        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), totalCount, data);
    }

    @Override
    public void batchDelete(Long[] ids) {
        mapper.batchDelete(ids);
    }

    @Override
    public void checkLogin(String name, String password) {
//        password = MD5.encode(name + password);
        Employee currentEmp = mapper.login(name, password);
        if (currentEmp == null) {
            throw new LogicException("账号或密码错误");
        }
        //将当前员工放进session
        UserContext.setEmployee(currentEmp);
        //将当前员工的权限放进session
        Set<String> expressions = mapper.selectExpressionsByEmpId(currentEmp.getId());
        UserContext.setExpressions(expressions);
    }
}
