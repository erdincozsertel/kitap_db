<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP List Book Records</title>
</head>
<body>
     
Book bookList[][] = new book[][]();
     
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of books</h2></caption>
            <tr>
				
                <th>BookID</th>
				<th>BookName</th>
				<th>BookWriter</th>
                
            </tr>
            <c:while int n=0, n<bookList[][].length(), n++>
                <tr>
					<c:while int m=0, m<5, n++>
                    <td><c:out value="booklist[n][m]" /></td>
                    </c:while>
                </tr>
            </c:while>
        </table>
    </div>
</body>
</html>