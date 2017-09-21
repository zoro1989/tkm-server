package com.ccbjb.dao.impl;

import com.ccbjb.common.domain.SysRolePermission;
import com.ccbjb.common.mapper.SysRolePermissionMapper;
import com.ccbjb.common.mybatis.AbstractDao;
import com.ccbjb.dao.SysRolePermissionDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2017/08/01.
 */
@Component
public class SysRolePermissionDaoImpl extends AbstractDao<SysRolePermission> implements SysRolePermissionDao {
    @Resource
    private SysRolePermissionMapper sysRolePermissionMapper;

    public List<SysRolePermission> findRolePermissionByPid(Long id) {
        return sysRolePermissionMapper.findRolePermissionByPid(id);
    }

    public List<SysRolePermission> findRolePermissionByRid(Long id) {
        return sysRolePermissionMapper.findRolePermissionByRid(id);
    }

    public List<SysRolePermission> find(SysRolePermission entity) {
        return sysRolePermissionMapper.find(entity);
    }

    public int deleteByPid(Long id) {
        return sysRolePermissionMapper.deleteByPid(id);
    }

    public int deleteByRid(Long id) {
        return sysRolePermissionMapper.deleteByRid(id);
    }

    public int deleteByEntity(SysRolePermission entity) {
        return sysRolePermissionMapper.delete(entity);
    }

    public int deleteByRids(Map<String, Object> resultMap) {
        return sysRolePermissionMapper.deleteByRids(resultMap);
    }
}
