package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.qo.EmployeeQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    int queryForCount(EmployeeQueryObject qo);

    List<Employee> queryForList(EmployeeQueryObject qo);

    void batchDelete(Long[] ids);

    void deleteEmployeeRoleRelation(Long id);

    void saveEmployeeRoleRelation(@Param("empId") Long id, @Param("roleId") Long roleId);

    Employee login(@Param("name") String name, @Param("password") String password);

    Set<String> selectExpressionsByEmpId(Long id);
}