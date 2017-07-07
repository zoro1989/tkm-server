package com.ccbjb.controller.permission;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ccbjb.common.mybatis.page.Pagination;
import com.ccbjb.controller.common.BaseController;
import com.ccbjb.model.TKMBaseModel;
import com.ccbjb.model.permission.RolePermissionAllocationModel;
import com.ccbjb.model.permission.SysPermissionModel;
import com.ccbjb.service.permission.IPermissionService;
import com.ccbjb.service.permission.IRoleService;

/**
 * 用户权限分配
 */
@Controller
@Scope(value="prototype")
@RequestMapping("permission")
public class PermissionAllocationController extends BaseController {
	
	@Autowired
	IPermissionService permissionService;
	@Autowired
	IRoleService roleService;
	/**
	 * 权限分配
	 * @param modelMap
	 * @param pageNo
	 * @param findContent
	 * @return
	 */
	@RequestMapping(value="allocation")
	@ResponseBody
	public TKMBaseModel allocation(ModelMap modelMap,Integer pageNo,String findContent){
		modelMap.put("findContent", findContent);
		Pagination<RolePermissionAllocationModel> boPage = roleService.findRoleAndPermissionPage(modelMap,pageNo,pageSize);
		modelMap.put("page", boPage);
		TKMBaseModel model = new TKMBaseModel();
		model.setResultCode(100);
		model.setResultData(boPage);
		return model;
	}
	
	/**
	 * 根据角色ID查询权限
	 * @param id
	 * @return
	 */
	@RequestMapping(value="selectPermissionById")
	@ResponseBody
	public TKMBaseModel selectPermissionById(Long id){
		TKMBaseModel model = new TKMBaseModel();
		List<SysPermissionModel> permissionBos = permissionService.selectPermissionById(id);
		model.setResultCode(100);
		model.setResultData(permissionBos);
		return model;
	}
	/**
	 * 操作角色的权限
	 * @param roleId 	角色ID
	 * @param ids		权限ID，以‘,’间隔
	 * @return
	 */
	@RequestMapping(value="addPermission2Role")
	@ResponseBody
	public TKMBaseModel addPermission2Role(Long roleId,Long[] ids){
		return permissionService.addPermission2Role(roleId,ids);
	}
	/**
	 * 根据角色id清空权限。
	 * @param roleIds	角色ID ，以‘,’间隔
	 * @return
	 */
	@RequestMapping(value="clearPermissionByRoleIds")
	@ResponseBody
	public TKMBaseModel clearPermissionByRoleIds(Long[] roleIds){
		return permissionService.deleteByRids(roleIds);
	}
}
