<%@page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<html:html lang="ja">
	<body>
		<h1>ログイン画面</h1>
			<html:errors />
	 		<html:form action="/LoginChk" method="post">   
			<table>
				<tr>
					<td>ユーザー名：</td>
					<td><html:text property="user" /></td>
				</tr>
				<tr>
					<td>パスワード：</td>
					<td><html:password property="password" /></td>
				</tr>
				<tr>
					<td><html:submit value="ログイン"/></td>
				</tr>
			</table>
	 </html:form>	
	</body>
</html:html>
