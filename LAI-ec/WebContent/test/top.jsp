<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/include/common_url.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>村のお店</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css"/>
<style>
.news_date_col {
	width: 120px;
	font-size: 13px;
	text-align: center;
}
.news_title {
	font-size: 13px;
	font-weight: bold;
}
.news_body {
	font-size: 12px;
}
.rec_img_col {
	width: 120px;
	font-size: 13px;
	text-align: center;
}
.rec_img_col img {
	width: 45px;
	height: 45px;
}
.rec_product_name {
	font-size: 13px;
	font-weight: bold;
}
.rec_desc {
	font-size: 12px;
}
.rec_price {
	width: 60px;
	font-size: 12px;
	font-weight: normal;
	text-align: right;
}
</style>
</head>
<body>
<table width="100%"><tr>
	<td><div class="shop_title">村のお店</div><div class="breadcrumbs">トップ</div></td>
	<td class="header_button_area"><s:a href="%{login_url}">マイページ</s:a> <s:a href="%{cart_url}">買物カゴ</s:a></td>
</tr></table>
<table><tr>
	<td class="col_left">
		<jsp:include page="/include/left_column.jsp" />
	</td>
	<td class="col_right">
		<div class="section_title">お知らせ</div>
		<table>
			<s:iterator value="news_list">
			<tr><td class="news_date_col"><s:date name="valid_start_dttm" format="yyyy/MM/dd"/></td>
				<td>
					<div class="news_title"><s:property value="title"/></div>
					<div class="news_body"><s:property value="body"/></div>
				</td></tr>
			</s:iterator>
		</table>
		<div class="section_title">テンチョーのイチオシ！</div>
		<table>
			<s:iterator value="prd_rec_list">
			<s:url value="/prd/%{prd_nm_for_url}/%{prd_cd}.html" var="prd_url"/>
			<tr><td class="rec_img_col">
					<s:a href="%{prd_url}"><img src="<s:url value="/prd/images/%{prd_cd}.png"/>"></s:a></td>
				<td>
					<div class="rec_product_name"><s:a href="%{prd_url}"><s:property value="prd_nm"/></s:a></div>
					<div class="rec_desc"><s:property value="p_description"/></div>
					<div class="rec_price price">
						<s:text name="format.number">
							<s:param name="value" value="selling_price"/>
						</s:text> VM
					</div>
				</td></tr>
			</s:iterator>
		</table>
	</td>
</tr></table>
</body>
</html>
