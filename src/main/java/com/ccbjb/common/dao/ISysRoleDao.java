package com.ccbjb.common.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ccbjb.common.entity.SysRole;

public interface ISysRoleDao {
    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

	Set<String> findRoleByUserId(Long id);

	List<SysRole> findNowAllPermission(Map<String, Object> map);
	
	void initData();
}