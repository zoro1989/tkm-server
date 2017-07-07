package com.ccbjb.common.entity;

import java.io.Serializable;

import net.sf.json.JSONObject;
/**
 * 角色{@link SysRole}和 权限{@link SysPermission}中间表
 * @version 1.0
 * @since 1.0
 * @author CJB-国内开发组
 */
public class SysRolePermission implements Serializable{
	private static final long serialVersionUID = 1L;
	/**{@link SysRole.id}*/
    private Long rid;
    /**{@link SysPermission.id}*/
    private Long pid;

    public SysRolePermission() {
	}
    public SysRolePermission(Long rid, Long pid) {
    	this.rid = rid;
    	this.pid = pid;
    }
    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }
    public String toString(){
    	return JSONObject.fromObject(this).toString();
    }
}