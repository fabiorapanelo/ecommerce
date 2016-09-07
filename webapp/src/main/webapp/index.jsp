<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>Ecommerce</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.4/css/bootstrap.min.css">
	
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
	
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.4/js/bootstrap.min.js"></script>
</body>
</html>