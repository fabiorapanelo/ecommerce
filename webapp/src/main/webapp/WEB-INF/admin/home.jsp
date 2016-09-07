<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/layout/header.jsp">
		<jsp:param  name="title" value="Admin"/>
	</jsp:include>
</head>
<body>
	<div class="container">
		<a class="btn btn-default btn-block" href="category">Category</a>
		<a class="btn btn-default btn-block" href="product">Products</a>
	</div>
</body>
</html>