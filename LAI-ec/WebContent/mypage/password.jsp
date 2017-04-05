<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/include/common_url.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>お客様情報編集 - パスワードの変更</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css2/add.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css" type="text/css">
<style>
</style>
<script>
function complete()
{
	if (window.confirm("パスワードを変更します。よろしいですか？"))
	{
		document.main_form.submit();
	}
}
</script>
</head>
<body>
<table width="100%"><tr><td><h1 class="king">村のお店</h1><div class="breadcrumbs"><s:a href="%{top_url}">トップ</s:a> &gt; <a href="index.html">マイページ</a> &gt; パスワードの変更</div></td><!--<td class="header_button_area">&nbsp;</td>--></tr></table>
<table class="mypage_header"><tr>
	<td class="page_title">パスワードの変更</td>
</tr></table>
<center>
	<s:form name="main_form" action="password.html" method="post" theme="simple">
		<input type="hidden" name="command" value="GO_NEXT"/>

		<div class="error_message" style="text-align: center;"><s:property value="error_message"/></div>

		<table class="order_body">
			<tr><td class="label_col"><label>現在のパスワード</label></td>
				<td class="input_col"><s:password name="current_password"/>
					<span class="note">現在のパスワードを入力してください</span>
					<s:fielderror cssClass="error_message" fieldName="current_password"/>
				</td></tr>
			<tr><td class="label_col"><label>新しいパスワード</label></td>
				<td class="input_col">
					<s:password name="new_password1"/>
					<span class="note">新しいパスワードを入力してください</span>
					<s:fielderror cssClass="error_message" fieldName="new_password1"/>
					<s:password name="new_password2"/>
					<span class="note">確認のためもう１度入力してください</span>
					<s:fielderror cssClass="error_message" fieldName="new_password2"/>
				</td></tr>
		</table>

		<table class="button_area">
			<tr><td>
				<input type="button" value="　　　戻る　　　" onClick="location.href='index.html'" class="button_sub">
				<input type="button" value="　　更新する　　" onClick="complete()" class="button_sub">
			</td></tr>
		</table>
	</s:form>
</center>
</body>
</html>
