<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/include/common_url.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>お届け先 - 村のお店</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css2/add.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/order.css" type="text/css">
<style>
</style>
<script>
function switchDelivTo()
{
	var deliv_to;
	deliv_to = main_form.deliv_to.value;
	if (deliv_to == "other") {
		document.getElementById('dt_info_tr').style.display = "";
	}
	else {
		document.getElementById('dt_info_tr').style.display = "none";
	}
}
function adjustAction()
{
	if (document.main_form.deliv_to.value == "other")
		document.main_form.action = "order_3_o.html";
	else
		document.main_form.action = "order_3.html";
}
function goBack()
{
	adjustAction();
	document.main_form.command.value = "GO_BACK";
	document.main_form.submit();
}
function goNext()
{
	adjustAction();
	document.main_form.command.value = "GO_NEXT";
	document.main_form.submit();
}
</script>
</head>
<body onLoad="switchDelivTo()">
<table width="100%"><tr>
	<td>
		<h1 class="king">村のお店</h1>
		<div class="breadcrumbs"><s:a href="%{top_url}">トップ</s:a> &gt; ご注文手続き</div>
	</td><!--<td class="header_button_area">&nbsp;</td>-->
</tr></table>
<table class="order_page_header"><tr>
	<td class="page_title">お届け先</td>
	<td></td>
	<td class="order_step">買物カゴ</td>
	<td class="order_step">ログイン</td>
	<td class="order_step">お客様情報</td>
	<td class="order_step here">お届け先</td>
	<td class="order_step">お支払方法</td>
	<td class="order_step">確認</td>
	<td class="order_step">完了</td>
</tr></table>
<center>
	<s:form name="main_form" action="order_3.html" method="post" theme="simple">
		<input type="hidden" name="command" value="GO_NEXT"/>
		<table class="order_body">
			<tr><td colspan="2">
				<ul class="radio_list">
					<li><input type="radio" name="deliv_to" value="my_home" onClick="switchDelivTo();" <s:property value="deliv_to_my_home_checked"/>> ご自身に送る</li>
					<li><input type="radio" name="deliv_to" value="other" onClick="switchDelivTo();" <s:property value="deliv_to_other_checked"/>> お友達に送る</li>
				</ul>
			</td></tr>
			<tr id="dt_info_tr" style="display: none;"><td class="label_col"><label>お友達について</label></td>
				<td class="input_col">
					この村<br>
					<input type="hidden" name="dt_address_1" value="この村"/>
					<s:textfield name="dt_address_2" value="%{dt_address_2}"/>
					<span class="note">村内の住宅エリア名を記入してください</span><br>
					<s:fielderror cssClass="error_message" fieldName="dt_address_2"/>
					<s:textfield name="dt_address_3" value="%{dt_address_3}" size="4"/> 丁目
					<s:textfield name="dt_address_4" value="%{dt_address_4}" size="4"/> 番地<br>
					<s:fielderror cssClass="error_message" fieldName="dt_address_3"/>
					<s:fielderror cssClass="error_message" fieldName="dt_address_4"/>
					お名前<br>
					<s:textfield name="dt_nm" value="%{dt_nm}" />
					<s:fielderror cssClass="error_message" fieldName="dt_nm"/>
				</td></tr>
		</table>
		<table class="button_area">
			<tr><td>
				<input type="button" value="　　　戻る　　　" onClick="goBack()" class="button_sub">
				<input type="button" value="　　　次へ　　　" onClick="goNext()" class="button_sub">
			</td></tr>
		</table>
	</s:form>
</center>
</body>
</html>
