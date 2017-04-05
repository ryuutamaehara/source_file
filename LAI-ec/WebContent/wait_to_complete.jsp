<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/include/common_url.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>しばらくお待ちください</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css2/add.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/order.css" type="text/css">
<style>
.message {
	display: table;
	width: 100%;
	height: 400px;
	text-align: center;
	font-size: 14px;
	font-weight: normal;
}
.message  > * {
	display:table-cell;
	vertical-align: middle;
}
</style>
<script>
function goNext()
{
	location.href="order_6.html";
}
</script>
</head>
<body onLoad="goNext()">
<div class="message blink"><div>しばらくお待ちください</div></div>
</body>
</html>
