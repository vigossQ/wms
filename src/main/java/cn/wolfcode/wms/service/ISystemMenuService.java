package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.SystemMenu;
import cn.wolfcode.wms.qo.SystemMenuQueryObject;
import cn.wolfcode.wms.utils.PageResult;

import java.util.List;
import java.util.Map;

public interface ISystemMenuService {
    int deleteByPrimaryKey(Long id);

    int insert(SystemMenu record);

    SystemMenu selectByPrimaryKey(Long id);

    List<SystemMenu> selectAll();

    int updateByPrimaryKey(SystemMenu record);

    PageResult query(SystemMenuQueryObject qo);

    List<Map<String,Object>> loadMenuByParentSn(String parentSn);
}
