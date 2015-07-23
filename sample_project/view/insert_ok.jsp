<%@page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<html:html lang="ja">
	<body>
	<bean:define id="sei_key" name="human" type="java.lang.String" property="seibetsu_str"/>
	ユーザー名：<%=session.getAttribute("login")%><br>
	<table>
	<tr>
		<td>社員ID：</td>
		<td><bean:write name="human" property="id"/></td>
	</tr>
	<tr>
		<td>氏名：</td>
		<td><bean:write name="human" property="name" /></td>
	</tr>
	<tr>
		<td>性別：</td>
		<td><bean:message key="<%=sei_key%>" /></td>
	</tr>
	<tr>
		<td>生年月日：</td>
		<td><bean:write name="human" property="birthday" /></td>
	</tr>
	<tr>
		<td>パスワード：</td>
		<td><bean:write name="InsertChkForm" property="ins_pass" /></td>
	</tr>
	</table>
	<html:link href="view/menu2.jsp">戻る</html:link>
 	</body>
</html:html>
