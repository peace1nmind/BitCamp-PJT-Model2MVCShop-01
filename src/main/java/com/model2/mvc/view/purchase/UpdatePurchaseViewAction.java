package com.model2.mvc.view.purchase;
// W D 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

// updatePurchaseView.do
// ���� ���� ������ ���� ȭ�� ��û
public class UpdatePurchaseViewAction extends Action {
	
	// Field

	// Constructor

	// Method
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("\n>> UpdatePurchaseViewAction");
		
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
		System.out.println("\ttranNO= "+tranNo);
		
		PurchaseService service = new PurchaseServiceImpl();
		PurchaseVO purchaseVO = service.getPurchase(tranNo);
		System.out.println("\tpurchaseVO= "+purchaseVO);
		
		request.setAttribute("purchaseVO", purchaseVO);
		
		return "forward:/purchase/updatePurchaseView.jsp";
	}

}
// class end
