package com.ccbjb.service.user;

import java.util.List;
import java.util.Map;

import org.springframework.ui.ModelMap;

import com.ccbjb.common.entity.SysUser;
import com.ccbjb.common.mybatis.page.Pagination;
import com.ccbjb.model.TKMBaseModel;
import com.ccbjb.model.permission.SysRoleModel;
import com.ccbjb.model.permission.UserRoleAllocationModel;

/**
 * Created by zhulin on 2017/3/18.
 */
public interface IUserService {
    int deleteByPrimaryKey(Long id);

    SysUser insert(SysUser record);

    SysUser insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser login(SysUser record);

    SysUser findUserByEmail(String email);

    Pagination<SysUser> findByPage(Map<String, Object> resultMap, Integer pageNo,
                                     Integer pageSize);

    TKMBaseModel deleteUserById(Long[] ids);

    Map<String, Object> updateForbidUserById(Long id, Long status);

    Pagination<UserRoleAllocationModel> findUserAndRole(ModelMap modelMap,
                                                        Integer pageNo, Integer pageSize);

    List<SysRoleModel> selectRoleByUserId(Long id);

    TKMBaseModel addRole2User(Long userId, Long[] ids);

    TKMBaseModel deleteRoleByUserIds(Long[] userIds);
}
