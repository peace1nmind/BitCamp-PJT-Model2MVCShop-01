package com.model2.mvc.view.purchase;
// W D 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

// updatePurchase.do
// 구매정보 수정 요청
public class UpdatePurchaseAction extends Action {
	
	// Field

	// Constructor

	// Method
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("\n>> UpdatePurchaseAction");
		
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
		System.out.println("\ttranNo= "+tranNo);
		
		PurchaseService service = new PurchaseServiceImpl();
		PurchaseVO purchaseVO = service.getPurchase(tranNo);
		System.out.println("\t기존 purchaseVO= "+purchaseVO);
		
		purchaseVO.setPaymentOption(request.getParameter("paymentOption"));
		purchaseVO.setReceiverName(request.getParameter("receiverName"));
		
		String receiverPhone = "";
		for (String p : request.getParameter("receiverPhone").split("-")) {
			receiverPhone += p;
		}
		purchaseVO.setReceiverPhone(receiverPhone);
		
		purchaseVO.setDlvyAddr(request.getParameter("dlvyAddr"));
		purchaseVO.setDlvyRequest(request.getParameter("dlvyRequest"));
		purchaseVO.setDlvyDate(request.getParameter("dlvyDate"));
		
		System.out.println("\t수정한 purchaseVO= "+purchaseVO);
		
		PurchaseVO purVO = service.updatePurchase(purchaseVO);
		System.out.println("\t최종 수정된 purchaseVO"+purVO);
		
		request.setAttribute("purchaseVO", purVO);
		
		return "forward:/purchase/updatePurchase.jsp";
	}

}
// class end
