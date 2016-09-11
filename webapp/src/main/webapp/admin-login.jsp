<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/layout/header.jsp">
		<jsp:param  name="title" value="Admin - Login"/>
	</jsp:include>
</head>
<body>
	<div class="container">

		<c:if test="${not empty authenticationFailed}">
			<div class="alert alert-danger" role="alert">Authentication failed.</div>
		</c:if>
		<form name="loginform" action="" method="post">
			<div class="form-group">
				<label for="j_username">Username</label>
				<input type="text" class="form-control" name="username" placeholder="Username">
			</div>
			<div class="form-group">
				<label for="j_password">Password</label> <input
					type="password" class="form-control" name="password" placeholder="Password">
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		</form>

	</div>


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.4/js/bootstrap.min.js"></script>
</body>
</html>