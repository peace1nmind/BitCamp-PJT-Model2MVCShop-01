package com.model2.mvc.view.purchase;
// W D 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;

// addPurchaseView.do
// 구매를 위한 화면 요청
public class AddPurchaseViewAction extends Action {

	// Field

	// Constructor
	public AddPurchaseViewAction() {
	}
	
	// Method
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("\n>> AddpurchaseViewAction");
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo")) ;
		System.out.println("\tprodNo= "+prodNo);
		
		ProductService service = new ProductServiceImpl();
		ProductVO productVO = service.getProduct(prodNo);
		System.out.println("\tproductVO= "+productVO);
		
		request.setAttribute("productVO", productVO);
		
		return "forward:/purchase/addPurchaseView.jsp";
	}

}
// class end
