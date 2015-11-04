
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.restfb.types.Post" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello Anh Quan!  
</h1>

<%-- <P>  The very first post: ${myFeed}. </P> --%>
<form action="submitAccessToken" method="post">
	<table>
		<tr>
			<td><h3>Input your access token</h3></td>
		</tr>
		<tr>
			<td><label>User access token:</label></td><td><input name="userAccessToken" type="text" maxlength="300" required="required" placeholder="User access token"></td>
			<td><label>Page ID:</label></td><td><input name="pageID" type="text" maxlength="50" placeholder="Page ID"></td>
		</tr>
		<tr>
			<td><input type="submit" name="Submit" value="Submit"/></td>
		</tr>
	</table>
</form>

<div>
	<c:if test="${userID != null}">
		<h4>Feed for user: <c:out value="${userID}"/></h4>
	</c:if>
	<c:if test="${pageName != null}">
		<h4>Feed for page: <c:out value="${pageName}"/></h4>
	</c:if>
	<c:if test="${listPosts != null }">
		<table border="1">
			<% int i = 0; %>
			<c:forEach items="${listPosts}" var="item">
				<tr>
					<c:if test="${item.message != null}">
						<td>Posts <%=i++ %></td>
						<td><c:out value="${item.message}"></c:out></td>
						<td><c:out value="${item.likesCount}"></c:out></td>
						<td><c:out value="${item.createdTime}"></c:out></td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</div>

</body>
</html>
