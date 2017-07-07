package com.ccbjb.common.dao;

import java.util.List;
import java.util.Map;

import com.ccbjb.common.entity.SysRolePermission;

public interface ISysRolePermissionDao {
    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);

	List<SysRolePermission> findRolePermissionByPid(Long id);
	
	List<SysRolePermission> findRolePermissionByRid(Long id);
	
	List<SysRolePermission> find(SysRolePermission entity);
	
	int deleteByPid(Long id);
	int deleteByRid(Long id);
	int delete(SysRolePermission entity);

	int deleteByRids(Map<String, Object> resultMap);
}