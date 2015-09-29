<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登録画面</title>
</head>
<body>
<p>ユーザ名： <s:property value="name" /></p>

<form action="register" method="post">
	<s:textfield label="氏名" key="name1"></s:textfield><br/>
	<s:radio label="性別" list="{\"男\", \"女\"}" key="gender1"></s:radio><br/>
	<s:textfield label="生年月日" key="birthday1"></s:textfield>
	<s:submit value="登録"></s:submit>
</form>

</body>
</html>