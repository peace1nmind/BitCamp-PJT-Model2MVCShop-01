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
		���ŵ� ��ǰ���� �����ٰž�
		<br><br>
		<%= testStr %>
		
	</body>
</html>