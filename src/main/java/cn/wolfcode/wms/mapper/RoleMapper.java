package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.Role;
import cn.wolfcode.wms.qo.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    int queryForCount(QueryObject qo);

    List<Role> queryForList(QueryObject qo);

    void deleteRolePermissionRelation(Long roleId);

    void saveRolePermissionRelation(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    void saveRoleSystemMenuRelation(@Param("roleId")Long roleId, @Param("systemMenuId")Long systemMenuId);

    void deleteRoleSystemMenuRelation(Long roleId);
}