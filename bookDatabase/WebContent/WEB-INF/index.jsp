<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<title>Index Page</title>
<link rel="stylesheet" type="text/css" href="main.css">
</head>
<body>
    <nav class="navbar navbar-expand-md">
      <a class="navbar-brand" href="/bookDatabase">Logo</a>
      <button class="navbar-toggler navbar-dark" type="button" data-toggle="collapse" data-target="#main-navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="main-navigation">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" href="/bookDatabase">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="bookController" method="get" >Book List</a>
          </li>
        </ul>
      </div>
    </nav>
    <div class="alert alert-warning alert-dismissible fade show" role="alert">
    	<strong>Holy guacamole!</strong> You should check in on some of those fields below.
  		<button type="button" class="close" data-dismiss="alert" aria-label="Close">
    		<span aria-hidden="true">&times;</span>
  		</button>
	</div>
	<div class="alert alert-danger alert-dismissible" runat ="server" id="modalEditError" visible ="false">
  		<button class="close" type="button" data-dismiss="alert">×</button>
  		<strong>The updated interview information was not saved!</strong> <div id="Div2" runat="server" ></div>
	</div>
	<h1>Index Page</h1>

	<!-- Show only when not in Session -->
	<form action="" 
		method="post">
		<input type="submit" class="btn btn-primary btn-lg" name="SignUp" value="Sign Up">
	</form>

	<!-- Show only when not in Session -->
	<form action=""  method="post">
		<input type="submit" class="btn btn-secondary" name="LogIn" value="Log In">
	</form>

	<!-- Show only when user is a admin-->
	<form action="" 
		method="post">
		<input type="submit" class="btn btn-info btn-sm active" name="BookRegister" value="Book Register">
	</form>

	<!-- Show only when in Session -->
	<!-- Drop Down List of available lists -->
	<!-- Show List according to chosen List in drop down menu -->
	<form action="bookController" 
		method="get">
		<input type="submit" class="btn btn-info btn-sm active" value="Show BookList">
	</form>
	<form action="others" 
		method="get">
		<input type="submit" class="btn btn-info btn-sm active" value="Edit Other Values">
	</form>	
	
	
	<div align="center">
		<table border="1" cellpadding="5" class="table table-bordered table-light table-striped table-hover table-sm">
			<h1>List of books</h1>
			<thead class="thead-dark">
			<tr>

			<!--<th>Book ID</th> -->
				<th>Book Name</th>
				<th>Book Writer</th>
				<th>Book Publisher</th>
				<th>Book Price</th>
				<th>Book Category</th>
				<th>Insert Date</th>
			</tr>
			</thead>
			<c:forEach items="${bookList}" var="book">
				<tr>
					<td>${book.bookName}</td>
					<td>${book.bookWriter.writerName}</td>
					<td>${book.bookPublisher}</td>
					<td>${book.bookPrice}</td>
					<td>${book.bookCategory.categoryName}</td>
					<td>${book.insertDate}</td>
<!-- 					<form action=bookController> -->
<!-- 					<td><button type="submit" name="editButton" value=${book.bookId} formmethod="post">Edit</button></td> -->
<!-- 					<td><button type="submit" name="deleteButton" value=${book.bookId}	formmethod="post">Delete!</button></td> -->
<!-- 					</form> -->
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>