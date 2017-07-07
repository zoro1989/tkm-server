package com.ccbjb.controller.permission;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccbjb.common.entity.SysPermission;
import com.ccbjb.common.mybatis.page.Pagination;
import com.ccbjb.common.utils.LoggerUtils;
import com.ccbjb.controller.common.BaseController;
import com.ccbjb.model.TKMBaseModel;
import com.ccbjb.service.permission.IPermissionService;

/**
 * 用户权限管理
 */
@Controller
@Scope(value="prototype")
@RequestMapping("permission")
public class PermissionController extends BaseController {
	
	@Autowired
	IPermissionService permissionService;
	/**
	 * 权限列表
	 * @param findContent	查询内容
	 * @param pageNo		页码
	 * @param modelMap		参数回显
	 * @return
	 */
	@RequestMapping(value="index",method=RequestMethod.POST)
	@ResponseBody
	public TKMBaseModel index(String findContent,ModelMap modelMap,Integer pageNo){
		modelMap.put("findContent", findContent);
		Pagination<SysPermission> permissions = permissionService.findPage(modelMap,pageNo,pageSize);
		TKMBaseModel model = new TKMBaseModel();
		model.setResultCode(100);
		model.setResultData(permissions);
		return model;
	}
	/**
	 * 权限添加
	 * @param psermission
	 * @return
	 */
	@RequestMapping(value="addPermission",method=RequestMethod.POST)
	@ResponseBody
	public TKMBaseModel addPermission(SysPermission psermission){
		TKMBaseModel model = new TKMBaseModel();
		try {
			SysPermission entity = permissionService.insertSelective(psermission);
			model.setResultCode(100);
			model.setResultData("权限添加成功");
		} catch (Exception e) {
			model.setResultCode(500);
			model.setResultData("添加失败，请刷新后再试！");
			LoggerUtils.fmtError(getClass(), e, "添加权限报错。source[%s]", psermission.toString());
		}
		return model;
	}
	/**
	 * 删除权限，根据ID，但是删除权限的时候，需要查询是否有赋予给角色，如果有角色在使用，那么就不能删除。
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="deletePermissionById",method=RequestMethod.POST)
	@ResponseBody
	public TKMBaseModel deletePermissionById(Long[] ids){
		return permissionService.deletePermissionById(ids);
	}
}
