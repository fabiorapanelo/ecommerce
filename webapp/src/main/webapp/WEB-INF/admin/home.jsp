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
  					<jsp:param value="true" name="admin"/>
  				</jsp:include>
  			</div>
  			<div class="col-md-9">
				<span>Admin panel</span>
			</div>
		</div>
	</div>
</body>
</html>