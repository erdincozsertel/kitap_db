<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP List Book Records</title>
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
	<div align="center" class="p-1">
		<table border="1" cellpadding="5" class="table table-responsive-sm table-bordered table-light table-striped table-hover table-sm p-1">
			<h1>List of books</h1>
			<thead class="thead-dark">
			<tr>

<!--			<th>Book ID</th> -->
				<th>Book Name</th>
				<th>Book Writer</th>
				<th>Book Publisher</th>
				<th>Book Price</th>
				<th>Book Category</th>
				<th>Insert Date</th>
				<th colspan="2"> Update</th>
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
					<form action=bookController>
					<td><button type="submit" name="editButton" value=${book.bookId} formmethod="post">Edit</button></td>
					<td><button type="submit" name="deleteButton" value=${book.bookId}	formmethod="post">Delete!</button></td>
					</form>
				</tr>
			</c:forEach>
		</table>
	</div>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark sticky-bottom">
		
	</nav>
</body>
</html>