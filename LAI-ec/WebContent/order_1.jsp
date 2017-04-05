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
	font-size: 12px;
}
.login_id_area {
/*	height: 48px; */
}
.password_area {
}
.note {
	font-size: 11px;
	font-weight: normal;
}
</style>
<script>
function switchIsNew()
{
	var is_new;
	is_new = main_form.is_new.value;
	if (is_new == "true") {
		document.getElementById('confirm_password_tr').style.display = "";
	}
	else {
		document.getElementById('confirm_password_tr').style.display = "none";
	}
}
function goNext()
{
	document.main_form.submit();
}
</script>
</head>
<body onLoad="switchIsNew()">
<table width="100%"><tr>
	<td>
		<h1 class="king">村のお店</h1>
		<div class="breadcrumbs"><s:a href="%{top_url}">トップ</s:a> &gt; ご注文手続き</div>
	</td><!--<td class="header_button_area">&nbsp;</td>-->
</tr></table>
<table class="order_page_header"><tr>
	<td class="page_title">ログイン</td>
	<td></td>
	<td class="order_step">買物カゴ</td>
	<td class="order_step here">ログイン</td>
	<td class="order_step">お客様情報</td>
	<td class="order_step">お届け先</td>
	<td class="order_step">お支払方法</td>
	<td class="order_step">確認</td>
	<td class="order_step">完了</td>
</tr></table>
<center>
	<s:form name="main_form" action="order_1.html" method="post" theme="simple">
		<div class="error_message" style="text-align: center;"><s:property value="error_message"/></div>
		<table class="login_body">
			<tr><td class="label_col"><label>ログインＩＤ　：</label></td>
				<td class="login_id_area"><s:textfield name="login_nm" value="%{login_nm}" cssClass="login_id_input" />
					<span class="note">（あなたのキャラクタＩＤをご指定下さい）</span></td>
			</tr>
			<tr><td colspan="2">
				<ul class="radio_list">
					<li><input type="radio" name="is_new" value="true" onClick="switchIsNew();" <s:property value="is_new_true_checked"/>> 初めて</li>
					<li><input type="radio" name="is_new" value="false" onClick="switchIsNew();" <s:property value="is_new_false_checked"/>> 買い物をしたことがある</li>
				</ul>
			</td></tr>
			<tr><td class="label_col"><label>パスワード　：</label></td>
				<td class="password_area"><input type="password" name="password" class="password_input" />
				<span class="note">（４～８文字の英数字でご指定下さい）</span></td>
			</tr>
			<tr id="confirm_password_tr" style="display: none;"><td class="label_col"><label>パスワード（確認）　：</label></td>
				<td class="password_area"><input type="password" name="password2" class="password_input" />
				<span class="note">（４～８文字の英数字でご指定下さい）</span></td>
			</tr>
		</table>
		<table class="button_area">
			<tr><td>
				<input type="button" value="買物カゴに戻る" onClick="location.href='<s:property value="cart_url"/>'" class="button_sub">
				<input type="button" value="　　　次へ　　　" onClick="goNext()" class="button_sub">
			</td></tr>
		</table>
	</s:form>
</center>
</body>
</html>
