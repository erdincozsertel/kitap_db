<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Form</title>
</head>
<body>
	<h1>Register Form</h1>
	<form action="bookController" method="post">
		<table style="with: 50%">

			<tr>
				<td>Book Name</td>
				<td><input type="text" name="bookName" /></td>
			</tr>
			<tr>
				<td>Writer Name</td>
				<td><input type="text" name="writerName" /></td>
			</tr>
			<tr>
				<td>Publisher Name</td>
				<td><input type="text" name="publisherName" /></td>
			</tr>
			<tr>
				<td>Price</td>
				<td><input type="number" step="0.01" min="0.01" max="999.99" name="bookPrice"></td>
			</tr>
			<tr>
				<td>Category</td>
				<td><select input type="text" name="bCategory">
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