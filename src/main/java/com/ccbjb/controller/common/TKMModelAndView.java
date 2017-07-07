package com.ccbjb.controller.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.ccbjb.common.entity.SysUser;
import com.ccbjb.common.shiro.TokenManager;

/**
 * Created by Administrator on 2017/3/24.
 */
public class TKMModelAndView extends ModelAndView {

    public TKMModelAndView() {
    }

    public TKMModelAndView(String viewName) {
        super.setViewName(viewName);
        SysUser token = TokenManager.getToken();
        super.addObject("token",token);
    }

    public TKMModelAndView(String viewName,HttpServletRequest request) {
        super.setViewName(viewName);
        SysUser token = TokenManager.getToken();
        super.addObject("token",token);
    }


    public TKMModelAndView(String viewName, String modelName, Object modelObject) {
        super.setViewName(viewName);
        this.addObject(modelName, modelObject);
        SysUser token = TokenManager.getToken();
        super.addObject("token",token);
    }

    public TKMModelAndView(String viewName, String modelName, Object modelObject,HttpServletRequest request) {
        super.setViewName(viewName);
        this.addObject(modelName, modelObject);
        SysUser token = TokenManager.getToken();
        super.addObject("token",token);
    }

}
