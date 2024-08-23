package com.model2.mvc.view.product;
// W D 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;

public class GetProductAction extends Action {
	
	// 상품 상세조회
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		ProductService service = new ProductServiceImpl();
		ProductVO productVO = service.getProdut(prodNo);
		
		request.setAttribute("productVO", productVO);
		
		return "forward:/product/getProduct.jsp";
	}
}
// class end
