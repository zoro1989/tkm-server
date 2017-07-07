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

import com.ccbjb.common.entity.SysRole;
import com.ccbjb.common.mybatis.page.Pagination;
import com.ccbjb.common.utils.LoggerUtils;
import com.ccbjb.controller.common.BaseController;
import com.ccbjb.logic.user.UserManager;
import com.ccbjb.model.TKMBaseModel;
import com.ccbjb.service.permission.IRoleService;

/**
 * 用户角色管理
 */
@Controller
@Scope(value="prototype")
@RequestMapping("role")
public class RoleController extends BaseController {
	@Autowired
	IRoleService roleService;
	/**
	 * 角色列表
	 * @return
	 */
	@RequestMapping(value="index",method=RequestMethod.POST)
	@ResponseBody
	public TKMBaseModel index(String findContent,ModelMap modelMap){
		modelMap.put("findContent", findContent);
		Pagination<SysRole> role = roleService.findPage(modelMap,pageNo,pageSize);
		TKMBaseModel model = new TKMBaseModel();
		model.setResultCode(100);
		model.setResultData(role);
		return model;
	}
	/**
	 * 角色添加
	 * @param role
	 * @return
	 */
	@RequestMapping(value="addRole",method=RequestMethod.POST)
	@ResponseBody
	public TKMBaseModel addRole(SysRole role){
		TKMBaseModel model = new TKMBaseModel();
		try {
			int count = roleService.insertSelective(role);
			model.setResultCode(100);
			model.setResultData("成功添加了一个角色");
		} catch (Exception e) {
			model.setResultCode(500);
			model.setErrorMessage("添加失败，请刷新后再试！");
			LoggerUtils.fmtError(getClass(), e, "添加角色报错。source[%s]",role.toString());
		}
		return model;
	}
	/**
	 * 删除角色，根据ID，但是删除角色的时候，需要查询是否有赋予给用户，如果有用户在使用，那么就不能删除。
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="deleteRoleById",method=RequestMethod.POST)
	@ResponseBody
	public TKMBaseModel deleteRoleById(Long[] ids){
		return roleService.deleteRoleById(ids);
	}
	/**
	 * 我的权限
	 * @return
	 */
	@RequestMapping(value="getPermissionTree",method=RequestMethod.POST)
	@ResponseBody
	public TKMBaseModel getPermissionTree(){
		//查询我所有的角色 ---> 权限
		List<SysRole> roles = roleService.findNowAllPermission();
		//把查询出来的roles 转换成bootstarp 的 tree数据
		TKMBaseModel model = new TKMBaseModel();
		model.setResultCode(100);
		model.setResultData(roles);
		return model;
	}
}
