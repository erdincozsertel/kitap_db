<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Form</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="main.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<!-- <a href="/bookDatabase">Return to Index</a> -->
	<div class="clearfix">
    	<nav class="navbar navbar-expand-md container-fluid p-2 ">
    	  <a class="navbar-brand" href="/bookDatabase"><img src="book.png" alt="logo" style="width:70px;"></a>
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
    </div>	
	<h1>Register Form</h1>
	<form action="bookController" method="post">
		<table style="with: 50%">

			<tr>
				<td>Book Name</td>
				<td><input type="text" name="bookName" required/></td>
			</tr>
			<tr>
				<td>Writer Name</td>
				<td><select input type="text" name="bookWriter" required>
						<c:forEach items="${writerList}" var="writer">
							<option value=${writer.writerId}>${writer.writerName}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>Publisher Name</td>
				<td><input type="text" name="publisherName" required/></td>
			</tr>
			<tr>
				<td>Price</td>
				<td><input type="number" step="0.01" min="0.01" max="999.99" name="bookPrice" required></td>
			</tr>
			<tr>
				<td>Category</td>
				<td><select input type="text" name="bCategory" required>
						<c:forEach items="${categoryList}" var="category">
							<option value=${category.categoryId}>${category.categoryName}</option>
						</c:forEach>
				</select></td>
			</tr>

		</table>
		<input type="submit" value="Submit" />
	</form>
</body>
</html>