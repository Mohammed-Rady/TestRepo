<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
		<title>Bookstore</title>
		
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		
		<script>
			function myFunction() {
				setTimeout(function() {
				    $('#add_result').fadeOut('fast');
				}, 3000);
			}
			$(document).ready(myFunction);
		</script>
		
		<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<%@ page isELIgnored="false" %>
	</head>
	
	<body>

		<p style="text-align: center;">
			<span style="font-size:72px;">Online Bookstore</span></p>
		<p style="text-align: center;">
			<span style="font-size:36px;">Available books</span></p>
			
		<p style="text-align: center;">
			<span style="color:#00ff00;" id="add_result"><span style="font-size: 20px;"><span style="background-color:#008000;">${addMessage}</span></span></span></p>
			
		<table align="center" border="1" cellpadding="1" cellspacing="1" style="width: 700px;">
			<thead>
				<tr>
					<th scope="col" style="text-align: center;">
						Book Title</th>
					<th scope="col" style="text-align: center;">
						Book Auther</th>
					<th scope="col" style="text-align: center;">
						Book Price</th>
					<th scope="col" style="text-align: center;">
						Stock Availability</th>
					<th scope="col" style="text-align: center;">
						Quantity Desired</th>
					<th scope="col" style="text-align: center;">
						Buy this book</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${bookList}">
					<form:form action="/bookstore/addBook" id="${item.id}" method="POST" modelAttribute="book" name="add_book_form_${item.id}">
						<input id="id" name="id" type="hidden" value="${item.id}"/>
						<tr>
							<td>${item.title}<input id="title" name="title" type="hidden" value="${item.title}"/></td>
							
							<td>${item.author}<input id="author" name="author" type="hidden" value="${item.author}"/></td>
							
							<td>${item.price}<input id="price" name="price" type="hidden" value="${item.price}"/></td>
							
							<td>${item.quantity}<input id="quantity" name="quantity" type="hidden" value="${item.quantity}"/></td>
							
							<td>
								<input id="toBuyQuantity" name="toBuyQuantity" type="text" value="1" />&nbsp;
							</td>
							
							<td><input type="submit" value="Buy Book"/></td>
						</tr>
					</form:form>
				</c:forEach>
			</tbody>
		</table>
		
		<table align="center" border="0" cellpadding="0" cellspacing="0" style="width: 700px;">
			<tbody>
				<tr>
					<td>
						<a href="searchBook">Can't find your book? Let's search!</a></td>
					<td style="text-align: right;">
						<a href="checkout">Enough! Let's checkout</a></td>
				</tr>
			</tbody>
		</table>
	</body>
</html>
