<%@page import="com.model2.mvc.service.product.vo.ProductVO"%>
<%@page import="com.model2.mvc.service.user.vo.UserVO"%>
<%@page import="com.model2.mvc.service.purchase.vo.PurchaseVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%
	System.out.println("\n:: getPurchase.jsp");
	
	PurchaseVO purchaseVO = (PurchaseVO) request.getAttribute("purchaseVO");
	System.out.println("\tpurchaseVO= "+purchaseVO);
	
	UserVO userVO = purchaseVO.getBuyer();
	ProductVO productVO = purchaseVO.getPurchaseProd();

%>
    
<!DOCTYPE html>

<html>

	<head>
	
		<title>구매상세조회</title>
		
		<link rel="stylesheet" href="/css/admin.css" type="text/css">
	
	</head>
	
	<body bgcolor="#ffffff" text="#000000">
	
		<table width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="15" height="37">
					<img src="/images/ct_ttl_img01.gif"	width="15" height="37"/>
				</td>
				<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="93%" class="ct_ttl01">구매상세조회</td>
							<td width="20%" align="right">&nbsp;</td>
						</tr>
					</table>
				</td>
				<td width="12" height="37">
					<img src="/images/ct_ttl_img03.gif"	width="12" height="37"/>
				</td>
			</tr>
		</table>
		
		<%-- 변경불가항목 --%>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 13px;">
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			<tr>
				<td width="104" class="ct_write">
					물품번호 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
				</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="105">
								<%= productVO.getProdNo() %>
							</td>
							<td></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			
			<tr>
				<td width="104" class="ct_write">
					상품명 <img	src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
				</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01"><%= productVO.getProdName() %></td>
			</tr>
			
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			
			<tr>
				<td width="104" class="ct_write">
					구매자아이디 <img	src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
				</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01"><%= userVO.getUserId() %></td>
			</tr>
			
			<%-- 변경가능항목--%>
			
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
		
			<tr>
				<td width="104" class="ct_write">구매방법</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01">
					<%= (purchaseVO.getPaymentOption().trim().equals("1"))? "현금구매" : "신용구매" %>
				</td>
			</tr>
			
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			
			<tr>
				<td width="104" class="ct_write">
					결제금액 <img	src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
				</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01"><%= productVO.getPrice() %></td>
			</tr>
			
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			<tr>
				<td width="104" class="ct_write">구매자이름</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01"><%= purchaseVO.getReceiverName() %></td>
			</tr>
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			<tr>
				<td width="104" class="ct_write">구매자연락처</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01"><%= purchaseVO.getReceiverPhone() %></td>
			</tr>
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			<tr>
				<td width="104" class="ct_write">구매자주소</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01"><%= purchaseVO.getDlvyAddr() %></td>
			</tr>
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			<tr>
				<td width="104" class="ct_write">구매요청사항</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01"><%= purchaseVO.getDlvyRequest() %></td>
			</tr>
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			<tr>
				<td width="104" class="ct_write">배송희망일</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01"><%= purchaseVO.getDlvyDate() %></td>
			</tr>
		
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
		
			<tr>
				<td width="104" class="ct_write">
					주문일 <img	src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
				</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01"><%= purchaseVO.getOrderDate() %></td>
			</tr>
		
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			
		</table>
		
		<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
			<tr>
				<td width="53%"></td>
				<td align="right">
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="17" height="23">
								<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
							</td>
							<td background="/images/ct_btnbg02.gif" class="ct_btn01"	style="padding-top: 3px;">
								<a href="/updatePurchaseView.do?tranNo=<%= purchaseVO.getTranNo() %>">수정</a>
							</td>
							<td width="14" height="23">
								<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
							</td>
							<td width="30"></td>
							<td width="17" height="23">
								<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
							</td>
							<td background="/images/ct_btnbg02.gif" class="ct_btn01"	style="padding-top: 3px;">
								<a href="/listPurchase.do">확인</a>
							</td>
							<td width="14" height="23">
								<img src="/images/ct_btnbg03.gif"width="14" height="23"/>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	
	</body>
	
</html>