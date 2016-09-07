<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/layout/header.jsp">
		<jsp:param value="title" name="Ecommerce Index"/>
	</jsp:include>
</head>
<body>
	<div class="container">
		<c:if test="${empty user}">
			<a class="btn btn-success btn-block" href="auth">Login with Google</a>
		</c:if>
		
		<c:if test="${not empty user}">
			<p>Welcome ${user.name}</p>
		</c:if>
	
	</div>

</body>
</html>