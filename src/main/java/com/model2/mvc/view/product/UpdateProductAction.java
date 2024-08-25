package com.model2.mvc.view.product;
// W D 

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;

public class UpdateProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		System.out.println("\n>> UpdateProductAction");
		
		ProductService service = new ProductServiceImpl();
		Enumeration<String> paramNames = request.getParameterNames();
		
		System.out.print("paramNames= ");
		while (paramNames.hasMoreElements()) {
			System.out.print(paramNames.nextElement() + " ");
		}
		System.out.println();
		
		// prodNo로 DB에서 기존 productVO를 가져오고 그걸 수정
		ProductVO productVO = service.getProduct(Integer.parseInt(request.getParameter("prodNo")));
		System.out.println("수정 전 productVO= "+productVO);
		
		productVO.setProdName(request.getParameter("prodName"));
		productVO.setProdName(request.getParameter("prodDetail"));
		
		String manuDate = "";
		for (String str : request.getParameter("manuDate").split("-")) {
			manuDate += str;
		}
		
		productVO.setManuDate(manuDate);
		
		productVO.setPrice(Integer.parseInt(request.getParameter("price")));
		productVO.setFileName(request.getParameter("fileName"));
		
		
		System.out.println("\t수정 후 productVO= "+productVO);
		
		ProductVO prodVO = service.updateProduct(productVO);
		System.out.println("\tDB 수정된 productVO= "+prodVO);
		
		request.setAttribute("productVO", prodVO);
		
		return "forward:/product/updateProduct.jsp";
	}
}
// class end
