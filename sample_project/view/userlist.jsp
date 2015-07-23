<%@ page pageEncoding = "utf-8" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<html:html>
	<body>
 	ユーザー名:<%=session.getAttribute("login")%><br>
 	<br><br>
 	<html:form action="/updatelist">
 		名前<html:text property="search_name" />
 		<html:submit value="検索"/>
 		<html:cancel value="戻る"/><br>
 	</html:form>
 	<hr>
 	<table>
 		<tr>
 			<th>社員ID</th>
 			<th>名前</th>
 			<th>性別</th>
 			<th>生年月日</th>
 			<th>更新</th>
 			<th>削除</th>
 		</tr>
 	<logic:notEqual parameter="search_name" value="">
 	<logic:iterate id="human" collection="<%=request.getAttribute(\"list\")%>"  indexId="idx">
 	<bean:define id="search_id" name="human" type="java.lang.String" property="id" />
 	<bean:define id="sei_key"   name="human" type="java.lang.String" property="seibetsu_str" />
 	<html:form action="/updatechk">
 	<tr>
 		<td><bean:write name="human" property="id" /></td>
 		<td><bean:write name="human" property="name"/></td>
 		<td><bean:message key="<%=sei_key%>" /></td>
 		<td><bean:write name="human" property="birthday"/></td>
 		<td>
 			<html:submit property="method"><bean:message key="button.update" /></html:submit>
 		</td>
 		<td>
 			<html:submit property="method"><bean:message key="button.delete" /></html:submit>
 			<html:hidden property="search_id" value="<%=search_id %>" />
 		</td>
 		
 	</tr>
 	</html:form>
 	</logic:iterate>
 	</logic:notEqual>
 	</table>
 	
 	</body>
</html:html>
