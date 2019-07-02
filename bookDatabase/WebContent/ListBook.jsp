<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP List Book Records</title>
</head>
<body>

	<div align="center">
		<table border="1" cellpadding="5">
			<caption>List of books</caption>
			<tr>

				<!--			<th>Book ID</th> -->
				<th>Book Name</th>
				<th>Book Writer</th>
				<th>Book Publisher</th>
				<th>Book Category</th>
			</tr>
			<c:forEach items="${bookList}" var="book">
				<tr>
					<td>${book.bookName}</td>
					<td>${book.bookWriter}</td>
					<td>${book.bookPublisher}</td>
					<td>${book.bookCategory}</td>
					<form action=bookController>
					<td><button type="submit" name="editButton" value=${book.bookId} formmethod="put">Edit</button></td>
					<td><button type="submit" name="deleteButton" value=${book.bookId}	formmethod="delete">Delete!</button></td>
					</form>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>