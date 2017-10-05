<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>Main Page</title>
    <style>
        <%@include file="../css/menuStyle.css"%>
        <%@include file="../css/tableStyle.css"%>
    </style>
</head>
<body>
<jsp:include page="../include/header.jsp"></jsp:include>

<table class="table_cool">
    <caption>${caption}</caption>
    <tr>
        <th>${title}</th>
        <th>${frequency}</th>
        <th>${price}</th>
        <th>${description}</th>
        <th></th>
    </tr>
    <c:forEach items="${periodicalsList}" var="Item" varStatus="status">
        <tr valign="top">
            <td>${Item.title}</td>
            <td>${Item.publicationFrequency}</td>
            <td>${Item.subscriptionPrice}</td>
            <td>${Item.description}</td>
            <td><a href="${pageContext.servletContext.contextPath}/add_to_cart?pItem_id=${Item.id}">${add}</a></td>
        </tr>
    </c:forEach>

</table>

<p>My Name: ${userLogin}</p>
</body>
</html>
