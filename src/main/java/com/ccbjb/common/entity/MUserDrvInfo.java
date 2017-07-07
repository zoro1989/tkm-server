package com.ccbjb.common.entity;

import java.io.Serializable;

/**
 * @Description 车主信息      
 */
public class MUserDrvInfo implements Serializable{
	 /**
		 * @field serialVersionUID
		 */
	   private static final long serialVersionUID = 1L;
	   private String userId;
	   private String userName;
	   private String bindcarNo1;
	   private String bindcarNo2;
	   private String bindcarNo3;
	   private String bindcarNo4;
	   private String bindcarNo5;
	   private String acountWithhold;
	   private String acountBankcd;
	   private String registTime;
	   private String userRating;
	   private String enable;
	   private String frozenType;
	   private String delFlg;
	   private String empAccoutId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBindcarNo1() {
		return bindcarNo1;
	}
	public void setBindcarNo1(String bindcarNo1) {
		this.bindcarNo1 = bindcarNo1;
	}
	public String getBindcarNo2() {
		return bindcarNo2;
	}
	public void setBindcarNo2(String bindcarNo2) {
		this.bindcarNo2 = bindcarNo2;
	}
	public String getBindcarNo3() {
		return bindcarNo3;
	}
	public void setBindcarNo3(String bindcarNo3) {
		this.bindcarNo3 = bindcarNo3;
	}
	public String getBindcarNo4() {
		return bindcarNo4;
	}
	public void setBindcarNo4(String bindcarNo4) {
		this.bindcarNo4 = bindcarNo4;
	}
	public String getBindcarNo5() {
		return bindcarNo5;
	}
	public void setBindcarNo5(String bindcarNo5) {
		this.bindcarNo5 = bindcarNo5;
	}
	public String getAcountWithhold() {
		return acountWithhold;
	}
	public void setAcountWithhold(String acountWithhold) {
		this.acountWithhold = acountWithhold;
	}
	public String getAcountBankcd() {
		return acountBankcd;
	}
	public void setAcountBankcd(String acountBankcd) {
		this.acountBankcd = acountBankcd;
	}
	public String getRegistTime() {
		return registTime;
	}
	public void setRegistTime(String registTime) {
		this.registTime = registTime;
	}
	public String getUserRating() {
		return userRating;
	}
	public void setUserRating(String userRating) {
		this.userRating = userRating;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	public String getFrozenType() {
		return frozenType;
	}
	public void setFrozenType(String frozenType) {
		this.frozenType = frozenType;
	}
	public String getDelFlg() {
		return delFlg;
	}
	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}
	public String getEmpAccoutId() {
		return empAccoutId;
	}
	public void setEmpAccoutId(String empAccoutId) {
		this.empAccoutId = empAccoutId;
	}
	   
}
