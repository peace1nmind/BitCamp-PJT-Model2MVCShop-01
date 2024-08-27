package com.model2.mvc.view.purchase;
// W D 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

// updateTranCode.do
// ���� �����ڵ� ���� ��û
public class UpdateTranCodeAction extends Action {

	// Field

	// Constructor
	public UpdateTranCodeAction() {
	}
	
	// Method
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("\n>> UpdateTranCodeAction");
		
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
		System.out.println("\ttranNo= "+tranNo);
		
		int page = Integer.parseInt(request.getParameter("page"));
		System.out.println("\tpage= "+page);
		
		// TODO
		/* ���������� �� ���̺� �� �ϰ� ���� �ѹ��� �ذ��ϴ� ��� ����ؾ��� */
		PurchaseService purchaseService = new PurchaseServiceImpl();
		PurchaseVO purchaseVO = purchaseService.getPurchase(tranNo);
		
		// 1. product pro_tran_codee ���� (4:��ۿϷ�)
		ProductVO productVO = purchaseVO.getPurchaseProd();
		ProductService productService = new ProductServiceImpl();
		productService.updateTranCode(productVO.getProdNo(), "4");
		
		// 2. transaction tran_status_code ���� (4:��ۿϷ�)
		purchaseVO.setTranCode("4");
		purchaseService.updateTranCode(purchaseVO);
		
		return "redirect:/listPurchase.do?page="+page;
	}

}
// class end
