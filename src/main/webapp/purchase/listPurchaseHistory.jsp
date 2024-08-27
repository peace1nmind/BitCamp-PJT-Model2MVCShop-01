<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="EUC-KR">
		<title>Insert title here</title>

	</head>
	
	<body>
		<%
			System.out.println("\n:: listPurchaseHistory.jsp");
		
			Object testStr = request.getAttribute("test");
		%>
		구매된 상품들을 보여줄거얌
		<br><br>
		<%= testStr %>
		
	</body>
</html>