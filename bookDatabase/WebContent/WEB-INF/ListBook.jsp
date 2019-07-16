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
</head>
<body>
	<a href="/bookDatabase">Return to Index</a>
	<div align="center">
		<table border="1" cellpadding="5">
			<h1>List of books</h1>
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
</body>
</html>