<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%
	System.out.println("\n:: getPurchase.jsp");

%>
    
<!DOCTYPE html>

<html>
	<head>
	
		<title>배송정보 조회</title>
		
		<link rel="stylesheet" href="/css/admin.css" type="text/css">
	
	</head>
	
	<body bgcolor="#ffffff" text="#000000">
	
		<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
			<tr>
				<td width="15" height="37">
					<img src="/images/ct_ttl_img01.gif" width="15" height="37">
				</td>
				<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="93%" class="ct_ttl01">배송정보 조회</td>
						<td width="20%" align="right">&nbsp;</td>
					</tr>
				</table>
				</td>
				<td width="12" height="37"><img src="/images/ct_ttl_img03.gif" width="12" height="37"></td>
			</tr>
		</table>
		
		<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:13px;">
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			<tr>
				<td width="104" class="ct_write">
					아이디 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
				</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="105">user08</td>
							<td>	</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			
			<tr>
				<td width="104" class="ct_write">
					이름 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle">
				</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01">SCOTT</td>
			</tr>
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			
			<tr>
				<td width="104" class="ct_write">주소</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01">Ko</td>
			</tr>
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			<tr>
				<td width="104" class="ct_write">휴대전화번호</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01">null</td>
			</tr>
		
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			<tr>
				<td width="104" class="ct_write">이메일 </td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01">
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="26">user08@abc.com</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			
			<tr>
				<td width="104" class="ct_write">가입일자</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01">2024-08-22</td>
			</tr>
		
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
		</table>
		
		<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top:10px;">
			<tr>
				<td width="53%"></td>
				<td align="right">
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
						
							<td width="17" height="23">
								<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
							</td>
							<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
							<%-- userId 값 변경 --%>
								<a href="/updateUserView.do?userId=user08">수정</a>
							</td>
							<td width="14" height="23">
								<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
							</td>
							
							<td width="30"></td>	
											
							<td width="17" height="23">
								<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
							</td>
							<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
								<a href="javascript:history.go(-1);">확인</a>
							</td>
							<td width="14" height="23">
								<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
							</td>
							
						</tr>
					</table>
				</td>
			</tr>
		</table>
	
	</body>
	
</html>
		