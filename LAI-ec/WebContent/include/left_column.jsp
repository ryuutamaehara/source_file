<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/include/common_url.jsp" %>
<!-- left_column BEGIN -->
		<ul class="cate_tree">
			<s:iterator value="master_cate_list">
			<li><s:property value="core.master_cate_nm" />
				<ul class="cate_tree_sub">
					<s:iterator value="categories">
					<s:url value="/cate/%{cate_nm_for_url}/%{cate_cd}.html" var="cate_url"/>
					<li><s:a href="%{cate_url}"><s:property value="cate_nm" /></s:a></li>
					</s:iterator>
				</ul>
			</li>
			</s:iterator>
		</ul>

		<s:form action="%{search_url}" style="text-align: center;" theme="simple">
			<s:hidden name="cate_cd" value="%{cate_cd}"/>
			<s:textfield name="search_keyword" size="18" value="%{search_keyword}"/>
			<s:submit value="検索" cssClass="button_sub"/>
		</s:form>
<!-- left_column END -->
