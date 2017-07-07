package com.ccbjb.service.permission;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ccbjb.common.entity.SysPermission;
import com.ccbjb.common.mybatis.page.Pagination;
import com.ccbjb.model.TKMBaseModel;
import com.ccbjb.model.permission.SysPermissionModel;

public interface IPermissionService {

	int deleteByPrimaryKey(Long id);

	SysPermission insert(SysPermission record);

	SysPermission insertSelective(SysPermission record);

	SysPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);

	TKMBaseModel deletePermissionById(Long[] ids);

	Pagination<SysPermission> findPage(Map<String, Object> resultMap, Integer pageNo,
									   Integer pageSize);
	List<SysPermissionModel> selectPermissionById(Long id);

	TKMBaseModel addPermission2Role(Long roleId, Long[] ids);

	TKMBaseModel deleteByRids(Long[] roleIds);
	//根据用户ID查询权限（permission），放入到Authorization里。
	Set<String> findPermissionByUserId(Long userId);
}
