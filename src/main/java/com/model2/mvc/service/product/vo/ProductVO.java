package com.model2.mvc.service.product.vo;

import java.sql.Date;


public class ProductVO {
	
	private int prodNo;
	private String prodName;
	private String prodDetail;
	private String manuDate;
	private int price;
	private String fileName;					// 상품 이미지 파일이름
	private Date regDate;
	private String proTranCode = "1";			// 상품 상태코드
	//											  (1:판매중, 2:구매완료, 3:배송중, 4:배송완료)
	
	
	// TODO proTranCode 처리에 대한 고민 (DB에 넣을지?, 상품관련 서비스에서 어떻게 처리할지)
	//		setProTranCode는 전부 "판매중"으로 써놓고 주석처리되어있음
	
	public ProductVO(){
	}
	
	public String getProTranCode() {
		return proTranCode;
	}
	public void setProTranCode(String proTranCode) {
		this.proTranCode = proTranCode;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getManuDate() {
		return manuDate;
	}
	public void setManuDate(String manuDate) {
		this.manuDate = manuDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getProdDetail() {
		return prodDetail;
	}
	public void setProdDetail(String prodDetail) {
		this.prodDetail = prodDetail;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getProdNo() {
		return prodNo;
	}
	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	// Override
	public String toString() {
		
		return String.format("ProductVO : [prodNo] %d [prodName] %s [prdoDetail] %s "
				+ "\n\t [price] %d [fileName] %s [regDate] %s "
				+ "\n\t [proTranCode] %s ", 
						prodNo, prodName, prodDetail, price, fileName, regDate,proTranCode);
	}	
}