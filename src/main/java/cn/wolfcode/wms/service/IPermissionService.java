package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Permission;
import cn.wolfcode.wms.qo.QueryObject;
import cn.wolfcode.wms.utils.PageResult;

import java.util.List;

public interface IPermissionService {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    List<Permission> selectAll();

    PageResult query(QueryObject qo);

    void reload();
}
