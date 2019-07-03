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
	<h1>Edit Book</h1>
	<form action="bookController" method="post">
		<table style="with: 50%">
			<c:forEach items="${bookList}" var="bookList">

				<tr>
					<td>Book Name</td>
					<td><input type="text" value=${bookList.bookName} name="bookName" /></td>
				</tr>
				<tr>
					<td>Writer Name</td>
					<td><input type="text" value=${bookList.bookWriter} name="writerName" /></td>
				</tr>
				<tr>
					<td>Publisher Name</td>
					<td><input type="text" value=${bookList.bookPublisher} name="publisherName" /></td>
				</tr>
				<tr>
					<td>Category Name</td>
					<td><input type="text" value=${bookList.bookCategory} name="categoryName" /></td>
				</tr>
				<tr>
					<td><input type="hidden" value=${bookList.bookId} name="bookId" /></td>
				</tr>
				<tr>
					<td><input type="submit" name="editPage" value="Edit book"></td>
				</tr>
			</c:forEach>
		</table>
		
	</form>
</body>
</html>



