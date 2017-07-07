package com.ccbjb.controller.permission;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ccbjb.common.mybatis.page.Pagination;
import com.ccbjb.controller.common.BaseController;
import com.ccbjb.model.TKMBaseModel;
import com.ccbjb.model.permission.SysRoleModel;
import com.ccbjb.model.permission.UserRoleAllocationModel;
import com.ccbjb.service.permission.IPermissionService;
import com.ccbjb.service.user.IUserService;

/**
 * 用户角色分配
 */
@Controller
@Scope(value="prototype")
@RequestMapping("role")
public class UserRoleAllocationController extends BaseController {
	@Autowired
	IUserService userService;
	@Autowired
	IPermissionService permissionService;
	/**
	 * 用户角色权限分配
	 * @param modelMap
	 * @param pageNo
	 * @param findContent
	 * @return
	 */
	@RequestMapping(value="allocation",method=RequestMethod.POST)
	@ResponseBody
	public TKMBaseModel allocation(ModelMap modelMap,Integer pageNo,String findContent){
		modelMap.put("findContent", findContent);
		Pagination<UserRoleAllocationModel> boPage = userService.findUserAndRole(modelMap,pageNo,pageSize);
		TKMBaseModel model = new TKMBaseModel();
		model.setResultCode(100);
		model.setResultData(boPage);
		return model;
	}
	
	/**
	 * 根据用户ID查询权限
	 * @param id
	 * @return
	 */
	@RequestMapping(value="selectRoleByUserId")
	@ResponseBody
	public TKMBaseModel selectRoleByUserId(Long id){
		List<SysRoleModel> bos = userService.selectRoleByUserId(id);
		TKMBaseModel model = new TKMBaseModel();
		model.setResultCode(100);
		model.setResultData(bos);
		return model;
	}
	/**
	 * 操作用户的角色
	 * @param userId 	用户ID
	 * @param ids		角色ID，以‘,’间隔
	 * @return
	 */
	@RequestMapping(value="addRole2User",method=RequestMethod.POST)
	@ResponseBody
	public TKMBaseModel addRole2User(Long userId,Long[] ids){
		return userService.addRole2User(userId,ids);
	}
	/**
	 * 根据用户id清空角色。
	 * @param userIds	用户ID ，以‘,’间隔
	 * @return
	 */
	@RequestMapping(value="clearRoleByUserIds")
	@ResponseBody
	public TKMBaseModel clearRoleByUserIds(Long[] userIds){
		return userService.deleteRoleByUserIds(userIds);
	}
}
