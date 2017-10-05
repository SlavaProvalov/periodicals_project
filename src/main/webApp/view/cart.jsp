<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--<fmt:setBundle basename="pageContext"/>--%>
<%--<fmt:setLocale value="en"/>--%>
<html>
<head>
    <title>Cart</title>
    <style>
        <%@include file="../css/menuStyle.css"%>
        <%@include file="../css/tableStyle.css"%>
    </style>
</head>
<body>
<jsp:include page="../include/header.jsp" flush="true"/>

<table class="table_cool">
    <caption>${caption}</caption>
    <tr>
        <th>${title}</th>
        <th>${frequency}</th>
        <th>${description}</th>
        <th>${price}</th>
        <th></th>
    </tr>
    <c:forEach items="${cartList}" var="Item" varStatus="status">
        <tr valign="top">
            <td>${Item.title}</td>
            <td>${Item.publicationFrequency}</td>
            <td>${Item.description}</td>
            <td>${Item.subscriptionPrice}</td>
            <td><a href="${pageContext.servletContext.contextPath}/delete_from_cart?Item_id=${Item.id}">${delete}</a>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="4"><b>${summary}</b></td>
        <td><b>${total_cost}</b></td>
    </tr>
</table>
<a class="checkout" href="${pageContext.servletContext.contextPath}/order_page">${checkout}</a>

</body>
</html>
