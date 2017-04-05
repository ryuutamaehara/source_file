<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>村の人の声一覧</title>
	<link rel="STYLESHEET" href="css/voicelist.css" type="text/css">
</head>
<body>
<h1>村の人の声一覧</h1>
<hr>
<div class = "page_header" >
	<s:form action = "logout" theme = "simple">
		ようこそ <s:property value="currentUserProfile.userName"/>
		<s:url var="password_change_url" action="password_change_display"/>
		<s:a href="%{password_change_url}">パスワード変更</s:a>
		<s:submit value = "ログアウト"/>
	</s:form>
</div>
<div class = "search_condition">
	<s:form action = "voicelist" theme = "simple">
		<s:label for="search_keyword" value = "検索キーワード"/>
		<s:textfield key = "search_keyword" value = "%{search_keyword}" size="40"/>
		<s:submit value = "検索" />
	</s:form>
</div>
<s:if test="voices.length == 0">
	<div class = "error_message">
	表示すべき声がみつかりません。
	</div>
</s:if>
<s:else>
<table class="list_table">
	<s:iterator value="voices">
		<tr>
			<td class="user_name">
				<s:property value="userName"/>
			</td>
			<td class="user_attr">
				<s:property value="age"/>歳<s:property value="gender"/>
			</td>
			<td class = "timestamp">
				<s:date name ="postedOn" format = "yyyy/MM/dd HH:mm"/>
			</td>
		</tr>
		<tr>
			<td colspan = "3" class = "subject">
				<s:property value="subject"/>
			</td>
		</tr>
		<tr>
			<td colspan = "3" class = "voice_body">
				<pre><s:property value = "voiceBody" escape = "false"/></pre>
			</td>
		</tr>
		<tr>
			<td colspan = "3" class = "fotter_row">
				&nbsp;
			</td>
		</tr>
	</s:iterator>
</table>
</s:else>
</body>
</html>
