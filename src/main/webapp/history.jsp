<%@page import="java.util.Map"%>
<%@page import="com.model2.mvc.service.purchase.TranCodeMapper"%>
<%@page import="com.model2.mvc.service.product.vo.ProductVO"%>
<%@page import="com.model2.mvc.service.product.impl.ProductServiceImpl"%>
<%@page import="com.model2.mvc.service.product.ProductService"%>
<%@ page contentType="text/html; charset=EUC-KR" %>

<%
	Map<String, String> tranCodeMap = (TranCodeMapper.getInstance()).getMap();
%>

<html>
	<head>
	
		<title>열어본 상품 보기</title>
	
	</head>
	
	<body>
	
		<center><h2>[ 최근 본 상품 ]</h2></center>
		
	<br>
	
	<%
		request.setCharacterEncoding("euc-kr");
		response.setCharacterEncoding("euc-kr");
		
		System.out.println("\n>> history.jsp");
		
		String history = null;
		Cookie[] cookies = request.getCookies();
	%>
	
	
		
	<%	if (cookies!=null && cookies.length > 0) { 
		
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("history")) {
					history = cookie.getValue();
					System.out.println("\thistory= "+ ((history==null||history.equals(""))? "null" : history));
				}
			}
			
			if (history != null) { %>
		<center>
		<table border="1" cellspacing="0" cellpadding="5">
		<tr>
			<th>No.</th>
			<th>상품명</th>
			<th>가격</th>
			<th style="width: 70px;">현재상태</th>
		</tr>			
			
	<%			String[] historys = (history.trim()).split("&");
				int no = 1;
				for (String h : historys) {
					System.out.println("\th= "+ ((h==null||h.equals(""))? "null" : h));
					if (!h.equals("null") && !h.equals("")) {
						ProductService service = new ProductServiceImpl();
						ProductVO productVO = service.getProduct(Integer.parseInt(h));
						System.out.println("\tprodName= "+productVO.getProdName());
	%>
		<tr>
			<td><%= no++ %></td>
			<td><a href="/getProduct.do?prodNo=<%= h %>&menu=search"	target="rightFrame"><%= productVO.getProdName() %></a></td>
			<td><%= productVO.getPrice() %></td>
			<td><%= tranCodeMap.get(productVO.getProTranCode().trim()) %></td>
		</tr>
	<%
					}
				}
			} else { %>
				<p>최근 조회한 상품이 없습니다.</p>
				
	<%		} %>
	
		</table>
		</center>
	<%	} %>
	
	</body>
</html>