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
	
	// ��ǰ ����ȸ
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
		
		// ��Ű���� ���� history�� �����ͼ� ���纻 ��ǰ�� prodNo�� ����
		Cookie[] cookies = request.getCookies();
		String history = ""+prodNo;
		
		/* cookie���� �ذ� �ʿ� history���� ���µ� */
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie!=null && cookie.getName().equals("history")) {
					System.out.println("\t���� history= "+cookie.getValue());
					history += /*(!cookie.getValue().contains(history))?*/ ", "+cookie.getValue() /*: ""*/;
					System.out.println("\t�߰��� history= "+history);
					cookie.setValue(history);
					System.out.println("\t���� ����� cookie= "+cookie.getValue());
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
