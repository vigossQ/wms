package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Role;
import cn.wolfcode.wms.mapper.RoleMapper;
import cn.wolfcode.wms.qo.QueryObject;
import cn.wolfcode.wms.service.IRoleService;
import cn.wolfcode.wms.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper mapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);
        mapper.deleteRolePermissionRelation(id);
        mapper.deleteRoleSystemMenuRelation(id);
        return 0;
    }

    @Override
    public int insert(Role record, Long[] permissionIds, Long[] systemMenuIds) {
        mapper.insert(record);
        if (permissionIds != null) {
            for (Long permissionId : permissionIds) {
                mapper.saveRoleSystemMenuRelation(record.getId(), permissionId);
            }
        }
        if (systemMenuIds != null) {
            for (Long systemMenuId : systemMenuIds) {
                mapper.saveRoleSystemMenuRelation(record.getId(), systemMenuId);
            }
        }
        return 0;
    }

    @Override
    public Role selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Role record, Long[] permissionIds, Long[] systemMenuIds) {
        mapper.updateByPrimaryKey(record);
        mapper.deleteRolePermissionRelation(record.getId());
        mapper.deleteRoleSystemMenuRelation(record.getId());
        if (permissionIds != null) {
            for (Long permissionId : permissionIds) {
                mapper.saveRolePermissionRelation(record.getId(), permissionId);
            }
        }
        if (systemMenuIds != null) {
            for (Long systemMenuId : systemMenuIds) {
                mapper.saveRoleSystemMenuRelation(record.getId(), systemMenuId);
            }
        }
        return 0;
    }

    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = mapper.queryForCount(qo);
        if (totalCount == 0) {
            return PageResult.EMPTY_PAGE;
        }
        List<Role> data = mapper.queryForList(qo);
        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), totalCount, data);
    }
}
