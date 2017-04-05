<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/include/common_url.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>お問合せ一覧</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css2/add.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css" type="text/css">
<style>
.inq_header {
	border-bottom: 1px solid #e0e0e0;
	background: #f0f0f0;
}
.inq_row {
	border-bottom: 1px solid #e0e0e0;
}
.inq_id_col {
	width: 100px;
	text-align: center;
	font-size: 12px;
}
.inq_date_col {
	width: 100px;
	text-align: center;
	font-size: 12px;
}
.inq_status_col {
	width: 120px;
	text-align: center;
	font-size: 12px;
}
.inq_subject_col {
	width: 220px;
	text-align: left;
	font-size: 12px;
}
.inq_body_col {
	width: 320px;
	text-align: left;
	font-size: 12px;
}
.sub_menu {
	vertical-align: bottom;
	font-size: 12px;
}
</style>
<script>
</script>
</head>
<body>
<table width="100%"><tr><td><h1 class="king">村のお店</h1><div class="breadcrumbs"><s:a href="%{top_url}">トップ</s:a> &gt; <a href="index.html">マイページ</a> &gt; お問合せ一覧</div></td><!--<td class="header_button_area">&nbsp;</td>--></tr></table>
<table class="mypage_header"><tr>
	<td class="page_title">お問合せ一覧</td>
	<td class="sub_menu"><a href="inquiry_entry_.html">新規問合せ</a></td>
</tr></table>
<center>
	<table class="mypage_body">
		<tr class="inq_header">
			<th class="inq_id_col">問合せ番号</th>
			<th class="inq_date_col">問合せ日</th>
<!-- 			<th class="inq_status_col">問合せ状況</th> -->
			<th class="inq_subject_col">タイトル</th>
			<th class="inq_body_col">問合せ内容</th>
		</tr>
		<s:iterator value="inquiry_list">
		<tr class="inq_row">
			<td class="inq_id_col"><s:property value="inq_id"/></td>
			<td class="inq_date_col"><s:date name="%{accepted_on}" format="yyyy/MM/dd"/></td>
<!-- 			<td class="inq_status_col"><s:property value="inq_status_nm"/></td> -->
			<td class="inq_subject_col"><s:property value="inq_subject"/></td>
			<td class="inq_body_col"><s:property value="inq_body"/></td>
		</tr>
		</s:iterator>
	</table>
</center>
</body>
</html>
