package com.model2.mvc.service.purchase.dao;
// W D 

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class PurchaseDAO {

	// Field

	// Constructor
	public PurchaseDAO() {
	}

	// Method
	// 구매정보 상세조회를 위한 DBMS
	public PurchaseVO findPurchase(int tranNo) {
		return null;
	}
	
	// 구매목록 보기를 위한 DBMS
	public HashMap getPurchaseList(SearchVO searchVO, String tranCode) {
		return null;	
	}
	
	// 판매목록 보기를 위한 DBMS
	public HashMap getSaleList(SearchVO searchVO) {
		return null;	
	}
	
	// 구매를 위한 DBMS
	public void insertPurchase(PurchaseVO purchaseVO) {
		
	}
	
	// 구매정보 수정을 위한 DBMS
	public void updatePurchase(PurchaseVO purchaseVO) {
		
	}
	
	// 구매상태코드 수정을 위한 DBMS
	public void updateTranCode(PurchaseVO purchaseVO) {
		
	}
	
}
// class end
