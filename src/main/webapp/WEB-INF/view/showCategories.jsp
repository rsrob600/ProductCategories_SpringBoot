<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<!-- Custom CSS -->
	<link rel="stylesheet" type="text/css" href="css/style.css">
	
	<title>ProductCategories | Show Categories</title>
</head>
<body>

	<section class="add-form mt-5">
	
		<div class="container">
			<div class="row justify-content-md-center">
				<div class="col-md-auto">
					<h1 class="mb-4 font-weight-light">Category: ${category.name}</h1>
				</div>
			</div>
		</div>
		
		<div class="container">
			<div class="row d-flex justify-content-center">
				<div class="col col-md-4 d-flex align-items-stretch">
					<div class="card shadow mb-3" style="width: 25rem;">
					  	<div class="card-header">
					    	<h4 class="font-weight-light">Assigned Products</h4>
					  	</div>
						<div class="card-body">
						    <table id="dtable" class="table table-hover table-dark">
							    <thead>
							        <tr>
							            <th scope="col">Product</th>
							        </tr>
							    </thead>
							    <tbody>
							        <c:forEach items="${category.products}" var="p">
							        <tr>
							            <td scope="row"><c:out value="${p.name}"/></td>
							        </tr>
							        </c:forEach>
							    </tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="col col-md-4 d-flex align-items-stretch">
					<div class="card shadow mb-3" style="width: 25rem;">
					  	<div class="card-header">
					    	<h4 class="font-weight-light">Add Product</h4>
					  	</div>
						<div class="card-body">
						    <form action="/categories/${category.id}" method="POST">
								<select name="product">
									<c:forEach items="${products}" var="p">
										<option value="${p.id}">
											<c:out value="${p.name}"/>
										</option>
									</c:forEach>
								</select><br><br> 
								<input class="btn btn-primary mr-2" type="submit" value="Add Product"/>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	
	</section>

    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>
</html>