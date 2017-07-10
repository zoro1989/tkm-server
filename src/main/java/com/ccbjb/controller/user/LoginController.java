package com.ccbjb.controller.user;

import com.ccbjb.common.entity.SysUser;
import com.ccbjb.common.shiro.TokenManager;
import com.ccbjb.common.shiro.VerifyCodeUtils;
import com.ccbjb.common.utils.LoggerUtils;
import com.ccbjb.common.utils.StringUtils;
import com.ccbjb.controller.common.BaseController;
import com.ccbjb.model.TKMBaseModel;
import com.ccbjb.service.user.IUserService;
import net.sf.json.JSONObject;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 登陆和退出
 * @version 1.0
 * @since 1.0
 * @author CJB-国内开发组
 */
@Controller
@Scope(value="prototype")
@RequestMapping("user")
public class LoginController extends BaseController{

	@Autowired
	IUserService userService;
	/**
	 * 登录跳转
	 * @return
	 */
	@RequestMapping(value="login")
	@ResponseBody
	public TKMBaseModel login(HttpServletRequest request){
		TKMBaseModel model = new TKMBaseModel();
		model.setResultCode(101);
		return model;
	}

	/**
	 * 无权限
	 * @return
	 */
	@RequestMapping(value="refuse")
	@ResponseBody
	public TKMBaseModel refuse(HttpServletRequest request){
		TKMBaseModel model = new TKMBaseModel();
		model.setResultCode(103);
		return model;
	}

	//登陆提交地址，和applicationContext-shiro.xml中配置的loginurl一致
	@RequestMapping(value="submitLogin",method=RequestMethod.POST)
	@ResponseBody
	public TKMBaseModel submitLogin(SysUser sysUser, Boolean rememberMe, HttpServletRequest request)throws Exception{
		TKMBaseModel model = new TKMBaseModel();
		try {
			sysUser = TokenManager.login(sysUser.getEmail(),sysUser.getPswd(),rememberMe);

			/**
			 * shiro 获取登录之前的地址
			 * 之前0.1版本这个没判断空。
			 */
			SavedRequest savedRequest = WebUtils.getSavedRequest(request);
			String url = null ;
			if(null != savedRequest){
				url = savedRequest.getRequestUrl();
			}
			/**
			 * 我们平常用的获取上一个请求的方式，在Session不一致的情况下是获取不到的
			 * String url = (String) request.getAttribute(WebUtils.FORWARD_REQUEST_URI_ATTRIBUTE);
			 */
			LoggerUtils.fmtDebug(getClass(), "获取登录之前的URL:[%s]",url);
			//如果登录之前没有地址，那么就跳转到首页。
			if(StringUtils.isBlank(url)){
				url = "first";
			}
			model.setResultCode(104);
			Map<String,String> resultMap = new HashMap<String, String>();
			resultMap.put("resultMsg", "登录成功");
			//跳转地址
			resultMap.put("back_url", url);
			model.setResultData(resultMap);
			/**
			 * 这里其实可以直接catch Exception，然后抛出 message即可，但是最好还是各种明细catch 好点。。
			 */
		} catch (DisabledAccountException e) {
			model.setResultCode(500);
			model.setErrorMessage("帐号已经禁用。");
		} catch (Exception e) {
			model.setResultCode(500);
			model.setErrorMessage("帐号或密码错误");
		}

		return model;
	}

	/**
	 * 注册 && 登录
	 * @param vcode		验证码
	 * @param entity	SysUser实体
	 * @return
	 */
	@RequestMapping(value="subRegister",method=RequestMethod.POST)
	@ResponseBody
	public TKMBaseModel subRegister(String vcode,SysUser entity){
		TKMBaseModel model = new TKMBaseModel();
		if(!VerifyCodeUtils.verifyCode(vcode)){
			model.setResultCode(500);
			model.setErrorMessage("验证码不正确！");
			return model;
		}
		String email =  entity.getEmail();
		String password = entity.getPswd();

		SysUser user = userService.findUserByEmail(email);
		if(null != user){
			model.setResultCode(500);
			model.setErrorMessage("帐号|Email已经存在！");
			return model;
		}

		userService.insert(entity);
		LoggerUtils.fmtDebug(getClass(), "注册插入完毕！", JSONObject.fromObject(entity).toString());
		TokenManager.login(email,password, Boolean.TRUE);
		LoggerUtils.fmtDebug(getClass(), "注册后，登录完毕！", JSONObject.fromObject(entity).toString());
		model.setResultCode(100);
		model.setResultData("注册成功！");
		return model;
	}
}
