<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/layout/header.jsp">
		<jsp:param  name="title" value="Admin - Category Management"/>
	</jsp:include>
</head>
<body>
	<div class="container">
		
		<div class="panel panel-default">
			<div class="panel-heading">Create category</div>
			<div class="panel-body">		 
				<c:if test="${not empty failed}">
					<div class="alert alert-danger" role="alert">Failed to create a new category.</div>
				</c:if>
				<form method="POST">
					<div class="form-group">
						<label for="name">Name</label>
						<input type="text" class="form-control" name="name" placeholder="Name">
					</div>
					<div class="form-group">
						<label for="description">Description</label> <input
							type="text" class="form-control" name="description" placeholder="Description">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
		
			</div>
		</div>
		
		<table class="table table-bordered table-striped">
		<thead>
			<tr>
				<th>#</th>
				<th>Name</th>
				<th>Description</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${categories}" var="item">
			<tr>
				<td>${item.id}</<td>
				<td>${item.name}</<td>
				<td>${item.description}</<td>
			</tr>		
		</c:forEach>
		</tbody>
		</table>
		
		<a class="btn btn-default" href=".">Back</a>
		
	</div>

</body>
</html>