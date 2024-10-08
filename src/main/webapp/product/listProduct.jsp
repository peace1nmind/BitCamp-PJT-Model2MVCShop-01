<%@page import="com.model2.mvc.service.Paging"%>
<%@page import="java.util.Map"%>
<%@page import="com.model2.mvc.service.purchase.TranCodeMapper"%>
<%@page import="javax.swing.event.SwingPropertyChangeSupport"%>
<%@page import="java.util.List"%>
<%@page import="com.model2.mvc.service.product.vo.ProductVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.model2.mvc.common.SearchVO"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%	
	System.out.println("\n:: listProduct.jsp");

	String menu = request.getParameter("menu");
	System.out.println("\tmenu= "+menu);
	
	if (menu == null) {
		menu = "";
	}
	
	String title = (menu.equals("search"))? "상품 목록조회" : "상품관리";
	String navi = "getProduct.do";
	
	switch (menu) {
	case "search":
		
		break;
	
	case "manage":
		navi = "updateProductView.do";
		break;
	}

	HashMap<String, Object> map = (HashMap<String, Object>) request.getAttribute("map");
	
	// ListProductAction에서 SearchVO를 만들어서 보내기때문에 무조건 null이 아님
	SearchVO searchVO = (SearchVO) request.getAttribute("searchVO");
	
	int total = 0;
	List<ProductVO> productList = null;
	
	if (map != null) {
		// 검색된 전체 레코드의 양을 int로 형변환 (Object → Integer → int)
		total = ((Integer) map.get("count")).intValue();
		System.out.println("\ttotal= "+total);
		productList = (ArrayList<ProductVO>) map.get("list");
	}
	
	int currentPage = searchVO.getPage();
	int totalPage = 0;
	
	if (total>0) {
		// 전체를 pageUnit으로 나누고 올림하여 전체 페이지값을 계산
		totalPage = (int) Math.ceil(total*1.0 / searchVO.getPageUnit());
		System.out.println("\ttotalPage= "+totalPage);
	}
	
	if (searchVO.getSearchCondition() == null) {
		searchVO.setSearchCondition("0");
	}
	
	if (searchVO.getSearchKeyword() == null) {
		searchVO.setSearchKeyword("");
	}
	
	TranCodeMapper tranCodeMapper = TranCodeMapper.getInstance();
	Map<String, String> tranCodeMap = tranCodeMapper.getMap();
	
	Paging paging = (Paging) request.getAttribute("paging");
	paging.calculatePage(totalPage, currentPage);
	
%>
    
<!DOCTYPE html>
<html>
	<head>
		<title><%= title %></title>
		
		<link rel="stylesheet" href="/css/admin.css" type="text/css">
		
		<script type="text/javascript">
		<!--
		function fncGetProductList(){
			document.detailForm.submit();
		}
		-->
		</script>
	</head>

	<body bgcolor="#ffffff" text="#000000">
	
		<div style="width:98%; margin-left:10px;">
		
			<form name="detailForm" action="/listProduct.do?menu=<%= menu %>" method="post">
			
				<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
					<tr>
						<td width="15" height="37">
							<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
						</td>
						<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="93%" class="ct_ttl01">
									
											<%= title %>
									
									</td>
								</tr>
							</table>
						</td>
						<td width="12" height="37">
							<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
						</td>
					</tr>
				</table>
				
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
					<tr>
						
						<td align="right">
							<select name="searchCondition" class="ct_input_g" style="width:80px">
								<option value="0" <%= (searchVO.getSearchCondition().equals("0"))? "selected": "" %>>
									상품번호
								</option>
								<option value="1" <%= (searchVO.getSearchCondition().equals("1"))? "selected": "" %>>
									상품명
								</option>
								<option value="2" <%= (searchVO.getSearchCondition().equals("2"))? "selected": "" %>>
									상품가격
								</option>
							</select>
							<input type="text" name="searchKeyword" value="<%= searchVO.getSearchKeyword() %>" 
									class="ct_input_g" style="width:200px; height:19px" />
						</td>
						
						<td align="right" width="70">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="17" height="23">
										<img src="/images/ct_btnbg01.gif" width="17" height="23">
									</td>
									<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
										<a href="javascript:fncGetProductList();">검색</a>
									</td>
									<td width="14" height="23">
										<img src="/images/ct_btnbg03.gif" width="14" height="23">
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
					<tr>
						<td colspan="11" >전체 <%= total %> 건수, 현재 <%= currentPage %> 페이지</td>
					</tr>
					<tr>
						<td class="ct_list_b" width="100">No</td>
						<td class="ct_line02"></td>
						<td class="ct_list_b" width="150">상품명</td>
						<td class="ct_line02"></td>
						<td class="ct_list_b" width="150">가격</td>
						<td class="ct_line02"></td>
						<td class="ct_list_b">등록일</td>	
						<td class="ct_line02"></td>
						<td class="ct_list_b">현재상태</td>	
					</tr>
					<tr>
						<td colspan="11" bgcolor="808285" height="1"></td>
					</tr>
					
					<%	
						int no = productList.size();
					
						for(ProductVO productVO : productList) {
					%>
					<tr class="ct_list_pop">
						<td align="center"> <%= no-- %></td>
						<td></td>
								
						<td align="left">
							<a href="/<%= navi %>?prodNo=<%= productVO.getProdNo() %>&menu=<%= menu %>">
								<%= productVO.getProdName() %>
							</a>
						</td>
						
						<td></td>
						<td align="left"><%= productVO.getPrice() %></td>
						<td></td>
						<td align="left"><%= productVO.getRegDate() %></td>
						<td></td>
						
						<% String tranCode = productVO.getProTranCode().trim(); %>
						<td align="left">
							<%= tranCodeMap.get(tranCode) %>
							
						<% if (menu.equals("manage") && tranCode.equals("2")) { %>
							&nbsp;
							<a href="/updateTranCodeByProd.do?page=<%= currentPage %>&prodNo=<%= productVO.getProdNo() %>">
								배송하기
							</a>
						<% } %>
						
						</td>	
					</tr>
					<tr>
						<td colspan="11" bgcolor="D6D7D6" height="1"></td>
					</tr>	
					<%	} %>
					
				</table>
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
					<tr>
						<td align="center">
					
					<%	if (paging.isLeft()) { %>
					
							<a href="/listProduct.do?page=1
													&menu=<%= menu %>
													&searchCondition=<%= searchVO.getSearchCondition() %>
													&searchKeyword=<%= searchVO.getSearchKeyword() %>">
								<span>◀</span>
							</a>
							
							&nbsp;
							
							<a href="/listProduct.do?page=<%= paging.getStart()-1 %>
													&menu=<%= menu %>
													&searchCondition=<%= searchVO.getSearchCondition() %>
													&searchKeyword=<%= searchVO.getSearchKeyword() %>">
								<span>이전</span>
							</a>
							
					<%	} %>
					
							&nbsp;&nbsp;
							
					<%	for (int i=paging.getStart(); i<=paging.getEnd(); i++) { %>
							<a href="/listProduct.do?page=<%= i %>
													&menu=<%= menu %>
													&searchCondition=<%= searchVO.getSearchCondition() %>
													&searchKeyword=<%= searchVO.getSearchKeyword() %>" 
							<%= (currentPage==i)? "style='font-weight: bold; font-size: 15px'" : ""%>>
								<%= i %>
							</a>
					<%	} %>
					
							&nbsp;&nbsp;
							
					<%	if (paging.isRight()) { %>
							
							<a href="/listProduct.do?page=<%= paging.getEnd()+1 %>
													&menu=<%= menu %>
													&searchCondition=<%= searchVO.getSearchCondition() %>
													&searchKeyword=<%= searchVO.getSearchKeyword() %>">
								<span>다음</span>
							</a>
							
							&nbsp;
							
							<a href="/listProduct.do?page=<%= totalPage %>
													&menu=<%= menu %>
													&searchCondition=<%= searchVO.getSearchCondition() %>
													&searchKeyword=<%= searchVO.getSearchKeyword() %>">
								<span>▶</span>
							</a>
							
					<%	} %>
					
				    	</td>
					</tr>
				</table>
				<!--  페이지 Navigator 끝 -->
			
			</form>
		
		</div>
	</body>
</html>