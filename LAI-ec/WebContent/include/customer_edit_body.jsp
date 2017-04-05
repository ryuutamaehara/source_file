<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/include/common_url.jsp" %>

		<table class="order_body">
			<tr><td class="label_col"><label>お名前</label></td>
				<td class="input_col"><s:textfield name="customer_nm" value="%{customer_nm}"/>
				<span class="note">あなたのお名前を入力してください</span>
				<s:fielderror cssClass="error_message" fieldName="customer_nm"/>
				</td></tr>
			<tr><td class="label_col"><label>住所</label></td>
				<td class="input_col">
					この村<s:hidden name="address_1" value="この村"/><br>
					<s:textfield name="address_2" value="%{address_2}"/>
					<span class="note">村内の住宅エリア名を記入してください</span>
					<s:fielderror cssClass="error_message" fieldName="address_2"/>
					<br>
					<s:textfield name="address_3" value="%{address_3}" size="4" cssClass="num"/> 丁目
					<s:textfield name="address_4" value="%{address_4}" size="4" cssClass="num"/> 番地
					<s:fielderror cssClass="error_message" fieldName="address_3"/>
					<s:fielderror cssClass="error_message" fieldName="address_4"/>
				</td></tr>
		</table>
