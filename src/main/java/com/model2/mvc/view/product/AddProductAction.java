package com.model2.mvc.view.product;
// W D 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;

public class AddProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		System.out.println("\n>> AddProductAction");
		
		ProductVO productVO = new ProductVO();
		// prodNo = seq_product_prod_no.NEXTVAL
		productVO.setProdName(request.getParameter("prodName"));
		productVO.setProdDetail(request.getParameter("prodDetail"));
		
		String manuDate = "";
		for (String str : request.getParameter("manuDate").split("-")) {
			manuDate += str;
		}
		productVO.setManuDate(manuDate);
		
		productVO.setPrice(Integer.parseInt(request.getParameter("price")));
		productVO.setFileName(request.getParameter("fileName"));
		System.out.println(request.getParameter("fileName"));
		// regDate = SYSDATE
		productVO.setProTranCode("1");
		
		System.out.println("\t추가할 productVO= "+productVO);
		ProductService service = new ProductServiceImpl();
		ProductVO prodVO = service.addProduct(productVO);
		
		request.setAttribute("productVO", prodVO);
		System.out.println("\tDB 추가된 productVO= "+prodVO);
		
		return "forward:/product/addProduct.jsp";
	}
}
// class end
