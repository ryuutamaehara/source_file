<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>エラー</title>
</head>
<body>
	<h1>エラー</h1>
	<hr>
	<div align="center">
		<table border="0">
			<tr>
				<td>
					エラーが発生しました。<br>
					内容: <s:property value="errorMessage"/>
				</td>
			</tr>
			<tr>
				<td>
					<form>
						<input type="button" value="戻る" onClick="history.back();"/>
					</form>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
