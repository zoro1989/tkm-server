package com.ccbjb.service.permission;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ccbjb.common.entity.SysRole;
import com.ccbjb.common.mybatis.page.Pagination;
import com.ccbjb.model.TKMBaseModel;
import com.ccbjb.model.permission.RolePermissionAllocationModel;

public interface IRoleService {

	int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

	SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

	Pagination<SysRole> findPage(Map<String, Object> resultMap, Integer pageNo,
								 Integer pageSize);

	TKMBaseModel deleteRoleById(Long[] ids);

	Pagination<RolePermissionAllocationModel> findRoleAndPermissionPage(
            Map<String, Object> resultMap, Integer pageNo, Integer pageSize);
	//根据用户ID查询角色（role），放入到Authorization里。
	Set<String> findRoleByUserId(Long userId);

	List<SysRole> findNowAllPermission();
	//初始化数据
	void initData();
}
