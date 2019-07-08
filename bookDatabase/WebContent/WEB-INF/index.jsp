<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Index Page</h1>

	<!-- Show only when not in Session -->
	<form action="" style="border: 1px solid #ccc"
		method="post">
		<input type="submit"  name="SignUp" value="Sign Up">
	</form>

	<!-- Show only when not in Session -->
	<form action="" style="border: 1px solid #ccc" method="post">
		<input type="submit" name="LogIn" value="Log In">
	</form>

	<!-- Show only when user is a admin-->
	<form action="" style="border: 1px solid #ccc"
		method="post">
		<input type="submit" name="BookRegister" value="Book Register">
	</form>

	<!-- Show only when in Session -->
	<!-- Drop Down List of available lists -->
	<!-- Show List according to chosen List in drop down menu -->
	<form action="bookController" style="border: 1px solid #ccc"
		method="get">

		<input type="submit" value="Show BookList">

	</form>
	<div align="center">
		<table border="1" cellpadding="5">
			<caption>List of books</caption>
			<tr>

			<!--<th>Book ID</th> -->
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