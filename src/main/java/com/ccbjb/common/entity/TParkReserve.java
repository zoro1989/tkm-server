package com.ccbjb.common.entity;

import java.io.Serializable;

/**
 * @Description TODO       
 */
public class TParkReserve implements Serializable{

	/**
	 * @field serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private Double yuyueNo;
	private String empAccoutId;
	private String deviceId;
	private String parkingPlaceArea;
	private String yuyueStatus;
	private String yuyueTimeFrom;
	private String yuyueTimeTo;
	private String parkingCarNo;
	private String parkNo;
	private String delFlg;
	private String yuyueTimeReq;
	private String yuyueTimeRsp;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Double getYuyueNo() {
		return yuyueNo;
	}
	public void setYuyueNo(Double yuyueNo) {
		this.yuyueNo = yuyueNo;
	}
	public String getEmpAccoutId() {
		return empAccoutId;
	}
	public void setEmpAccoutId(String empAccoutId) {
		this.empAccoutId = empAccoutId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getParkingPlaceArea() {
		return parkingPlaceArea;
	}
	public void setParkingPlaceArea(String parkingPlaceArea) {
		this.parkingPlaceArea = parkingPlaceArea;
	}
	public String getYuyueStatus() {
		return yuyueStatus;
	}
	public void setYuyueStatus(String yuyueStatus) {
		this.yuyueStatus = yuyueStatus;
	}
	public String getYuyueTimeFrom() {
		return yuyueTimeFrom;
	}
	public void setYuyueTimeFrom(String yuyueTimeFrom) {
		this.yuyueTimeFrom = yuyueTimeFrom;
	}
	public String getYuyueTimeTo() {
		return yuyueTimeTo;
	}
	public void setYuyueTimeTo(String yuyueTimeTo) {
		this.yuyueTimeTo = yuyueTimeTo;
	}
	public String getParkingCarNo() {
		return parkingCarNo;
	}
	public void setParkingCarNo(String parkingCarNo) {
		this.parkingCarNo = parkingCarNo;
	}
	public String getParkNo() {
		return parkNo;
	}
	public void setParkNo(String parkNo) {
		this.parkNo = parkNo;
	}
	public String getDelFlg() {
		return delFlg;
	}
	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}
	public String getYuyueTimeReq() {
		return yuyueTimeReq;
	}
	public void setYuyueTimeReq(String yuyueTimeReq) {
		this.yuyueTimeReq = yuyueTimeReq;
	}
	public String getYuyueTimeRsp() {
		return yuyueTimeRsp;
	}
	public void setYuyueTimeRsp(String yuyueTimeRsp) {
		this.yuyueTimeRsp = yuyueTimeRsp;
	}
	

}
