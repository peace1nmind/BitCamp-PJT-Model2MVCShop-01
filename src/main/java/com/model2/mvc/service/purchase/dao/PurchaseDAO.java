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
	// �������� ����ȸ�� ���� DBMS
	public PurchaseVO findPurchase(int tranNo) {
		return null;
	}
	
	// ���Ÿ�� ���⸦ ���� DBMS
	public HashMap getPurchaseList(SearchVO searchVO, String tranCode) {
		return null;	
	}
	
	// �ǸŸ�� ���⸦ ���� DBMS
	public HashMap getSaleList(SearchVO searchVO) {
		return null;	
	}
	
	// ���Ÿ� ���� DBMS
	public void insertPurchase(PurchaseVO purchaseVO) {
		
	}
	
	// �������� ������ ���� DBMS
	public void updatePurchase(PurchaseVO purchaseVO) {
		
	}
	
	// ���Ż����ڵ� ������ ���� DBMS
	public void updateTranCode(PurchaseVO purchaseVO) {
		
	}
	
}
// class end
