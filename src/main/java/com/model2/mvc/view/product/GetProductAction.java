package com.model2.mvc.view.product;
// W D 

import java.util.ArrayList;
import java.util.List;

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
		String prodNo = request.getParameter("prodNo");
		
		System.out.println(String.format("\tprodNo= %s & menu= %s", prodNo, menu));
		
		ProductService service = new ProductServiceImpl();
		ProductVO productVO = service.getProduct(Integer.parseInt(prodNo));
		
		request.setAttribute("productVO", productVO);
		
		// ��Ű���� ���� history�� �����ͼ� ���纻 ��ǰ�� prodNo�� ����
		Cookie[] cookies = request.getCookies();
		Cookie historyCookie = new Cookie("history", null);
		
		/* cookie���� �ذ� �ʿ� history���� ���µ� */
		if (cookies!=null && cookies.length>0) {
			for (Cookie cookie : cookies) {
				historyCookie = (cookie.getName().equals("history"))? cookie : historyCookie;
			}
		}
		
		System.out.println("historyCookie= "+historyCookie);
		
		if (historyCookie.getValue() == null) {
			historyCookie.setValue(prodNo);
			
		} else {
			if (!historyCookie.getValue().contains(prodNo)) {
				historyCookie.setValue(prodNo+"&"+historyCookie.getValue());
				
			}
			
		}
		
		System.out.println("historyCookieValue= "+historyCookie.getValue());
		response.addCookie(historyCookie);
		
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
