package com.ccbjb.common.entity;

import java.io.Serializable;

/**
 * @author 仇招
 * @version v1.0
 * @date 2017年4月10日 下午4:23:44
 */
public class TParkOrder implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO(版本号)
	 */
	private static final long serialVersionUID = 1L;
	// 流水号
	private String orderNum;
	// 结算状态
	private String orderStatus;
	// 取消原因种类
	private String accountsStatus;
	// 结算时间
	private String orderTimeCal;
	// 支付时间
	private String orderTimePay;
	// 车主ID
	private String userId;
	// 车牌编号
	private String parkingCarNo;
	// 车位编号
	private String parkNo;
	// 车位类型
	private String parkingPlaceType;
	// 停车开始时间
	private String parkingTimeFrom;
	// 停车结束时间
	private String parkingTimeTo;
	// 停车费
	private double parkingFee;
	// 收费员ID
	private String empAccoutId;
	// 设备ID
	private String deviceId;
	// 支付方式
	private String paymentMode;
	// 订单号
	private String paymentOrderCode;
	// 已开发票
	private String invoiceFlg;
	// 删除FLG
	private String delFlg;

	/**
	 * @return String orderNum
	 * @date 2017年4月10日 下午4:30:01
	 */
	public String getOrderNum() {
		return orderNum;
	}

	/**
	 * @param orderNum
	 *            the orderNum to set
	 * @date 2017年4月10日 下午4:30:01
	 */
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * @return String orderStatus
	 * @date 2017年4月10日 下午4:30:01
	 */
	public String getOrderStatus() {
		return orderStatus;
	}

	/**
	 * @param orderStatus
	 *            the orderStatus to set
	 * @date 2017年4月10日 下午4:30:01
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * @return String accountsStatus
	 * @date 2017年4月10日 下午4:30:01
	 */
	public String getAccountsStatus() {
		return accountsStatus;
	}

	/**
	 * @param accountsStatus
	 *            the accountsStatus to set
	 * @date 2017年4月10日 下午4:30:01
	 */
	public void setAccountsStatus(String accountsStatus) {
		this.accountsStatus = accountsStatus;
	}

	/**
	 * @return String orderTimeCal
	 * @date 2017年4月10日 下午4:30:01
	 */
	public String getOrderTimeCal() {
		return orderTimeCal;
	}

	/**
	 * @param orderTimeCal
	 *            the orderTimeCal to set
	 * @date 2017年4月10日 下午4:30:01
	 */
	public void setOrderTimeCal(String orderTimeCal) {
		this.orderTimeCal = orderTimeCal;
	}

	/**
	 * @return String orderTimePay
	 * @date 2017年4月10日 下午4:30:01
	 */
	public String getOrderTimePay() {
		return orderTimePay;
	}

	/**
	 * @param orderTimePay
	 *            the orderTimePay to set
	 * @date 2017年4月10日 下午4:30:01
	 */
	public void setOrderTimePay(String orderTimePay) {
		this.orderTimePay = orderTimePay;
	}

	/**
	 * @return String userId
	 * @date 2017年4月10日 下午4:30:01
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 * @date 2017年4月10日 下午4:30:01
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return String parkingCarNo
	 * @date 2017年4月10日 下午4:30:01
	 */
	public String getParkingCarNo() {
		return parkingCarNo;
	}

	/**
	 * @param parkingCarNo
	 *            the parkingCarNo to set
	 * @date 2017年4月10日 下午4:30:01
	 */
	public void setParkingCarNo(String parkingCarNo) {
		this.parkingCarNo = parkingCarNo;
	}

	/**
	 * @return String parkNo
	 * @date 2017年4月10日 下午4:30:01
	 */
	public String getParkNo() {
		return parkNo;
	}

	/**
	 * @param parkNo
	 *            the parkNo to set
	 * @date 2017年4月10日 下午4:30:01
	 */
	public void setParkNo(String parkNo) {
		this.parkNo = parkNo;
	}

	/**
	 * @return String parkingPlaceType
	 * @date 2017年4月10日 下午4:30:01
	 */
	public String getParkingPlaceType() {
		return parkingPlaceType;
	}

	/**
	 * @param parkingPlaceType
	 *            the parkingPlaceType to set
	 * @date 2017年4月10日 下午4:30:01
	 */
	public void setParkingPlaceType(String parkingPlaceType) {
		this.parkingPlaceType = parkingPlaceType;
	}

	/**
	 * @return String parkingTimeFrom
	 * @date 2017年4月10日 下午4:30:01
	 */
	public String getParkingTimeFrom() {
		return parkingTimeFrom;
	}

	/**
	 * @param parkingTimeFrom
	 *            the parkingTimeFrom to set
	 * @date 2017年4月10日 下午4:30:01
	 */
	public void setParkingTimeFrom(String parkingTimeFrom) {
		this.parkingTimeFrom = parkingTimeFrom;
	}

	/**
	 * @return String parkingTimeTo
	 * @date 2017年4月10日 下午4:30:01
	 */
	public String getParkingTimeTo() {
		return parkingTimeTo;
	}

	/**
	 * @param parkingTimeTo
	 *            the parkingTimeTo to set
	 * @date 2017年4月10日 下午4:30:01
	 */
	public void setParkingTimeTo(String parkingTimeTo) {
		this.parkingTimeTo = parkingTimeTo;
	}

	/**
	 * @return double parkingFee
	 * @date 2017年4月10日 下午4:30:01
	 */
	public double getParkingFee() {
		return parkingFee;
	}

	/**
	 * @param parkingFee
	 *            the parkingFee to set
	 * @date 2017年4月10日 下午4:30:01
	 */
	public void setParkingFee(double parkingFee) {
		this.parkingFee = parkingFee;
	}

	/**
	 * @return String empAccoutId
	 * @date 2017年4月10日 下午4:30:01
	 */
	public String getEmpAccoutId() {
		return empAccoutId;
	}

	/**
	 * @param empAccoutId
	 *            the empAccoutId to set
	 * @date 2017年4月10日 下午4:30:01
	 */
	public void setEmpAccoutId(String empAccoutId) {
		this.empAccoutId = empAccoutId;
	}

	/**
	 * @return String deviceId
	 * @date 2017年4月10日 下午4:30:01
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId
	 *            the deviceId to set
	 * @date 2017年4月10日 下午4:30:01
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * @return String paymentMode
	 * @date 2017年4月10日 下午4:30:01
	 */
	public String getPaymentMode() {
		return paymentMode;
	}

	/**
	 * @param paymentMode
	 *            the paymentMode to set
	 * @date 2017年4月10日 下午4:30:01
	 */
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	/**
	 * @return String paymentOrderCode
	 * @date 2017年4月10日 下午4:30:01
	 */
	public String getPaymentOrderCode() {
		return paymentOrderCode;
	}

	/**
	 * @param paymentOrderCode
	 *            the paymentOrderCode to set
	 * @date 2017年4月10日 下午4:30:01
	 */
	public void setPaymentOrderCode(String paymentOrderCode) {
		this.paymentOrderCode = paymentOrderCode;
	}

	/**
	 * @return String invoiceFlg
	 * @date 2017年4月10日 下午4:30:01
	 */
	public String getInvoiceFlg() {
		return invoiceFlg;
	}

	/**
	 * @param invoiceFlg
	 *            the invoiceFlg to set
	 * @date 2017年4月10日 下午4:30:01
	 */
	public void setInvoiceFlg(String invoiceFlg) {
		this.invoiceFlg = invoiceFlg;
	}

	/**
	 * @return String delFlg
	 * @date 2017年4月10日 下午4:30:01
	 */
	public String getDelFlg() {
		return delFlg;
	}

	/**
	 * @param delFlg
	 *            the delFlg to set
	 * @date 2017年4月10日 下午4:30:01
	 */
	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}

}
