package com.model2.mvc.service.purchase;
// W D 

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public interface PurchaseService {

	public PurchaseVO addPurchase(PurchaseVO purchaseVO);
	
	public PurchaseVO getPurchase(int tranNo);
	
	public PurchaseVO getPurchaseByProd(int prodNo);
	
	public HashMap<String, Object> getPurchaseList(SearchVO searchVO, String tranCode);
	
	public HashMap<String, Object> getSaleList(SearchVO searchVO);
	
	public PurchaseVO updatePurchase(PurchaseVO puchaseVO);
	
	public void updateTranCode(PurchaseVO purchaseVO);

}
// class end
