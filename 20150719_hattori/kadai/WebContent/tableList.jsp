<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一覧画面</title>
</head>
<body>
<s:form action="database">
	<s:label label="検索条件"></s:label>
	<s:textfield label="氏名" key="name"></s:textfield>
	<s:submit action="database.SelectShain" value="検索"></s:submit>
	<s:submit value="戻る"></s:submit>
</s:form>
<hr>
<!-- データベース表示エリア -->

</body>
</html>