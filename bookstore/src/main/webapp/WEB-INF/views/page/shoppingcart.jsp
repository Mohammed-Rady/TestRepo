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
			<span style="font-size:36px;">Shopping Cart</span></p>
			
		<table align="center" border="1" cellpadding="1" cellspacing="1" style="width: 700px;">
			<thead>
				<tr>
					<th scope="col">
						Book</th>
					<th scope="col">
						Quantity</th>
					<th scope="col">
						Status</th>
					<th scope="col">
						Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${shoppingCart.booksInCart}" varStatus="count">
					<form:form action="/bookstore/deleteBook" id="${item.id}" method="POST" modelAttribute="book" name="delete_book_form_${item.id}">
					<input id="id" name="id" type="hidden" value="${item.id}"/>
						<tr>
							<td>
								${item.title}<input id="title" name="title" type="hidden" value="${item.title}"/></td>
							<td>
								${item.toBuyQuantity}<input id=toBuyQuantity name="toBuyQuantity" type="hidden" value="${item.toBuyQuantity}"/></td>
							<td>
								<c:choose>
								    <c:when test="${item.status == 0}">
								       It will be shipped to your deliver address.
								    </c:when>
								    <c:when test="${item.status == 1}">
								        Sorry, it is out of stock. Please try later.
								    </c:when>
								    <c:when test="${item.status == 2}">
								        No longer available. Sorry!
								    </c:when>
								</c:choose>
								<input id="status" name="status" type="hidden" value="${item.status}"/></td>
							<td><input type="submit" value="Delete"/></td>
						</tr>
					</form:form>
				</c:forEach>
			</tbody>
		</table>
		
		<table align="center" border="0" cellpadding="0" cellspacing="0" style="width: 700px;">
			<tbody>
				<tr>
					<td>
						<a href="/bookstore/">Back to the books world!</a></td>
					<td style="text-align: right;">
						<a href="#"></a></td>
				</tr>
			</tbody>
		</table>
		
	</body>
</html>
