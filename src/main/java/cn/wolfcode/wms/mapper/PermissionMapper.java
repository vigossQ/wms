package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.Permission;
import cn.wolfcode.wms.qo.QueryObject;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    List<Permission> selectAll();

    int queryForCount(QueryObject qo);

    List<Permission> queryForList(QueryObject qo);

    List<String> selectAllExpressions();
}