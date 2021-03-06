<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/include/common_url.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>お問合せ</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css2/add.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css" type="text/css">
<style>
</style>
<script>
function complete()
{
	if (window.confirm("お問合せを登録します。よろしいですか？"))
	{
		document.main_form.submit();
	}
}
</script>
</head>
<body>
<table width="100%"><tr><td><h1 class="king">村のお店</h1><div class="breadcrumbs"><s:a href="%{top_url}">トップ</s:a> &gt; <a href="index.html">マイページ</a> &gt; お問合せ</div></td><!--<td class="header_button_area">&nbsp;</td>--></tr></table>
<table class="mypage_header"><tr>
	<td class="page_title">お問合せ</td>
</tr></table>
<center>
	<s:form name="main_form" action="inquiry_entry.html" method="post" theme="simple">
		<input type="hidden" name="command" value="GO_NEXT"/>

		<div class="error_message" style="text-align: center;"><s:property value="error_message"/></div>

		<table class="order_body">
			<tr><td class="label_col"><label>タイトル</label></td>
				<td class="input_col"><s:textfield name="inq_subject"/>
					<span class="note">お問合せタイトルを簡潔に記入してください</span>
					<s:fielderror cssClass="error_message" fieldName="inq_subject"/>
				</td></tr>
			<tr><td class="label_col"><label>お問合せ内容</label></td>
				<td class="input_col">
					<s:textarea name="inq_body" cols="50" rows="8"></s:textarea>
					<s:fielderror cssClass="error_message" fieldName="inq_body"/>
				</td></tr>
		</table>

		<table class="button_area">
			<tr><td>
				<input type="button" value="　　　戻る　　　" onClick="location.href='inquiry_list.html'" class="button_sub">
				<input type="button" value="　　登録する　　" onClick="complete()" class="button_sub">
			</td></tr>
		</table>
	</s:form>
</center>
</body>
</html>
