package com.model2.mvc.service.purchase.vo;

import java.sql.Date;

import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.vo.UserVO;


public class PurchaseVO {
	
	private int tranNo;				// TRAN_NO			PK	구매번호 10000부터 시작
	private ProductVO purchaseProd;	// PROD_NO			NN	구매 물품 정보
	private UserVO buyer;			// BUYER_ID			NN	구매자 정보
	private String paymentOption;	// PAYMENT_OPTION		지불방식 (1:현금구매, 2:신용구매)
	private String receiverName;	// RECEIVER_NAME		받는사람 이름
	private String receiverPhone;	// RECEIVER_PHONE		받는사람 연락처
	private String dlvyAddr;		// DLVY_ADDR			배송지 주소
	private String dlvyRequest;		// DLVY_REQUEST 		배송 요청사항
	private String tranCode;		// TRAN_STATUS_CODE 	구매 상태 코드
	//														(1:판매중, 2:구매완료, 3:배송중, 4:배송완료)
	private Date orderDate;			// ORDER_DATE			구매 일자
	private String dlvyDate;		// DLVY_DATE			배송 희망 일자
	
	
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