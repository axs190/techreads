<!DOCTYPE html>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<body>
<h1>Edit Book</h1>
<form:form method="post" modelAttribute= "bookForm" action="${pageContext.request.contextPath}/books/edit">
    <!-- Gets the ID so that it does not change it-->
    <form:input path="id" type="hidden" />
    <label>Title: </label> <form:input path="title" type="text"/>
    <label>Author: </label><form:input path="author" type="text"/>
    <label>Rating: </label><form:input path="rating" type="text"/>
    <label>Cover: </label><form:input path="cover" type="text"/>
    <button type ="submit">Add</button></form:form>

</body>
</html>