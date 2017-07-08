package com.ccbjb.controller.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccbjb.common.entity.SysUser;
import com.ccbjb.common.shiro.TokenManager;
import com.ccbjb.common.utils.LoggerUtils;
import com.ccbjb.controller.common.BaseController;
import com.ccbjb.controller.common.TKMModelAndView;
import com.ccbjb.logic.user.UserManager;
import com.ccbjb.model.TKMBaseModel;
import com.ccbjb.service.user.IUserService;

/**
 * 登陆和退出
 * @version 1.0
 * @since 1.0
 * @author CJB-国内开发组
 */
@Controller
@Scope(value="prototype")
@RequestMapping("welcome")
public class WelcomeController extends BaseController{

	@Autowired
	IUserService userService;

	//登陆提交地址，和applicationContext-shiro.xml中配置的loginurl一致
	@RequestMapping("first")
	@ResponseBody
	public TKMBaseModel first(HttpServletRequest request, ModelMap modal)throws Exception{
		TKMBaseModel model = new TKMBaseModel();
		model.setResultCode(102);
		return model;
	}
	
	@RequestMapping("userinfo")
	@ResponseBody
	public TKMBaseModel userinfo(HttpServletRequest request, ModelMap modal)throws Exception{
		
		SysUser token = TokenManager.getToken();
		TKMBaseModel model = new TKMBaseModel();
		model.setResultData(token);
		return model;
	}

	/**
	 * 密码修改
	 * @return
	 */
	@RequestMapping(value="updatePswd",method=RequestMethod.POST)
	@ResponseBody
	public TKMBaseModel updatePswd(String pswd,String newPswd){
		SysUser	user = TokenManager.getToken();
		String email = user.getEmail();
		user.setPswd(pswd);
		user = userService.login(user);
		
		TKMBaseModel model = new TKMBaseModel();
		if("admin".equals(email)){
			model.setResultCode(300);
			model.setErrorMessage("管理员不准修改密码。");
			return model;
		}
		
		if(null == user){
			model.setResultCode(300);
			model.setErrorMessage("密码不正确！");
		}else{
			String newPassword = newPswd;
			user.setPswd(newPassword);
			//重新生成盐值
			String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
			user.setSalt(salt);
	        user = UserManager.md5Pswd(user);
			//修改密码
			userService.updateByPrimaryKeySelective(user);
			model.setResultCode(100);
			model.setResultData("修改成功!");
			//重新登录一次
			TokenManager.login(user.getEmail(),newPassword, Boolean.TRUE);
		}
		return model;
	}
	/**
	 * 个人资料修改
	 * @return
	 */
	@RequestMapping(value="updateSelf",method=RequestMethod.POST)
	@ResponseBody
	public TKMBaseModel updateSelf(SysUser entity){
		TKMBaseModel model = new TKMBaseModel();
		try {
			userService.updateByPrimaryKeySelective(entity);
			model.setResultCode(100);
			model.setResultData("修改成功!");
		} catch (Exception e) {
			model.setResultCode(500);
			model.setResultData("修改失败!");
			LoggerUtils.fmtError(getClass(), e, "修改个人资料出错。[%s]", JSONObject.fromObject(entity).toString());
		}
		return model;
	}
}
