package com.ccbjb.controller.push;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

import com.ccbjb.common.utils.LoggerUtils;
import com.ccbjb.controller.common.BaseController;
import com.ccbjb.model.TKMBaseModel;

/**
 * 消息推送
 */
@Controller
@Scope(value="prototype")
@RequestMapping("push")
public class PushController extends BaseController {
	
	/**
	 * 消息推送index
	 * @return
	 */
	@RequestMapping(value="index")
	public String index(){
		return "push/index";
	}
	/**
	 * 消息推送
	 * @param pushTitle	          推送标题
	 * @param pushContent	 推送内容
	 * @param pushTag		 设备tag
	 * @param pushAlias		 设备alias
	 * @return
	 */
	@RequestMapping(value="send",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> send(String pushTitle,String pushContent,String pushTag,String pushAlias){
		
		JPushClient jpushClient = new JPushClient("8fea6193b3c2723254887345", "a482d6ef515165c46b805000", null, ClientConfig.getInstance());
		
		PushPayload payload = buildPushObject(pushContent,pushTitle,pushTag);
		TKMBaseModel model = new TKMBaseModel();
		try {
			PushResult result = jpushClient.sendPush(payload);
			
			LoggerUtils.fmtDebug(getClass(), "推送成功 结果 - " + result);
			model.setResultCode(100);
			model.setResultData("推送成功");
		} catch (APIConnectionException e) {
			LoggerUtils.fmtDebug(getClass(), "连接极光推送服务器失败，请稍后重试");
			model.setResultCode(500);
			model.setErrorMessage("连接极光推送服务器失败，请稍后重试");
			
		} catch (APIRequestException e) {
			// Should review the error, and fix the request
			LoggerUtils.fmtDebug(getClass(), "请求失败");
			LoggerUtils.fmtDebug(getClass(), "HTTP Status: " + e.getStatus());
			LoggerUtils.fmtDebug(getClass(), "Error Code: " + e.getErrorCode());
			LoggerUtils.fmtDebug(getClass(), "Error Message: " + e.getErrorMessage());
			model.setResultCode(500);
			model.setErrorMessage("请求失败");
		}
		
		return resultMap;
	}
	
	private PushPayload buildPushObject(String alert,String titles,String tag) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.tag(tag))
                .setNotification(Notification.android(alert, titles, null))
                .build();
    }
}
