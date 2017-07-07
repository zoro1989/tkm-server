package com.ccbjb.service.permission.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccbjb.common.dao.ISysPermissionDao;
import com.ccbjb.common.dao.ISysRolePermissionDao;
import com.ccbjb.common.dao.ISysUserDao;
import com.ccbjb.common.dao.ISysUserRoleDao;
import com.ccbjb.common.entity.SysPermission;
import com.ccbjb.common.entity.SysRolePermission;
import com.ccbjb.common.mybatis.BaseMybatisService;
import com.ccbjb.common.mybatis.page.Pagination;
import com.ccbjb.common.utils.LoggerUtils;
import com.ccbjb.common.utils.StringUtils;
import com.ccbjb.model.TKMBaseModel;
import com.ccbjb.model.permission.SysPermissionModel;
import com.ccbjb.service.permission.IPermissionService;

@Service
public class PermissionServiceImpl extends BaseMybatisService<ISysPermissionDao> implements IPermissionService {

	@Autowired
	ISysPermissionDao permissionMapper;
	@Autowired
	ISysUserDao userMapper;
	@Autowired
	ISysRolePermissionDao rolePermissionMapper;
	@Autowired
	ISysUserRoleDao userRoleMapper;

	@Transactional
	public int deleteByPrimaryKey(Long id) {
		return permissionMapper.deleteByPrimaryKey(id);
	}

	@Transactional
	public SysPermission insert(SysPermission record) {
		permissionMapper.insert(record);
		return record;
	}

	@Transactional
	public SysPermission insertSelective(SysPermission record) {
		//添加权限
		permissionMapper.insertSelective(record);
		//每添加一个权限，都往【系统管理员 	SYS_ADMIN】里添加一次。保证系统管理员有最大的权限
		executePermission(new Long(1), new Long[]{record.getId()});
		return record;
	}

	public SysPermission selectByPrimaryKey(Long id) {
		return permissionMapper.selectByPrimaryKey(id);
	}

	@Transactional
	public int updateByPrimaryKey(SysPermission record) {
		return permissionMapper.updateByPrimaryKey(record);
	}

	@Transactional
	public int updateByPrimaryKeySelective(SysPermission record) {
		return permissionMapper.updateByPrimaryKeySelective(record);
	}

	@Transactional
	public TKMBaseModel deletePermissionById(Long[] ids) {
		TKMBaseModel model = new TKMBaseModel();
		try {
			int successCount=0,errorCount=0;
			String resultMsg ="删除%s条，失败%s条";
			
			for (Long id : ids) {
				
				List<SysRolePermission> rolePermissions= rolePermissionMapper.findRolePermissionByPid(id);
				if(null != rolePermissions && rolePermissions.size() > 0){
					successCount+=this.deleteByPrimaryKey(id);
				}else{
					errorCount ++;
				}
			}
			//如果有成功的，也有失败的，提示清楚。
			if(errorCount > 0){
				resultMsg = String.format(resultMsg, successCount,errorCount);
			}else{
				resultMsg = "操作成功";
			}
			model.setResultCode(100);
			model.setResultData(resultMsg);
		} catch (Exception e) {
			LoggerUtils.fmtError(getClass(), e, "根据IDS删除用户出现错误，ids[%s]", ids);
			model.setResultCode(500);
			model.setResultData("删除出现错误，请刷新后再试！");
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Pagination<SysPermission> findPage(Map<String,Object> resultMap, Integer pageNo,
											  Integer pageSize) {
		return super.findPage(resultMap, pageNo, pageSize);
	}

	public List<SysPermissionModel> selectPermissionById(Long id) {
		return permissionMapper.selectPermissionById(id);
	}

	@Transactional
	public TKMBaseModel addPermission2Role(Long roleId, Long[] ids) {
		//先删除原有的。
		rolePermissionMapper.deleteByRid(roleId);
		return executePermission(roleId, ids);
	}
	/**
	 * 处理权限 
	 * @param roleId
	 * @param ids
	 * @return
	 */
	@Transactional
	private TKMBaseModel executePermission(Long roleId, Long[] ids){
		TKMBaseModel model = new TKMBaseModel();
		int count = 0;
		try {
			//如果ids,permission 的id 有值，那么就添加。没值象征着：把这个角色（roleId）所有权限取消。
			if(ids.length>0){
				//添加新的。
				for (Long id : ids) {
					SysRolePermission entity = new SysRolePermission(roleId,id);
					count += rolePermissionMapper.insertSelective(entity);
				}
			}
			model.setResultCode(100);
			model.setResultData("操作清空"+count+"个权限！");
		} catch (Exception e) {
			model.setResultCode(500);
			model.setResultData("操作失败，请重试！");
		}
		//清空拥有角色Id为：roleId 的用户权限已加载数据，让权限数据重新加载
		List<Long> userIds = userRoleMapper.findUserIdByRoleId(roleId);
		//TODO session未持久化 不能清空用户权限
		//TokenManager.clearUserAuthByUserId(userIds);
		return model;
		
	}

	@Transactional
	public TKMBaseModel deleteByRids(Long[] roleIds) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		TKMBaseModel model = new TKMBaseModel();
		try {
			if(StringUtils.isNotBlank(roleIds)){
				StringBuilder sb = new StringBuilder();
				for (Long id : roleIds) {
					sb.append(id);
					sb.append(",");
				}
				String roleIdsSbString = sb.toString();
				resultMap.put("roleIds", roleIdsSbString.substring(0, roleIdsSbString.length()-1));
				
				rolePermissionMapper.deleteByRids(resultMap);
				model.setResultCode(100);
				model.setResultData("操作成功");
			}
		} catch (Exception e) {
			model.setResultCode(500);
			model.setResultData("操作失败，请重试！");
		}
		return model;
	}

	public Set<String> findPermissionByUserId(Long userId) {
		return permissionMapper.findPermissionByUserId(userId);
	}

	
}
