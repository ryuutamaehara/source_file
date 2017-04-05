<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/include/common_url.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>マイページ - 村のお店</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css2/add.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css" type="text/css">
<style>
.area_title {
	margin-top: 16px;
	margin-bottom: 16px;
	width: 640px;
	text-align: left;
	font-size: 14px;
}
.oh_header {
	border-bottom: 1px solid #e0e0e0;
	background: #f0f0f0;
}
.oh_row {
	border-bottom: 1px solid #e0e0e0;
}
.oh_order_number_col {
	width: 160px;
	text-align: center;
	font-size: 12px;
}
.oh_date_col {
	width: 160px;
	text-align: center;
	font-size: 12px;
}
.oh_status_col {
	width: 160px;
	text-align: center;
	font-size: 12px;
}
.left_menu {
	padding-left: 12px;
	font-size: 12px;
	list-style-type: none;
}
</style>
<script>
</script>
</head>
<body>
<table width="100%"><tr><td><h1 class="king">村のお店</h1><div class="breadcrumbs"><s:a href="%{top_url}">トップ</s:a> &gt; マイページ</div></td><!--<td class="header_button_area">&nbsp;</td>--></tr></table>
<table class="mypage_header"><tr>
	<td class="page_title">マイページ</td>
</tr></table>
<table><tr>
	<td class="col_left">
		<ul class="left_menu">
			<li><a href="inquiry_list.html">お問合せ</a></li>
		</ul>
	</td>
	<td class="col_right">
		<form name="main_form" action="">
			<div class="area_title">お客様情報（<a href="customer_edit.html">編集</a>）（<a href="password_.html">パスワード変更</a>）</div>
			<table class="mypage_body">
				<tr><td class="label_col"><label>お名前</label></td>
					<td class="input_col"><s:property value="customer.customer_nm"/></td></tr>
				<tr><td class="label_col"><label>住所</label></td>
					<td class="input_col">
						<s:property value="customer.address_1"/><br>
						<s:property value="customer.address_2"/><br>
						<s:property value="customer.address_3"/> 丁目
						<s:property value="customer.address_4"/> 番地
					</td></tr>
			</table>
			<div class="area_title">ご注文履歴</div>
			<table class="mypage_body">
				<tr class="oh_header">
					<th class="oh_order_number_col">ご注文番号</th>
					<th class="oh_date_col">ご注文日</th>
					<th class="oh_status_col">ご注文状況</th>
				</tr>
				<s:iterator value="order_list">
				<s:url value="order_display.html?odr_id=%{odr_id}" var="odr_url"/>
				<tr class="oh_row">
					<td class="oh_order_number_col"><s:a href="%{odr_url}"><s:property value="odr_id"/></s:a></td>
					<td class="oh_date_col"><s:date name="%{accepted_on}" format="yyyy/MM/dd"/></td>
					<td class="oh_status_col"><s:property value="odr_status_nm"/></td>
				</tr>
				</s:iterator>
			</table>
		</form>
	</td>
</tr></table>
</body>
</html>
