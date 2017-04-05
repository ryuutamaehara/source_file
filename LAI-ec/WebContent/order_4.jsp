<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/include/common_url.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>お支払方法 - 村のお店</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css2/add.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/order.css" type="text/css">
<style>
</style>
<script>
function initPage()
{
	main_form.payment_method_sub_cd.value = "<s:property value="payment_method_sub_cd"/>";
	switchPaymentMethod();
}
function switchPaymentMethod()
{
	var payment_method_id;
	payment_method_id = main_form.payment_method_id.value;
	if (payment_method_id == "20") {
		document.getElementById('quest_info_tr').style.display = "";
	}
	else {
		document.getElementById('quest_info_tr').style.display = "none";
	}
}
function adjustAction()
{
	if (document.main_form.payment_method_id.value == "20") {
		document.main_form.action = "order_4_q.html";
	}
	else {
		document.main_form.action = "order_4.html";
	}
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
<body onLoad="initPage()">
<table width="100%"><tr>
	<td>
		<h1 class="king">村のお店</h1>
		<div class="breadcrumbs"><s:a href="%{top_url}">トップ</s:a> &gt; ご注文手続き</div>
	</td><!--<td class="header_button_area">&nbsp;</td>-->
</tr></table>
<table class="order_page_header"><tr>
	<td class="page_title">お支払方法</td>
	<td></td>
	<td class="order_step">買物カゴ</td>
	<td class="order_step">ログイン</td>
	<td class="order_step">お客様情報</td>
	<td class="order_step">お届け先</td>
	<td class="order_step here">お支払方法</td>
	<td class="order_step">確認</td>
	<td class="order_step">完了</td>
</tr></table>
<center>
	<form name="main_form" action="order_4.html" method="post">
		<input type="hidden" name="command" value="GO_NEXT"/>
		<table class="order_body">
			<tr><td colspan="2">
				<ul class="radio_list">
					<li><input type="radio" name="payment_method_id" value="10" onClick="switchPaymentMethod();" <s:property value="payment_method_id_cash_checked"/>> 村通貨（VM）で支払う</li>
					<li><input type="radio" name="payment_method_id" value="20" onClick="switchPaymentMethod();" <s:property value="payment_method_id_quest_checked"/>> クエストで支払う<br><span class="note">（指定のクエストをクリアして村長に報告することで支払が完了します。）</span></li>
				</ul>
					<s:fielderror cssClass="error_message" fieldName="payment_method_id"/>
			</td></tr>
			<tr id="quest_info_tr" style="display: none;"><td class="label_col"><label>クエストの選択</label></td>
				<td class="input_col">
				<ul class="radio_list">
					<li><input type="radio" name="payment_method_sub_cd" value="Q0001" /> 山のボス討伐１０匹</li>
					<li><input type="radio" name="payment_method_sub_cd" value="Q0002" /> サルっぽい敵討伐１００匹</li>
					<li><input type="radio" name="payment_method_sub_cd" value="Q0003" /> 「すっごい剣」を３個作って納品</li>
					<li><input type="radio" name="payment_method_sub_cd" value="Q0004" /> 畑でトマトを３個作って納品</li>
				</ul>
				<s:fielderror cssClass="error_message" fieldName="payment_method_sub_cd"/>
			</td></tr>
		</table>
		<table class="button_area">
			<tr><td>
				<input type="button" value="　　　戻る　　　" onClick="goBack()" class="button_sub">
				<input type="button" value="　　　次へ　　　" onClick="goNext()" class="button_sub">
			</td></tr>
		</table>
	</form>
</center>
</body>
</html>
