package com.model2.mvc.service.purchase.impl;
// W D 

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class PurchaseServiceImpl implements PurchaseService {

	// Field

	// Constructor
	public PurchaseServiceImpl() {
	}
	
	// 구매를 위한 BL
	@Override
	public PurchaseVO addPurchase(PurchaseVO purchaseVO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// 구매정보 상세조회를 위한 BL
	@Override
	public PurchaseVO getPurchase(int tranNo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// 구매목록 보기를 위한 BL
	@Override
	public HashMap getPurchaseList(SearchVO searchVO, String tranCode) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// 판매목록 보기를 위한 BL
	@Override
	public HashMap getSaleList(SearchVO searchVO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// 구매정보 수정을 위한 BL
	@Override
	public PurchaseVO updatePurchase(PurchaseVO puchaseVO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// 구매상태코드 수정을 위한 BL
	@Override
	public void updateTranCode(PurchaseVO purchaseVO) {
		// TODO Auto-generated method stub

	}

}
// class end
