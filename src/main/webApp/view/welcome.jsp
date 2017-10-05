<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>Welcome page</title>
    <style>
        <%@include file="../css/tableStyle.css"%>
    </style>
</head>

<body>
<jsp:include page="../include/header.jsp" flush="true" />

<table class="table_cool">
    <caption>${caption}</caption>
    <tr>
        <th>${title}</th>
        <th>${frequency}</th>
        <th>${description}</th>
        <th>${price}</th>
    </tr>
    <c:forEach items="${periodicalsList}" var="Item" varStatus="status">
        <tr valign="top">
            <td>${Item.title}</td>
            <td>${Item.publicationFrequency}</td>
            <td>${Item.description}</td>
            <td>${Item.subscriptionPrice}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
