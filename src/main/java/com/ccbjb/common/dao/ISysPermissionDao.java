package com.ccbjb.common.dao;

import java.util.List;
import java.util.Set;

import com.ccbjb.common.entity.SysPermission;
import com.ccbjb.model.permission.SysPermissionModel;

public interface ISysPermissionDao {
    int deleteByPrimaryKey(Long id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);

	List<SysPermissionModel> selectPermissionById(Long id);
	//根据用户ID获取权限的Set集合
	Set<String> findPermissionByUserId(Long id);
}