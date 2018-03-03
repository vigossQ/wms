package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.qo.EmployeeQueryObject;
import cn.wolfcode.wms.utils.PageResult;

import java.util.List;

public interface IEmployeeService {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record, Long[] roleIds);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record, Long[] roleIds);

    PageResult query(EmployeeQueryObject qo);

    void batchDelete(Long[] ids);

    void checkLogin(String name, String password);
}
