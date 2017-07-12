package com.ccbjb.service.mobile.impl;

import com.ccbjb.common.dao.IShopOwnerDao;
import com.ccbjb.common.dao.cache.RedisDao;
import com.ccbjb.common.entity.ShopOwner;
import com.ccbjb.logic.user.MobileUserManager;
import com.ccbjb.service.mobile.MobileLoginService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/12.
 */
public class MobileLoginServiceImpl implements MobileLoginService{

    @Autowired
    IShopOwnerDao iShopOwnerDao;
    @Autowired
    private RedisDao redisDao;

    public ShopOwner login(ShopOwner user) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("nickname", user.getNickname());
        user = MobileUserManager.md5Pswd(user);
        map.put("pswd", user.getPswd());
        ShopOwner result = iShopOwnerDao.login(map);
        if(result!=null){
            //1：访问redis
            //String tokenId = redisDao.getTokenId(result.getEmail());
            String tokenId = MobileUserManager.createTokenId();
            redisDao.putUserId(tokenId, result.getNickname());
            result.setTokenId(tokenId);
        }
        return result;
    }
}
