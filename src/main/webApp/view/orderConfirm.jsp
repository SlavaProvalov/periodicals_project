<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>Order Confirm</title>
    <style>
        <%@include file="../css/menuStyle.css"%>
        <%@include file="../css/tableStyle.css"%>
        <%@include file="../css/text.css"%>
    </style>
</head>
<body>
<jsp:include page="../include/header.jsp" flush="true" />
<br/>
<b>${thankyou}</b>
<br/>
<table class="table_cool">
    <caption>${caption}</caption>
    <tr>
        <th>${title}</th>
        <th>${frequency}</th>
        <th>${description}</th>
        <th>${price}</th>
    </tr>
    <c:forEach items="${cartList}" var="Item" varStatus="status">
        <tr valign="top">
            <td>${Item.title}</td>
            <td>${Item.publicationFrequency}</td>
            <td>${Item.description}</td>
            <td>${Item.subscriptionPrice}</td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="3"><b>${summary}</b></td>
        <td><b>${total_cost}</b></td>
    </tr>
</table>
<br/>
<p>${order_address}: ${address}</p>
<p> ${order_postalCode}: ${postalCode}</p>
<p> ${order_city}: ${city}</p>
<p> ${order_country}: ${country}</p>

</body>
</html>
