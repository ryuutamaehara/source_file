<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>パスワード変更</title>
</head>
<body>
	<h1>パスワード変更</h1>
	<hr>
	<div class = "page_header">
		<s:form action ="logout" theme = "simple">
			<s:url id="go_to_top_url" action = "go_to_top" />
			<s:a href = "%{go_to_top_url}">トップに戻る</s:a>
			ようこそ<s:property value="currentUserProfile.userName"/>さん
			<s:submit value = "ログアウト" />
		</s:form>
	</div>
	<s:property value = "message"/>
	<s:actionerror/>
	<div align = "center">
		<s:form action = "password_change">
			<s:password name = "password1" size = "24" label = "パスワード" />
			<s:password name = "password2" size = "24" label= "確認用パスワード" />
			<s:submit value="パスワード変更" align = "center" />
		</s:form>
	</div>
</body>
</html>
