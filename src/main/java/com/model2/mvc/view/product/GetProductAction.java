package com.model2.mvc.view.product;
// W D 

import javax.servlet.http.Cookie;
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
		
		System.out.println("\n>> GetProductAction");
		
		String menu = request.getParameter("menu");
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		System.out.println(String.format("\tprodNo= %d & menu= %s", prodNo, menu));
		
		ProductService service = new ProductServiceImpl();
		ProductVO productVO = service.getProduct(prodNo);
		
		request.setAttribute("productVO", productVO);
		
		// 쿠키에서 기존 history를 가져와서 현재본 상품의 prodNo랑 더함
		Cookie[] cookies = request.getCookies();
		String history = ""+prodNo;
		
		/* cookie문제 해결 필요 history값이 리셋됨 */
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie!=null && cookie.getName().equals("history")) {
					System.out.println("\t기존 history= "+cookie.getValue());
					history += /*(!cookie.getValue().contains(history))?*/ ", "+cookie.getValue() /*: ""*/;
					System.out.println("\t추가된 history= "+history);
					cookie.setValue(history);
					System.out.println("\t새로 저장된 cookie= "+cookie.getValue());
				}
			}
		}
		
		if (menu == null) {
			menu = "";
		}
		
		switch (menu) {
		case "search":
			return "forward:/product/getProduct.jsp";
			
		case "manage" :
			return "forward:/product/updateProductView.jsp";

		default:
			return "forward:/product/getProduct.jsp";
		}
		
	}
}
// class end
