package com.ccbjb.common.dao;

import com.ccbjb.common.entity.SysUserRole;

import java.util.List;
import java.util.Map;


public interface ISysUserRoleDao {
    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

	int deleteByUserId(Long id);

	int deleteRoleByUserIds(Map<String, Object> resultMap);

	List<Long> findUserIdByRoleId(Long id);
}