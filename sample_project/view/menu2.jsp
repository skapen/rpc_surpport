<%@page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<html:html lang="ja">
	<body>
 	ユーザー名：<%=session.getAttribute("login")%><br>
 	メニュー:<br>
 	<html:link href="/mystruts2/view/insert.jsp">登録画面</html:link>
	<html:link href="/mystruts2/view/userlist.jsp">一覧画面</html:link>
	</body>
</html:html>
