<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>
	<c:if test="${empty param.title }">Ecommerce</c:if>
	<c:if test="${not empty param.title }">${param.title}</c:if>
</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>