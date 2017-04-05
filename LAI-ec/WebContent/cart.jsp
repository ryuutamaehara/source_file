<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/include/common_url.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>買物カゴ - 村のお店</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css2/add.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/order.css" type="text/css">
<style>
</style>
<script>
function recalc()
{
	document.main_form.command.value = "RECALC";
	document.main_form.submit();
}
function removeLine(prd_cd)
{
	document.main_form.command.value = "REMOVE";
	document.main_form.prd_cd.value = prd_cd;
	document.main_form.submit();
}
function goToTop()
{
	document.main_form.command.value = "GO_TO_TOP";
	document.main_form.submit();
}
function startOrder()
{
	document.main_form.command.value = "START_ORDER";
	document.main_form.submit();
}
</script>
</head>
<body>
<table width="100%"><tr>
	<td>
		<h1 class="king">村のお店</h1>
		<div class="breadcrumbs"><s:a href="%{top_url}">トップ</s:a> &gt; 買物カゴ</div>
	</td>
	<td class="header_button_area">
		<s:a href="%{login_url}">
		<div class="mypage">
		マイページ
		</div>
		</s:a>
		<s:a href="%{cart_url}">
		<div class="cart">
		買物カゴ
		</div>
		</s:a>
	</td>
</tr></table>
<s:form name="main_form" action="%{cart_url}" method="post" theme="simple">
	<s:hidden name="command" value="RECALC"/>
	<s:hidden name="prd_cd" value=""/>
		<div class="page_title">買物カゴ</div>
		<div class="error_message"><s:property value="error_message"/></div>
		<table class="cart_list">
			<tr class="cart_header">
				<th class="cart_h_prd_name" colspan="2">商品名</th>
				<th class="cart_h_unit_price">単価</th>
				<th class="cart_h_quantity">個数</th>
				<th class="cart_h_line_price">金額</th>
				<th class="cart_h_del_button">削除</th>
			</tr>
			<s:iterator value="cart.detail_list">
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
					<s:select name="quantity.%{prd.prd_cd}"
							list="quantity_sel_elements"
							value="detail.quantity"
							cssClass="quantity_select"
							onchange="recalc()"
							/>
				</td>
				<td class="cart_line_price price">
					<s:text name="format.number">
						<s:param name="value" value="detail.total_price"/>
					</s:text>
				</td>
				<td class="cart_del_button">
					<s:submit type="button" value="削除" cssClass="button_sub" onclick="removeLine('%{prd.prd_cd}')"/>
				</td>
			</tr>
			</s:iterator>
		</table>
		<table class="cart_footer">
			<tr>
				<td >&nbsp;</td>
				<td class="cart_footer_item_label">合計</td>
				<td class="cart_footer_item price">
					<s:text name="format.number">
						<s:param name="value" value="cart.header.product_total_price"/>
					</s:text>
				</td>
				<td class="cart_footer_right_blank">&nbsp;</td>
			</tr>
		</table>
		<table class="button_area">
			<tr><td>
				<input type="button" value="トップページに戻る" onClick="goToTop()" class="button_sub"/>
				<input type="button" value="注文手続きに進む" onClick="startOrder()" class="button_sub"/>
			</td></tr>
		</table>
</s:form>
<s:form name="delete_form" action="%{cart_url}" method="post" theme="simple">
</s:form>
</body>
</html>
