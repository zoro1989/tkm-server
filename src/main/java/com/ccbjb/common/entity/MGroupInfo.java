package com.ccbjb.common.entity;

import java.io.Serializable;

import net.sf.json.JSONObject;

/**
 * @Project_name ParkCharge Maven Webapp
 * @Package_name com.ccbjb.common.entity
 * @File_name GroupMessage.java
 * @Class_name GroupMessage
 * @Description 分组信息
 * @date 2017年4月1日 下午4:51:45
 * @author CaiHuiyu
 * @version v1.0 
 * @变更NUM 1.0	  @变更者 CaiHuiyu 	@变更时间 2017年4月1日 下午4:51:45	   @变更内容 初版建成         
 */
public class MGroupInfo implements Serializable{

	/**
	 * @field serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 组ID
	 */
    private String groupId;
    /**
     * 组级别 
     */
    private String groupLevel;
    /**
     * 上级组ID
     */
    private String groupSuperiorId;
    /**
     * 组名称
     */
    private String groupName;
    /**
     * 组地址
     */
    private String groupAddr;
    /**
     * 组GPS_经度
     */
    private Long groupGpsX;
    /**
     * 组GPS_纬度
     */
    private Long groupGpsY;
    /**
     * @return the groupId
     * @date 2017年4月1日 下午5:13:01
     */
	public String getGroupId() {
		return groupId;
	}
	/**
	 * @param groupId the groupId to set
	 * @date 2017年4月1日 下午5:13:01
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	/**
	 * @return the groupLevel
	 * @date 2017年4月1日 下午5:13:01
	 */
	public String getGroupLevel() {
		return groupLevel;
	}
	/**
	 * @param groupLevel the groupLevel to set
	 * @date 2017年4月1日 下午5:13:01
	 */
	public void setGroupLevel(String groupLevel) {
		this.groupLevel = groupLevel;
	}
	/**
	 * @return the groupSuperiorId
	 * @date 2017年4月1日 下午5:13:01
	 */
	public String getGroupSuperiorId() {
		return groupSuperiorId;
	}
	/**
	 * @param groupSuperiorId the groupSuperiorId to set
	 * @date 2017年4月1日 下午5:13:01
	 */
	public void setGroupSuperiorId(String groupSuperiorId) {
		this.groupSuperiorId = groupSuperiorId;
	}
	/**
	 * @return the groupName
	 * @date 2017年4月1日 下午5:13:01
	 */
	public String getGroupName() {
		return groupName;
	}
	/**
	 * @param groupName the groupName to set
	 * @date 2017年4月1日 下午5:13:01
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	/**
	 * @return the groupAddr
	 * @date 2017年4月1日 下午5:13:01
	 */
	public String getGroupAddr() {
		return groupAddr;
	}
	/**
	 * @param groupAddr the groupAddr to set
	 * @date 2017年4月1日 下午5:13:01
	 */
	public void setGroupAddr(String groupAddr) {
		this.groupAddr = groupAddr;
	}
	/**
	 * @return the groupGpsX
	 * @date 2017年4月1日 下午5:26:21
	 */
	public Long getGroupGpsX() {
		return groupGpsX;
	}
	/**
	 * @return the groupGpsX
	 * @date 2017年4月1日 下午5:26:21
	 */
	public void setGroupGpsX(Long groupGpsX) {
		this.groupGpsX = groupGpsX;
	}
	/**
	 * @return the groupGpsY
	 * @date 2017年4月1日 下午5:26:21
	 */
	public Long getGroupGpsY() {
		return groupGpsY;
	}
	/**
	 * @param groupGpsY the groupGpsY to set
	 * @date 2017年4月1日 下午5:26:21
	 */
	public void setGroupGpsY(Long groupGpsY) {
		this.groupGpsY = groupGpsY;
	}
	/**
	 * 
	 * @Description 
	 * @return
	 * @author CaiHuiyu
	 * @date 2017年4月1日 下午5:14:55
	 * @version v1.0
	 */
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}
}
