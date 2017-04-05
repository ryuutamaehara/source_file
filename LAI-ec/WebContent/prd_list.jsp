<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/include/common_url.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:property value="page_title"/> - 村のお店</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css2/add.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/prd_list.css" type="text/css">
<style>
</style>
</head>
<body>
<table width="100%"><tr>
	<td>
		<h1 class="king">村のお店</h1>
		<div class="breadcrumbs"><s:a href="%{top_url}">トップ</s:a> &gt; <s:property value="page_title"/></div>
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
<table><tr>
	<td class="col_left">
		<jsp:include page="/include/left_column.jsp" />
	</td>
	<td class="col_right">
		<div class="section_title"><s:property value="page_title"/></div>
		<s:if test="prd_list.size == 0">
			<div class="error_message">
				該当する商品が見つかりませんでした。
			</div>
		</s:if>
		<s:else>
			<table>
				<s:iterator value="prd_list">
				<s:url value="/prd/%{prd_nm_for_url}/%{prd_cd}.html" var="prd_url"/>
				<tr><td class="prd_img_col">
						<s:a href="%{prd_url}"><img src="<s:url value="/prd/images/%{prd_cd}.png"/>"></s:a></td>
					<td>
						<div class="prd_name"><s:a href="%{prd_url}"><s:property value="prd_nm"/></s:a></div>
						<div class="prd_desc"><s:property value="description"/></div>
						<div class="prd_price price">
							<s:text name="format.number">
								<s:param name="value" value="selling_price"/>
							</s:text>
						</div>
					</td></tr>
				</s:iterator>
			</table>
		</s:else>
	</td>
</tr></table>
</body>
</html>
