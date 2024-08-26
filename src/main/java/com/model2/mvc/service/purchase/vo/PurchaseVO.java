package com.model2.mvc.service.purchase.vo;

import java.sql.Date;

import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.vo.UserVO;


public class PurchaseVO {
	
	private int tranNo;				// TRAN_NO			PK	���Ź�ȣ 10000���� ����
	private ProductVO purchaseProd;	// PROD_NO			NN	���� ��ǰ ����
	private UserVO buyer;			// BUYER_ID			NN	������ ����
	private String paymentOption;	// PAYMENT_OPTION		���ҹ�� (1:���ݱ���, 2:�ſ뱸��)
	private String receiverName;	// RECEIVER_NAME		�޴»�� �̸�
	private String receiverPhone;	// RECEIVER_PHONE		�޴»�� ����ó
	private String dlvyAddr;		// DLVY_ADDR			����� �ּ�
	private String dlvyRequest;		// DLVY_REQUEST 		��� ��û����
	private String tranCode;		// TRAN_STATUS_CODE 	���� ���� �ڵ�
	//														(1:�Ǹ���, 2:���ſϷ�, 3:�����, 4:��ۿϷ�)
	private Date orderDate;			// ORDER_DATE			���� ����
	private String dlvyDate;		// DLVY_DATE			��� ��� ����
	
	
	public PurchaseVO(){
	}
	
	public UserVO getBuyer() {
		return buyer;
	}
	public void setBuyer(UserVO buyer) {
		this.buyer = buyer;
	}
	public String getDlvyAddr() {
		return dlvyAddr;
	}
	public void setDlvyAddr(String dlvyAddr) {
		this.dlvyAddr = dlvyAddr;
	}
	public String getDlvyDate() {
		return dlvyDate;
	}
	public void setDlvyDate(String dlvyDate) {
		this.dlvyDate = dlvyDate;
	}
	public String getDlvyRequest() {
		return dlvyRequest;
	}
	public void setDlvyRequest(String dlvyRequest) {
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

		return String.format("PurchaseVO : [tranNo] %d "
				+ "\n\t[puchaseProd] %s "
				+ "\n\t[buyer] %s "
				+ "\n\t[paymentOption] %s "
				+ "\n\t[receiverName] %s [receiverPhone] %s "
				+ "\n\t[dlvyAddr] %s [dlvyRequest] %s "
				+ "\n\t[tranCode] %s "
				+ "\n\t[orderDate] %s [dlvyDate] %s ", 
						tranNo, 
						purchaseProd.toString(), 
						buyer.toString(), 
						paymentOption, 
						receiverName, receiverPhone, 
						dlvyAddr, dlvyRequest, 
						tranCode, 
						orderDate, dlvyDate);
	}
}