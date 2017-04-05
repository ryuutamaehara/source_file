<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/include/common_url.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイン - 村のお店</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css2/add.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/order.css" type="text/css">
<style>
.body_container {
	text-align: center;
}
.login_body {
	margin-top: 12px;
	width: 640px;
	padding: 8px;
	border-collapse: collapse;
}
.login_body td {
	padding: 16px;
}
.label_col {
	width: 150px;
	text-align: right;
}
.login_id_area {
	height: 48px;
}
.password_row {
	height: 16px;
}
.password_area {
}
.note {
	font-size: 11px;
	font-weight: normal;
}
</style>
<script>
function goNext()
{
	document.main_form.submit();
}
</script>
</head>
<body>
<table width="100%"><tr>
	<td>
		<h1 class="king">村のお店</h1>
		<div class="breadcrumbs"><s:a href="%{top_url}">トップ</s:a> &gt; ログイン</div>
	</td><!--<td class="header_button_area">&nbsp;</td>-->
</tr></table>
<table class="order_page_header"><tr>
	<td class="page_title">ログイン</td>
</tr></table>
<center>
	<s:form name="main_form" action="login.html" method="post" theme="simple">
		<div class="error_message" style="text-align: center;"><s:property value="error_message"/></div>
		<table class="login_body">
			<tr><td class="label_col"><label>ログインＩＤ　：</label></td>
				<td class="login_id_area"><s:textfield name="login_nm" value="%{login_nm}" cssClass="login_id_input" />
				<span class="note">（あなたのキャラクタＩＤをご指定下さい）</span></td></tr>
			<tr><td class="label_col"><label>パスワード　：</label></td>
				<td class="password_area"><input type="password" name="password" class="password_input" />
				<span class="note">（４～８文字の英数字でご指定下さい）</span></td></tr>
		</table>
		<table class="button_area">
			<tr><td>
				<input type="button" value="トップページに戻る" onClick="location.href='<s:property value="top_url"/>'" class="button_sub">
				<input type="button" value="　　ログイン　　" onClick="goNext()" class="button_sub">
			</td></tr>
		</table>
	</s:form>
</center>
</body>
</html>
