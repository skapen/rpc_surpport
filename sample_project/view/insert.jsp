<%@page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<html:html>
	<body>
			ユーザー名：<%=session.getAttribute("login")%>
			<br><br>
			<html:errors/>
	 		<html:form action="/InsertChk" method="post">   
			<table>
				<tr>
					<td>氏名：</td>
					<td><html:text property="name" /></td>
				</tr>
				<tr>
					<td>性別：  </td>
					<td><html:radio property="seibetsu" value="1" />男　
					    <html:radio property="seibetsu" value="2" />女</td>
				</tr>
				<tr>
					<td> 生年月日：<br>(yyyy-mm-dd) </td>
					<td><html:text property="birthday"/></td>
				</tr>
				<tr>
					<td>パスワード：</td>
					<td><html:text property="ins_pass"/></td>
				</tr>
				<tr>
					<td>パスワード再確認</td>
					<td><html:text property="ins_pass_anather"/></td>
				</tr>
				<tr>
					<td><html:submit value="登録"/> </td>
					<td><html:cancel value="戻る"/> </td>
				</tr>
			</table>
	 </html:form>	
	</body>
</html:html>