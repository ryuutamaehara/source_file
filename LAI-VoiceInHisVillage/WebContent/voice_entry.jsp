<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>あなたの声の登録</title>
	<link rel="STYLESHEET" href="css/voiceentry.css" type="text/css">
</head>
<body>
	<h1>あなたの声の登録</h1>
	<hr>
	<div align = "center">
		<s:form action = "voiceentry" cssErrorClass = "errorMessage">
			<s:textfield name ="user_name" label = "お名前" value = "%{currentUserProfile.userName}" disabled = "true" />
			<s:textfield name = "subject" id = "subject" label = "お題" size = "60" value = "%{subject}" />
			<s:textarea name = "voice_body" id = "voice_body" label = "本文" cols = "60" rows = "10" value = "%{voice_body}" />
			<s:submit value = "登録する" align = "center" />
		</s:form>
	</div>
</body>
</html>