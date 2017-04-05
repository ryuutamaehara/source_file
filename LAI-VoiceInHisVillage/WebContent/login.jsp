<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイン</title>
</head>
<body>
	<h1>ログイン</h1>
	<hr />
	<div align="center">
		<s:form name="login_form" action="login_auth">
		<s:textfield name="user_id" value="" size="24" label="ユーザID" />
		<s:password name="password" value="" size="24" label="パスワード" />
		<s:submit value="ログイン" align="center"/>
		</s:form>
	</div>
</body>
</html>
