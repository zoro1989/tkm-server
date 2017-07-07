package com.ccbjb.service.user.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.ccbjb.common.dao.ISysUserDao;
import com.ccbjb.common.dao.ISysUserRoleDao;
import com.ccbjb.common.entity.SysUser;
import com.ccbjb.common.entity.SysUserRole;
import com.ccbjb.common.mybatis.BaseMybatisService;
import com.ccbjb.common.mybatis.page.Pagination;
import com.ccbjb.common.utils.LoggerUtils;
import com.ccbjb.common.utils.StringUtils;
import com.ccbjb.logic.user.UserManager;
import com.ccbjb.model.TKMBaseModel;
import com.ccbjb.model.permission.SysRoleModel;
import com.ccbjb.model.permission.UserRoleAllocationModel;
import com.ccbjb.service.user.IUserService;

/**
 * Created by zhulin on 2017/3/18.
 */
@Service
public class UserServiceImpl extends BaseMybatisService<ISysUserDao> implements IUserService {
    /***
     * 用户手动操作Session
     * */
    //@Autowired
    //CustomSessionManager customSessionManager;
    @Autowired
    private ISysUserDao sysUserDao;
    @Autowired
    ISysUserRoleDao sysUserRoleDao;

    public int deleteByPrimaryKey(Long id) {
        return sysUserDao.deleteByPrimaryKey(id);
    }

    @Transactional
    public SysUser insert(SysUser entity) {

        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        entity.setSalt(salt);

        UserManager.md5Pswd(entity);
        Date date = new Date();
        entity.setCreateTime(date);
        entity.setLastLoginTime(date);
        //设置有效
        entity.setStatus(SysUser._1);
        sysUserDao.insert(entity);
        return entity;
    }
    @Transactional
    public SysUser insertSelective(SysUser entity) {
        sysUserDao.insertSelective(entity);
        return entity;
    }

    public SysUser selectByPrimaryKey(Long id) {
        return sysUserDao.selectByPrimaryKey(id);
    }
    @Transactional
    public int updateByPrimaryKey(SysUser entity) {
        return sysUserDao.updateByPrimaryKey(entity);
    }
    @Transactional
    public int updateByPrimaryKeySelective(SysUser entity) {
        return sysUserDao.updateByPrimaryKeySelective(entity);
    }

    public SysUser login(SysUser user) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("email", user.getEmail());
        user = UserManager.md5Pswd(user);
        map.put("pswd", user.getPswd());
        return sysUserDao.login(map);
    }

    public SysUser findUserByEmail(String email) {
        return sysUserDao.findUserByEmail(email);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public Pagination<SysUser> findByPage(Map<String, Object> resultMap,
                                          Integer pageNo, Integer pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }

    @Transactional
    public TKMBaseModel deleteUserById(Long[] ids) {
        TKMBaseModel model = new TKMBaseModel();
        try {
            int count=0;

            for (Long id : ids) {
                count+=this.deleteByPrimaryKey(id);
            }
            model.setResultCode(100);
            model.setResultData("成功删除"+ count +"个！");
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "根据IDS删除用户出现错误，ids[%s]", ids);
            model.setResultCode(500);
            model.setErrorMessage("删除出现错误，请刷新后再试！");
        }
        return model;
    }

    @Transactional
    public Map<String, Object> updateForbidUserById(Long id, Long status) {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        try {
            SysUser user = selectByPrimaryKey(id);
            user.setStatus(status);
            updateByPrimaryKeySelective(user);

            //如果当前用户在线，需要标记并且踢出  踢出功能暂未实现
            //customSessionManager.forbidUserById(id,status);


            resultMap.put("status", 200);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "操作失败，请刷新再试！");
            LoggerUtils.fmtError(getClass(), "禁止或者激活用户登录失败，id[%s],status[%s]", id,status);
        }
        return resultMap;
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public Pagination<UserRoleAllocationModel> findUserAndRole(ModelMap modelMap,
                                                               Integer pageNo, Integer pageSize) {
        return super.findPage("findUserAndRole", "findCount", modelMap, pageNo, pageSize);
    }

    public List<SysRoleModel> selectRoleByUserId(Long id) {
        return sysUserDao.selectRoleByUserId(id);
    }

    @Transactional
    public TKMBaseModel addRole2User(Long userId, Long[] ids) {
        TKMBaseModel model = new TKMBaseModel();
        int count = 0;
        try {
            //先删除原有的。
            sysUserRoleDao.deleteByUserId(userId);
            //如果ids,role 的id 有值，那么就添加。没值象征着：把这个用户（userId）所有角色取消。
            if(ids.length>0){
                //添加新的。
                for (Long id : ids) {
                	SysUserRole entity = new SysUserRole(userId,id);
                    count += sysUserRoleDao.insertSelective(entity);
                }
            }
            model.setResultCode(100);
            model.setResultData("成功添加"+count+"个角色！");
        } catch (Exception e) {
        	model.setResultCode(500);
            model.setResultData("操作失败，请重试！");
        }
        //清空用户的权限，迫使再次获取权限的时候，得重新加载
        //TODO session未持久化 不能清空用户权限
        //TokenManager.clearUserAuthByUserId(userId);
        return model;
    }

    @Transactional
    public TKMBaseModel deleteRoleByUserIds(Long[] userIds) {

        Map<String,Object> resultMap = new HashMap<String, Object>();
        TKMBaseModel model = new TKMBaseModel();
        try {
        	if(StringUtils.isNotBlank(userIds)){
        		StringBuilder sb = new StringBuilder();
        		for (Long id : userIds) {
        			sb.append(id);
        			sb.append(",");
        		}
        		String userIdsSbString = sb.toString();
        		resultMap.put("userIds", userIdsSbString.substring(0, userIdsSbString.length()-1));
        		sysUserRoleDao.deleteRoleByUserIds(resultMap);
        		model.setResultCode(100);
        		model.setResultData("操作成功");
        	}
        } catch (Exception e) {
        	model.setResultCode(500);
            model.setErrorMessage("操作失败，请重试！");
        }
        return model;

    }


}
