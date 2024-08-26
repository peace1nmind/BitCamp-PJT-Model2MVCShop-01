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
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

// addPurchase.do
// 구매 요청
public class AddPurchaseAction extends Action {

	// Field

	// Constructor
	
	// Method
	@Override
	public String execute(	HttpServletRequest request, HttpServletResponse response) 
							throws Exception {
		
		System.out.println("\n>> AddpurchaseAction");
		
		UserService userService = new UserServiceImpl();
		ProductService productService = new ProductServiceImpl();
		PurchaseService purchaseService = new PurchaseServiceImpl();
		
		UserVO userVO = userService.getUser(request.getParameter("buyerId"));
		ProductVO productVO = productService.getProduct(Integer.parseInt(request.getParameter("prodNo")));
		PurchaseVO purchaseVO = new PurchaseVO();
		
//		purchaseVO.setTranNo(seq_transaction_tran_no.NEXTVAL);
		purchaseVO.setPurchaseProd(productVO);
		purchaseVO.setBuyer(userVO);
		purchaseVO.setPaymentOption(request.getParameter("paymentOption"));
		purchaseVO.setReceiverName(request.getParameter("receiverName"));
		
		String receiverPhone = "";
		
		for (String p : request.getParameter("receiverPhone").split("-")) {
			receiverPhone += p;
		}
		
		purchaseVO.setReceiverPhone(receiverPhone);
		purchaseVO.setDlvyAddr(request.getParameter("dlvyAddr"));
		purchaseVO.setDlvyRequest(request.getParameter("dlvyRequest"));
		purchaseVO.setTranCode("2");
//		purchaseVO.setOrderDate(SYSDATE);
		
		String dlvyDate = "";
		
		for (String d : request.getParameter("dlvyDate").split("-")) {
			dlvyDate += d;
		}
		
		purchaseVO.setDlvyDate(dlvyDate);
		
		PurchaseVO purVO = purchaseService.addPurchase(purchaseVO);
		System.out.println("\t최종 purchaseVO= "+purVO);
		request.setAttribute("purchaseVO", purVO);
		
		return "forward:/purchase/addPurchase.jsp";
	}

}
// class end
