package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Role;
import cn.wolfcode.wms.qo.QueryObject;
import cn.wolfcode.wms.utils.PageResult;

import java.util.List;

public interface IRoleService {
    int deleteByPrimaryKey(Long id);

    int insert(Role record, Long[] permissionIds, Long[] systemMenuIds);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record, Long[] permissionIds, Long[] systemMenuIds);

    PageResult query(QueryObject qo);
}
