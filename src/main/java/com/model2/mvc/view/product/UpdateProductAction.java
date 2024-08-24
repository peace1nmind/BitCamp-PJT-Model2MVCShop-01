package com.model2.mvc.view.product;
// W D 

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
		
		ProductVO productVO = new ProductVO();
		/* 오류 수정필요 */
		productVO.setProdNo(Integer.parseInt(request.getParameter("prodNo")));
		productVO.setProdName(request.getParameter("prodName"));
		productVO.setProdName(request.getParameter("prodDetail"));
		
		String manuDate = "";
		for (String str : request.getParameter("manuDate").split("-")) {
			manuDate += str;
		}
		productVO.setManuDate(manuDate);
		
		productVO.setPrice(Integer.parseInt(request.getParameter("price")));
		productVO.setFileName(request.getParameter("fileName"));
		
		ProductService service = new ProductServiceImpl();
		System.out.println("\t수정 productVO= "+productVO);
		
		ProductVO prodVO = service.updateProduct(productVO);
		System.out.println("\tDB 수정된 productVO= "+prodVO);
		
		request.setAttribute("productVO", prodVO);
		
		return "forward:/product/updateProduct.jsp";
	}
}
// class end
