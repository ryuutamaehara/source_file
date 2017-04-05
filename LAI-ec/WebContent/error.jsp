<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/include/common_url.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>エラー - 村のお店</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css2/add.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css"/>
<style>
.message_area {
	width: 480px;
}
.message_cell {
	padding: 12px;
	text-align: left;
	text-wrap: normal;
	height: 120px;
	vertical-align: middle;
	border: 1px solid #bbb;
	background: #f0f0f0;
}
.button_cell {
	text-align: center;
}
</style>
<script>
function goToTop()
{
	location.href = "<s:property value="top_url"/>";
}
</script>
</head>
<body>
<table width="100%"><tr>
	<td>
		<h1 class="king">村のお店</h1>
		<div class="breadcrumbs"><s:a href="%{top_url}">トップ</s:a> &gt; ご注文手続き</div>
	</td><!--<td class="header_button_area">&nbsp;</td>-->
</tr></table>
<center>
	<form name="main_form" action="">
		<div class="page_desc">
			エラーが発生しました。
		</div>
		<table class="message_area">
			<tr><td class="error_message message_cell"><s:property value="error_message"/></td></tr>
			<tr><td>
				<table class="button_area">
					<tr><td class="button_cell">
						<input type="button" value="　トップページに戻る　" onClick="goToTop()" class="button_sub">
					</td></tr>
				</table>
			</td></tr>
		</table>
	</form></center>
</body>
</html>
