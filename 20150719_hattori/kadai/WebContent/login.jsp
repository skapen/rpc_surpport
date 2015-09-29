<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイン</title>
</head>
<body>
<h1>ログイン画面</h1>
<p>※ログインID：test、パスワード：test</p>
<p>使用データベース：KADAI, テーブル：M_USER 使用。</p>
<s:form action="login" method="post">
	<s:actionerror/>
	<s:textfield key="id" label="ログインID"/><br>
	<s:textfield key="pass" type="password" label="パスワード"/><br>
	<s:submit value="ログイン"></s:submit>
</s:form>
</body>
</html>