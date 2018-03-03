package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.SystemMenu;
import cn.wolfcode.wms.qo.SystemMenuQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SystemMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemMenu record);

    SystemMenu selectByPrimaryKey(Long id);

    List<SystemMenu> selectAll();

    int updateByPrimaryKey(SystemMenu record);

    int queryForCount(SystemMenuQueryObject qo);

    List<SystemMenu> queryForList(SystemMenuQueryObject qo);

    List<Map<String,Object>> loadMenuByParentSn(String parentSn);

    List<Map<String,Object>> loadMenuByParentSnAndEmpId(@Param("parentSn") String parentSn, @Param("empId") Long empId);
}