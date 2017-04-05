<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/include/common_url.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注文完了 - 村のお店</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css2/add.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/order.css" type="text/css">
<style>
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
<table class="order_page_header"><tr>
	<td class="page_title">注文完了</td>
	<td></td>
	<td class="order_step">買物カゴ</td>
	<td class="order_step">ログイン</td>
	<td class="order_step">お客様情報</td>
	<td class="order_step">お届け先</td>
	<td class="order_step">お支払方法</td>
	<td class="order_step">確認</td>
	<td class="order_step here">完了</td>
</tr></table>
<center>
	<form name="main_form" action="">
		<div class="page_desc">
			ご注文を承りました。
		</div>
		<table class="order_body"><tr><td>
			ご注文番号　：　<s:property value="header.odr_id"/>
		</td></tr></table>
		<table class="button_area">
			<tr><td>
				<input type="button" value="　トップページに戻る　" onClick="goToTop()" class="button_sub">
			</td></tr>
		</table>
	</form></center>
</body>
</html>
