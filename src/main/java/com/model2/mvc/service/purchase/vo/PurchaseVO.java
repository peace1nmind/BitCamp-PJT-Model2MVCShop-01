package com.model2.mvc.service.purchase.vo;

import java.sql.Date;

import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.vo.UserVO;


public class PurchaseVO {
	
	private int tranNo;					// PK	
	private UserVO buyer;				// NN
	private ProductVO purchaseProd;		// NN
	private String paymentOption;
	private String receiverName;
	private String receiverPhone;
	private String dlvyAddr;
	private String dlvyRequest;
	private String tranCode;
	private Date orderDate;
	private String dlvyDate;
	
	
	public PurchaseVO(){
	}
	
	public UserVO getbuyer() {
		return buyer;
	}
	public void setbuyer(UserVO buyer) {
		this.buyer = buyer;
	}
	public String getdlvyAddr() {
		return dlvyAddr;
	}
	public void setdlvyAddr(String dlvyAddr) {
		this.dlvyAddr = dlvyAddr;
	}
	public String getdlvyDate() {
		return dlvyDate;
	}
	public void setdlvyDate(String dlvyDate) {
		this.dlvyDate = dlvyDate;
	}
	public String getdlvyRequest() {
		return dlvyRequest;
	}
	public void setdlvyRequest(String dlvyRequest) {
		this.dlvyRequest = dlvyRequest;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getPaymentOption() {
		return paymentOption;
	}
	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption;
	}
	public ProductVO getPurchaseProd() {
		return purchaseProd;
	}
	public void setPurchaseProd(ProductVO purchaseProd) {
		this.purchaseProd = purchaseProd;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public String getTranCode() {
		return tranCode;
	}
	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}
	public int getTranNo() {
		return tranNo;
	}
	public void setTranNo(int tranNo) {
		this.tranNo = tranNo;
	}
	
	@Override
	public String toString() {
		return "PurchaseVO [buyer=" + buyer + ", dlvyAddr=" + dlvyAddr
				+ ", dlvyDate=" + dlvyDate + ", dlvyRequest=" + dlvyRequest
				+ ", orderDate=" + orderDate + ", paymentOption="
				+ paymentOption + ", purchaseProd=" + purchaseProd
				+ ", receiverName=" + receiverName + ", receiverPhone="
				+ receiverPhone + ", tranCode=" + tranCode + ", tranNo="
				+ tranNo + "]";
	}
}