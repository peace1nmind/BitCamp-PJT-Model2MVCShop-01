<%@page import="com.model2.mvc.service.product.vo.ProductVO"%>
<%@page import="com.model2.mvc.service.product.impl.ProductServiceImpl"%>
<%@page import="com.model2.mvc.service.product.ProductService"%>
<%@ page contentType="text/html; charset=EUC-KR" %>

<html>
	<head>
	
		<title>열어본 상품 보기</title>
	
	</head>
	
	<body>
	
		당신이 열어본 상품을 알고 있다
		
	<br>
	<br>
	
	<%
		request.setCharacterEncoding("euc-kr");
		response.setCharacterEncoding("euc-kr");
		
		System.out.println("\n>> history.jsp");
		
		String history = null;
		Cookie[] cookies = request.getCookies();
		
		if (cookies!=null && cookies.length > 0) {
			
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("history")) {
					history = cookie.getValue();
					System.out.println("\thistory= "+ ((history==null||history.equals(""))? "null" : history));
				}
			}
			
			if (history != null) {
				String[] historys = (history.trim()).split(",");
				for (String h : historys) {
					System.out.println("\th= "+ ((h==null||h.equals(""))? "null" : h));
					if (!h.equals("null") && !h.equals("")) {
						ProductService service = new ProductServiceImpl();
						ProductVO productVO = service.getProduct(Integer.parseInt(h));
						System.out.println("\tprodName= "+productVO.getProdName());
	%>
	<a href="/getProduct.do?prodNo=<%= h %>&menu=search"	target="rightFrame"><%= productVO.getProdName() %></a>
	<br>
	<%
					}
				}
			}
		}
	%>
	
	</body>
</html>