<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <body>
        <h1>Books</h1>
        <a href="<c:url value ="/books/add" />">Add Books</a>
        <c:if test ="${not empty books}">
            <ul>
                <c:forEach var ="book" items="${books}">
                <li><c:out value ="${book.title}" />
                    <a href="<c:url value ="/books/delete/${book.id}" />">   Remove</a>
                    <a href="<c:url value ="/books/detail/${book.id}" />">   Detail</a>
                    <a href="<c:url value ="/books/edit/${book.id}" />">   Edit</a>
                </li>
                </c:forEach>
            </ul>
        </c:if>


    </body>
</html>