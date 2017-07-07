package com.ccbjb.controller.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ccbjb.common.entity.SysUser;
import com.ccbjb.common.mybatis.page.Pagination;
import com.ccbjb.controller.common.BaseController;
import com.ccbjb.model.TKMBaseModel;
import com.ccbjb.service.user.IUserService;

/**
 * 用户会员管理
 */
@Controller
@Scope(value="prototype")
@RequestMapping("member")
public class MemberController extends BaseController {
	@Autowired
	IUserService userService;
	/**
	 * 用户列表管理
	 * @return
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public TKMBaseModel list(ModelMap map,Integer pageNo,String findContent){
		map.put("findContent", findContent);
		Pagination<SysUser> page = userService.findByPage(map,pageNo,pageSize);
		TKMBaseModel model = new TKMBaseModel();
		model.setResultCode(100);
		model.setResultData(page);
		return model;
	}
	/**
	 * 根据ID删除，
	 * @param ids	如果有多个，以“,”间隔。
	 * @return
	 */
	@RequestMapping(value="deleteUserById",method=RequestMethod.POST)
	@ResponseBody
	public TKMBaseModel deleteUserById(Long[] ids){
		return userService.deleteUserById(ids);
	}
	/**
	 * 禁止登录
	 * @param id		用户ID
	 * @param status	1:有效，0:禁止登录
	 * @return
	 */
	@RequestMapping(value="forbidUserById",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> forbidUserById(Long id,Long status){
		return userService.updateForbidUserById(id,status);
	}
	
}
