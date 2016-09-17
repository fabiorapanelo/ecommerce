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
	
		<div class="row">
  			<div class="col-md-3">
  				<jsp:include page="/WEB-INF/admin/admin_menu.jsp">
  					<jsp:param value="true" name="products"/>
  				</jsp:include>
  			</div>
  			<div class="col-md-9">
				<div class="panel panel-default">
					<div class="panel-heading">Create product</div>
					<div class="panel-body">
						<c:if test="${not empty failed}">
							<div class="alert alert-danger" role="alert">Failed to create a new product.</div>
						</c:if>
						<form method="POST">
							<div class="form-group">
								<label for="name">Name</label>
								<input type="text" class="form-control" name="name" placeholder="Name">
							</div>
							<div class="form-group">
								<label for="category">Category</label>
								<select name="category" class="form-control" placeholder="Category">
									<c:forEach items="${categories}" var="category">
										<option value="${category.id}">${category.name}</option>									
									</c:forEach>
								</select>
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
						<th>Category</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${products}" var="product">
					<tr>
						<td>${product.id}</<td>
						<td>${product.name}</<td>
						<td>${product.category.name}</<td>
					</tr>		
				</c:forEach>
				</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>