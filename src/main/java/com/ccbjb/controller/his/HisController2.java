package com.ccbjb.controller.his;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccbjb.controller.common.BaseController;
import com.ccbjb.model.TKMBaseModel;

/**
 * 消息推送
 */
@Controller
@Scope(value="prototype")
@RequestMapping("api")
public class HisController2 extends BaseController {
	
	/**
	 * 消息推送index
	 * @return
	 */
	@RequestMapping(value="goods")
	@ResponseBody
	public TKMBaseModel goods(){
		TKMBaseModel b = new TKMBaseModel();
		b.setResultCode(100);
		b.setResultData("成功");
		
		return b;
	}
}
