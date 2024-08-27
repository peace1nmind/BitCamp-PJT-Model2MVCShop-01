package com.model2.mvc.view.purchase;
// W D 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class UpdateTranCodeByProdAction extends Action {

	// Field
	
	
	// Constructor
	public UpdateTranCodeByProdAction() {
	}
	
	// �ǸŰ������� ���ſϷ� �� ��������� �����ϴ� Action
	// Method
	@Override
	public String execute(	HttpServletRequest request, HttpServletResponse response) 
							throws Exception {
		
		System.out.println("\n>> UpdateTranCodeByProdAction");
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		System.out.println("\tprodNo= "+prodNo);
		
		int page = Integer.parseInt(request.getParameter("page"));
		System.out.println("\tpage= "+page);
		
		// TODO
		/* ���������� �� ���̺� �� �ϰ� ���� �ѹ��� �ذ��ϴ� ��� ����ؾ��� */
		
		// 1. product pro_tran_codee ���� (3:�����)
		ProductService productService = new ProductServiceImpl();
		productService.updateTranCode(prodNo, "3");
		
		// 2. transaction tran_status_code ���� (3:�����)
		PurchaseService purchaseService = new PurchaseServiceImpl();
		PurchaseVO purchaseVO = purchaseService.getPurchaseByProd(prodNo);
		purchaseVO.setTranCode("3");
		purchaseService.updateTranCode(purchaseVO);
		
		return "redirect:/listProduct.do?page="+page+"&menu=manage";
	}

}
// class end
