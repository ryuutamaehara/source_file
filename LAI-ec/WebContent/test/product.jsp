<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/include/common_url.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:property value="prd.prd_nm"/> - 村のお店</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/prd.css" type="text/css"/>
<style>
</style>
<script>
function buyThis()
{
	document.cart_form.submit();
}
</script>
</head>
<body>
<s:url value="/cate/%{cate.cate_nm_for_url}/%{cate.cate_cd}.html" var="cate_url"/>
<table width="100%"><tr>
	<td>
		<div class="shop_title">村のお店</div>
		<div class="breadcrumbs"><s:a href="%{top_url}">トップ</s:a> &gt; <s:a href="%{cate_url}"><s:property value="cate.cate_nm"/></s:a> &gt; <s:property value="prd.prd_nm"/></div>
	</td>
	<td class="header_button_area"><s:a href="%{login_url}">マイページ</s:a> <s:a href="%{cart_url}">買物カゴ</s:a></td>
</tr></table>
<table><tr>
	<td class="col_left">
		<jsp:include page="/include/left_column.jsp" />
	</td>
	<td class="col_right">
		<div class="prd_name"><s:property value="prd.prd_nm"/></div>
		<table style="width: 100%; padding: 8px;"><tr>
			<td class="prd_img_col"><img class="prd_img"></td>
			<td>
				<div class="section_title">商品の説明</div>
				<div class="prd_desc"><s:property value="prd.description"/></div>
				<div class="prd_price price">
					<s:text name="format.number">
						<s:param name="value" value="prd.selling_price"/>
					</s:text> VM
				</div>
				<div class="buy_button"><form><input type="button" value="買い物カゴに入れる" onClick="buyThis();"/></form></div>
			</td>
		</tr></table>
	</td>
</tr></table>
<s:form name="cart_form" action="%{cart_url}" method="post">
	<s:hidden name="prd_id" value="%{prd_id}"/>
</s:form>
</body>
</html>
