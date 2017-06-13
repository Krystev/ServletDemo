<%@page import="com.vso.models.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/main.css" rel="stylesheet" type="text/css">
<title>ANSWER</title>
</head>
<body>
	<a href="index.html">Back</a>
	<h1 align="center">Books in JSP</h1>
	<table align="center">
		<th>Book Name</th>
		<th>Author</th>
		<th>Id</th>
		<th>Date</th>
		<th>Take Book</th>

		<%
			List<Book> books = (List<Book>) request.getAttribute("books");
			if (!books.isEmpty()) {
				for (Book book : books) {
					out.print("<tr><td name=\"take\" value=" + "\"" + book.getName() + "\">" + book.getName()
							+ "</td><td> " + book.getBookAuthor() + "</td><td>" + book.getId() + "</td><td>"
							+ book.getDateOfPublishing() + "</td><td><form action=\"info\" method=\"post\">"
							+ "<button type=\"submit\"  name=\"take\" value=\"" + book.getName() + "\">Take</button>"
							+ "</form></td></tr>");
				}
			} else {
				out.println("No books found");
			}
		%>
	</table>
</body>
</html>