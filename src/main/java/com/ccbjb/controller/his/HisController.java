package com.ccbjb.controller.his;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ccbjb.controller.common.BaseController;

/**
 * 消息推送
 */
@Controller
@Scope(value="prototype")
@RequestMapping("his")
public class HisController extends BaseController {
	
	/**
	 * 消息推送index
	 * @return
	 */
	@RequestMapping(value="index")
	public String index(){
		return "his/index";
	}
}
