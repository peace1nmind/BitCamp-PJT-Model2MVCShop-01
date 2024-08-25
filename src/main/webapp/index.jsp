<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=euc-kr" %>

<%
	System.out.println("\n:: index.jsp");	
%>
<%--	boolean existHistory = false;
	Cookie[] cookies = request.getCookies();
	
	if (cookies != null && cookies.length > 0) {
		for (Cookie cookie : cookies) {
			existHistory = (cookie.getName().equals("history"))? true : existHistory;
		
			if (existHistory) {
				String cookieValue = cookie.getValue();
				System.out.println("\thistory= "+ ((cookieValue==null||cookieValue.equals(""))? "null" : cookieValue));
			}
		}
	}
	
	System.out.println("\texistHistory= "+existHistory);
	
	if (!existHistory) {
		response.addCookie(new Cookie("history", new ArrayList<String>().toString()));
	}

--%>

<html>

	<head>
		<title>Model2 MVC Shop</title>
	</head>
	
	<frameset rows="80,*" cols="*" frameborder="NO" border="0" framespacing="0">
	  
	  <frame src="/layout/top.jsp" name="topFrame" scrolling="NO" noresize >
	  
	  <frameset rows="*" cols="160,*" framespacing="0" frameborder="NO" border="0">
	    <frame src="/layout/left.jsp" name="leftFrame" scrolling="NO" noresize>
	    <frame src="" name="rightFrame"  scrolling="auto">
	  </frameset>
	
	</frameset>
	
	<noframes>
	<body>
	</body>
	</noframes>

</html>