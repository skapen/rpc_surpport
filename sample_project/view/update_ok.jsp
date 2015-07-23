<%@page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<bean:define id="update_sei"	name="human" type="java.lang.String" property="seibetsu_str" />
<html:html>
	<body>
	ユーザー名：<%=session.getAttribute("login")%><br>
	<table>
	<tr>
		<td>社員ID：</td>
		<td><bean:write name="updateForm" property="syain_id"/></td>
	</tr>
	<tr>
		<td>氏名：</td>
		<td><bean:write name="updateForm" property="name" /></td>
	</tr>
	<tr>
		<td>性別：</td>
		<td><bean:message key="<%=update_sei %>" /></td>
	</tr>
	<tr>
		<td>生年月日：</td>
		<td><bean:write name="updateForm" property="birthday" /></td>
	</tr>
	<tr>
		<td>パスワード：</td>
		<td><bean:write name="updateForm" property="password" /></td>
	</tr>
	</table>
	<html:link href="view/menu2.jsp">戻る</html:link>
 	</body>
</html:html>
