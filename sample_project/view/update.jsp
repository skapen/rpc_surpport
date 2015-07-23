<%@page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html:html>
	<body>
			ユーザー名：<%=session.getAttribute("login")%>
			<bean:define id="update_id"			name="human" type="java.lang.String" scope="session" property="id" />
 			<bean:define id="update_name"		name="human" type="java.lang.String" scope="session" property="name" />
 			<bean:define id="update_sei"		name="human" type="java.lang.String" scope="session" property="seibetsu" />
 			<bean:define id="update_birthday"	name="human" type="java.lang.String" scope="session" property="birthday" />
 			<br><br>
 			
 			<html:errors/>
 	
			<html:form action="/update">   
			<table>
				<tr>
					<td>社員ID：</td>
					<td><html:text property="s_id" disabled="true" value="<%=update_id %>"/></td>
				</tr>
				<tr>
					<td>氏名：</td>
					<td><html:text property="name" value="<%=update_name %>"/></</td>
				</tr>
				<tr>
					<td>性別：</td>
　　						<td>
							<input type="radio" name="seibetsu" value="1" <%if("1".equals(update_sei)){ %> checked <%}%> >男　
					    	<input type="radio" name="seibetsu" value="2" <%if("2".equals(update_sei)){ %> checked <%}%> >女
					    </td>
					
				</tr>
				<tr>
					<td>生年月日：<br>(yyyy-mm-dd)</td>
					<td><html:text property="birthday" value="<%=update_birthday %>"/></td>
				</tr>
				<tr>
					<td>パスワード：</td>
					<td><html:text property="password"/></td>
				</tr>
				<tr>
					<td>パスワード再確認</td>
					<td><html:text property="password_anather"/></td>
				</tr>
				<tr>
					<td><html:submit value="更新"/> </td>
					<td><html:cancel value="戻る"/> </td>
					<html:hidden property="syain_id" value="<%=update_id %>" />
				</tr>
			</table>
	 </html:form>	
	</body>
</html:html>