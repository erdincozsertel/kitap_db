<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Others</title>
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
<!-- 	<a href="/bookDatabase">Return to Index</a> -->
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
	<div align="center">
		<table border="1" cellpadding="5">
			<caption>List of Categories</caption>
			<tr>
				<th>Category Name</th>
				<th colspan="2"> Update</th>
			</tr>
				<c:forEach items="${categoryList}" var="category">
			<tr>
					<td>${category.categoryName}</td>
				<form action=others>
					<td><button type="submit" name="editCategory" value=${category.categoryId} formmethod="post">Edit</button></td>
					<td><button type="submit" name="deleteCategory" value=${category.categoryId} formmethod="post">Delete!</button></td>
				</form>
			</tr>
				</c:forEach>
			<tr>
				<form action = others>
					<td><input type="text" name="categoryName" required/></td>
					<td colspan="2"><button type="submit" name="addCategory" value="addCategory" formmethod="post">Add Category</button></td>
				</form>
			</tr>
		</table>
	</div>
	
	
	<div align="center">
		<table border="1" cellpadding="5">
			<caption>List of Writers</caption>
			<tr>
				<th>Writer Name</th>
				<th>Writer Gender</th>
				<th>Writer Birth Date</th>
				<th colspan="2"> Update</th>
			</tr>
				<c:forEach items="${writerList}" var="writer">
			<tr>
					<td>${writer.writerName}</td>
					<td>${writer.gender}</td>
					<td>${writer.birthDate}</td>
				<form action=others>
					<td><button type="submit" name="editWriter" value=${writer.writerId} formmethod="post">Edit</button></td>
					<td><button type="submit" name="deleteWriter" value=${writer.writerId} formmethod="post">Delete!</button></td>
				</form>
			</tr>
				</c:forEach>
			<tr>
				<form action = others>
					<td><input type="text" name="writerName" required/></td>
					<td>
						<input type="radio" name="gender" value="MALE" checked> Male
  						<input type="radio" name="gender" value="FEMALE"> Female
					</td>
					<td><input type="date" name="birthDate" required></td>
					<td colspan="2"><button type="submit" name="addWriter" value="addWriter" formmethod="post" >Add Writer</button></td>
				</form>
			</tr>
		</table>
	</div>
	
</body>
</html>