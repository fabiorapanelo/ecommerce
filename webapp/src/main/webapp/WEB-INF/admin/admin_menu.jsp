<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="nav nav-pills  nav-stacked">
	<c:choose>
		<c:when test="${param.admin}">
			<li role="presentation" class="active"><a href=".">Admin</a></li>
		</c:when>
		<c:otherwise>
			<li role="presentation"><a href=".">Admin</a></li>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${param.category}">
			<li role="presentation" class="active"><a href="category">Category</a></li>
		</c:when>
		<c:otherwise>
			<li role="presentation"><a href="category">Category</a></li>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${param.products}">
			<li role="presentation" class="active"><a href="product">Products</a></li>
		</c:when>
		<c:otherwise>
			<li role="presentation"><a href="product">Products</a></li>
		</c:otherwise>
	</c:choose>
</ul>