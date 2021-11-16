<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
<h1>Book Details</h1>

<img src='<c:url value="${book.cover}"></c:url>' />
    <ul>
        <li>Title: <c:out value ="${book.title}" /></li>
        <li>Author: <c:out value ="${book.author}" /> </li>
        <li>Rating: <c:out value ="${book.rating}" /> </li>
        </li>
    </ul>
</body>
</html>