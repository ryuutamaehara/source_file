<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/include/common_url.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ご注文の確認 - 村のお店</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css2/add.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/order.css" type="text/css">
<style>
</style>
<script>
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
	<td class="page_title">ご注文の確認</td>
	<td></td>
	<td class="order_step">買物カゴ</td>
	<td class="order_step">ログイン</td>
	<td class="order_step">お客様情報</td>
	<td class="order_step">お届け先</td>
	<td class="order_step">お支払方法</td>
	<td class="order_step here">確認</td>
	<td class="order_step">完了</td>
</tr></table>
<center>
	<form name="main_form" action="" method="post">
		<div class="page_desc">
			ご注文内容をご確認の上、「注文を確定する」ボタンをクリックしてください。
		</div>
		<table class="cart_list">
			<tr class="cart_header">
				<th class="cart_h_prd_name" colspan="2">商品名</th>
				<th class="cart_h_unit_price">単価</th>
				<th class="cart_h_quantity">個数</th>
				<th class="cart_h_line_price">金額</th>
				<th class="cart_h_del_button"/>
			</tr>
			<s:iterator value="detail_list">
			<s:url value="/prd/%{prd.prd_nm_for_url}/%{prd.prd_cd}.html" var="prd_url"/>
			<tr>
				<td class="cart_prd_img">
					<s:a href="%{prd_url}"><img src="<s:url value="/prd/images/%{prd.prd_cd}.png"/>"></s:a>
				</td>
				<td class="cart_prd_name"><s:a href="%{prd_url}"><s:property value="prd.prd_nm"/></s:a></td>
				<td class="cart_unit_price price">
					<s:text name="format.number">
						<s:param name="value" value="prd.selling_price"/>
					</s:text>
				</td>
				<td class="cart_quantity">
					<s:property value="detail.quantity"/>
				</td>
				<td class="cart_line_price price">
					<s:text name="format.number">
						<s:param name="value" value="detail.total_price"/>
					</s:text>
				</td>
				<td class="cart_del_button"/>
			</tr>
			</s:iterator>
		</table>
		<table class="cart_footer">
			<tr>
				<td >&nbsp;</td>
				<td class="cart_footer_item_label">合計</td>
				<td class="cart_footer_item price">
					<s:text name="format.number">
						<s:param name="value" value="header.product_total_price"/>
					</s:text>
				</td>
				<td class="cart_footer_right_blank">&nbsp;</td>
			</tr>
		</table>
		<table class="order_body">
			<tr><td class="label_col"><label>お名前</label></td>
				<td class="input_col"><s:property value="header.customer_nm"/></td></tr>
			<tr><td class="label_col"><label>住所</label></td>
				<td class="input_col">
					<s:property value="header.address_1"/><br>
					<s:property value="header.address_2"/><br>
					<s:property value="header.address_3"/> 丁目
					<s:property value="header.address_4"/> 番地
				</td></tr>
			<tr><td class="label_col"><label>お届け先</label></td>
				<td class="input_col">
					<s:property value="header.dt_address_1"/><br>
					<s:property value="header.dt_address_2"/><br>
					<s:property value="header.dt_address_3"/> 丁目
					<s:property value="header.dt_address_4"/> 番地<br>
					<s:property value="header.dt_nm"/><br>
				</td></tr>
			<tr><td class="label_col"><label>お支払方法</label></td>
				<td class="input_col">
					<s:property value="payment_method_nm"/><br>
					<s:property value="payment_method_sub_nm"/>
				</td></tr>
		</table>
		<table class="button_area">
			<tr><td>
				<input type="button" value="　　　戻る　　　" onClick="location.href='order_4.html'" class="button_sub">
				<input type="button" value="　注文を確定する　" onClick="location.href='wait_to_complete.html'" class="button_sub">
			</td></tr>
		</table>
	</form></center>
</body>
</html>
