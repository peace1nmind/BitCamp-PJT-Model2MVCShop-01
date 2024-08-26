package com.model2.mvc.view.product;
// W D 

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
		String prodNo = request.getParameter("prodNo");
		
		System.out.println(String.format("\tprodNo= %s & menu= %s", prodNo, menu));
		
		ProductService service = new ProductServiceImpl();
		ProductVO productVO = service.getProduct(Integer.parseInt(prodNo));
		
		request.setAttribute("productVO", productVO);
		
		// 쿠키에서 기존 history를 가져와서 현재본 상품의 prodNo랑 더함
		Cookie[] cookies = request.getCookies();
		Cookie historyCookie = new Cookie("history", null);
		
		/* cookie문제 해결 필요 history값이 리셋됨 */
		if (cookies!=null && cookies.length>0) {
			for (Cookie cookie : cookies) {
				historyCookie = (cookie.getName().equals("history"))? cookie : historyCookie;
			}
		}
		
		String historyCookieValue = historyCookie.getValue();
		String value = "";
		
		if (historyCookieValue == null) {
			value = prodNo;
//			historyCookie.setValue(prodNo);
			
		} else {
			if (!historyCookieValue.contains(prodNo)) {
				value = prodNo+"&"+historyCookieValue;
//				historyCookie.setValue(prodNo+"&"+historyCookieValue);
				
			} else {
				for (String s : historyCookieValue.split(prodNo)) {
					value += s;
				}
				
				String[] splittedValue = value.split("&");
				value = "";
				
				for (int i = 0; i < splittedValue.length; i++) {
					if (i < splittedValue.length -1) {
						value += splittedValue[i] + "&";
					} else {
						value += splittedValue[i];
					}
				}
				
				value = prodNo + "&" + value;
				
			}
			
		}
		
		System.out.println("\tvalue= "+value);
		historyCookie.setValue(value);
//		System.out.println("historyCookieValue= "+historyCookieValue);
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
