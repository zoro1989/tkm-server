package com.ccbjb.service.permission.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccbjb.common.dao.ISysRoleDao;
import com.ccbjb.common.dao.ISysRolePermissionDao;
import com.ccbjb.common.dao.ISysUserDao;
import com.ccbjb.common.entity.SysRole;
import com.ccbjb.common.mybatis.BaseMybatisService;
import com.ccbjb.common.mybatis.page.Pagination;
import com.ccbjb.common.shiro.TokenManager;
import com.ccbjb.common.utils.LoggerUtils;
import com.ccbjb.model.TKMBaseModel;
import com.ccbjb.model.permission.RolePermissionAllocationModel;
import com.ccbjb.service.permission.IRoleService;

@Service
@SuppressWarnings("unchecked")
public class RoleServiceImpl extends BaseMybatisService<ISysRoleDao> implements IRoleService {

	@Autowired
	ISysRoleDao roleMapper;
	@Autowired
	ISysUserDao userMapper;
	@Autowired
	ISysRolePermissionDao rolePermissionMapper;

	@Transactional
	public int deleteByPrimaryKey(Long id) {
		return roleMapper.deleteByPrimaryKey(id);
	}

	@Transactional
	public int insert(SysRole record) {
		return roleMapper.insert(record);
	}

	@Transactional
	public int insertSelective(SysRole record) {
		return roleMapper.insertSelective(record);
	}

	public SysRole selectByPrimaryKey(Long id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	@Transactional
	public int updateByPrimaryKey(SysRole record) {
		return roleMapper.updateByPrimaryKey(record);
	}

	@Transactional
	public int updateByPrimaryKeySelective(SysRole record) {
		return roleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	@Transactional
	public Pagination<SysRole> findPage(Map<String, Object> resultMap,
										Integer pageNo, Integer pageSize) {
		return super.findPage(resultMap, pageNo, pageSize);
	}

	@Transactional
	public Pagination<RolePermissionAllocationModel> findRoleAndPermissionPage(
			Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
		return super.findPage("findRoleAndPermission", "findCount", resultMap, pageNo, pageSize);
	}

	@Transactional
	public TKMBaseModel deleteRoleById(Long[] ids) {
		TKMBaseModel model = new TKMBaseModel();
		try {
			int count=0;
			String resultMsg = "";
			
			c:for (Long id : ids) {
				if(new Long(1).equals(id)){
					resultMsg = "操作成功，But'系统管理员不能删除。";
					continue c;
				}else{
					count+=this.deleteByPrimaryKey(id);
				}
			}
			resultMsg = "成功删除"+count+"个角色！";
			model.setResultCode(100);
			model.setResultData(resultMsg);
		} catch (Exception e) {
			LoggerUtils.fmtError(getClass(), e, "根据IDS删除用户出现错误，ids[%s]", ids);
			model.setResultCode(500);
			model.setErrorMessage("删除出现错误，请刷新后再试！");
		}
		return model;
	}

	public Set<String> findRoleByUserId(Long userId) {
		return roleMapper.findRoleByUserId(userId);
	}

	public List<SysRole> findNowAllPermission() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", TokenManager.getUserId());
		return roleMapper.findNowAllPermission(map);
	}
	/**
	 * 每20分钟执行一次
	 */
	public void initData() {
		roleMapper.initData();
	}
	
}
