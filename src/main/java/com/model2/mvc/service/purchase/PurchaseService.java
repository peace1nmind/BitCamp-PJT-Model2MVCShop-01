package com.model2.mvc.service.purchase;
// W D 

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public interface PurchaseService {

	public PurchaseVO addPurchase(PurchaseVO purchaseVO);
	
	public PurchaseVO getPurchase(int tranNo);
	
	public HashMap getPurchaseList(SearchVO searchVO, String tranCode);
	
	public HashMap getSaleList(SearchVO searchVO);
	
	public PurchaseVO updatePurchase(PurchaseVO puchaseVO);
	
	public void updateTranCode(PurchaseVO purchaseVO);

}
// class end
