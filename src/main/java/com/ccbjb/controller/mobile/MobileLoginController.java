package com.ccbjb.controller.mobile;

import com.ccbjb.common.entity.ShopOwner;
import com.ccbjb.controller.common.BaseController;
import com.ccbjb.model.TKMBaseModel;
import com.ccbjb.service.mobile.MobileLoginService;
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
@RequestMapping("api")
public class MobileLoginController extends BaseController {

    @Autowired
    MobileLoginService mobileLoginService;

    @RequestMapping(value="mobileLogin",method= RequestMethod.POST)
    @ResponseBody
    public TKMBaseModel empLogin(ShopOwner shopOwner, HttpServletRequest request)throws Exception{
        ShopOwner user = mobileLoginService.login(shopOwner);
        TKMBaseModel model = new TKMBaseModel();
        if(user!=null){
            model.setResultCode(104);
            Map m = new HashMap<String,String>();
            m.put("resultMsg","登陆成功");
            m.put("tokenId",user.getTokenId());
            model.setResultData(m);
        }else{
            model.setResultCode(500);
            model.setErrorMessage("帐号或密码错误");
        }
        return model;
    }
}
