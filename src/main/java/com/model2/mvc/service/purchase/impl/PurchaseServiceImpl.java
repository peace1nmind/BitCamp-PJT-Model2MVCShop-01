package com.model2.mvc.service.purchase.impl;
// W D 

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class PurchaseServiceImpl implements PurchaseService {

	// Field
	private PurchaseDAO purchaseDAO;
	private ProductDAO productDAO = new ProductDAO();

	// Constructor
	public PurchaseServiceImpl() {
		System.out.println("\ncom.model2.mvc.service.purchase.impl");
		System.out.println("PurchaseServiceImpl");
		purchaseDAO = new PurchaseDAO();
	}
	
	// ���Ÿ� ���� BL
	@Override
	public PurchaseVO addPurchase(PurchaseVO purchaseVO) {
		
		purchaseDAO.insertPurchase(purchaseVO);
		System.out.println("\t"+purchaseVO+"\n\tDB ��ϿϷ�");
		PurchaseVO purVO = purchaseDAO.findPurchase();
		System.out.println("\tDB ����= "+purVO);
		
		productDAO.updateTranCode(	purVO.getPurchaseProd().getProdNo(),
									purchaseVO.getTranCode().trim());
		
		return purVO;
	}
	
	// �������� ����ȸ�� ���� BL
	@Override
	public PurchaseVO getPurchase(int tranNo) {
		
		return null;
	}
	
	// ���Ÿ�� ���⸦ ���� BL
	@Override
	public HashMap getPurchaseList(SearchVO searchVO, String tranCode) {
		
		return null;
	}
	
	// �ǸŸ�� ���⸦ ���� BL
	@Override
	public HashMap getSaleList(SearchVO searchVO) {
		
		return null;
	}
	
	// �������� ������ ���� BL
	@Override
	public PurchaseVO updatePurchase(PurchaseVO puchaseVO) {
		
		return null;
	}
	
	// ���Ż����ڵ� ������ ���� BL
	@Override
	public void updateTranCode(PurchaseVO purchaseVO) {
		

	}

}
// class end
