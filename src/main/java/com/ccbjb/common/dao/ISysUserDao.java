package com.ccbjb.common.dao;

import java.util.List;
import java.util.Map;

import com.ccbjb.common.entity.SysUser;
import com.ccbjb.model.permission.SysRoleModel;

public interface ISysUserDao {
    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser login(Map<String, Object> map);

    SysUser findUserByEmail(String email);

	List<SysRoleModel> selectRoleByUserId(Long id);

}