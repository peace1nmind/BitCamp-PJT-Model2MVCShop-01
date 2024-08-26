package com.model2.mvc.view.purchase;
// W D 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

// getPurchase.do
// 구매 상세정보 요청
public class GetPurchaseAction extends Action {

	// Field

	// Constructor
	public GetPurchaseAction() {
	}

	// Method
	@Override
	public String execute(	HttpServletRequest request, HttpServletResponse response) 
							throws Exception {
		
		System.out.println("\n>> GetPurchaseAction");
		
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
		System.out.println("\ttranNo= "+tranNo);
		
		PurchaseService service = new PurchaseServiceImpl();
		PurchaseVO purchaseVO = service.getPurchase(tranNo);
		System.out.println("\tpurchaseVO= "+purchaseVO);
		
		request.setAttribute("purchaseVO", purchaseVO);

		return "forward:/purchase/getPurchase.jsp";
	}

}
// class end
