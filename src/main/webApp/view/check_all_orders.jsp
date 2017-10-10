<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="currency" uri="/WEB-INF/custom.tld" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="pageContext" var="lang" scope="session"/>
<html>
<head>
    <title>All Orders</title>
    <style>
        <%@include file="../css/tableStyle.css"%>
    </style>
</head>
<body>
<jsp:include page="../include/header.jsp" flush="true"/>

<table class="table_orders">
    <caption><fmt:message key="welcome.caption" bundle="${lang}"/></caption>

    <tr>
        <th><fmt:message key="order.clientId" bundle="${lang}"/></th>
        <th><fmt:message key="order.address" bundle="${lang}"/></th>
        <th><fmt:message key="order.city" bundle="${lang}"/></th>
        <th><fmt:message key="order.postalCode" bundle="${lang}"/></th>
        <th><fmt:message key="order.country" bundle="${lang}"/></th>
        <th><fmt:message key="order.date" bundle="${lang}"/></th>
        <th><fmt:message key="order.periodicals" bundle="${lang}"/></th>
    </tr>

    <c:forEach items="${ordersList}" var="item" varStatus="status">
        <fmt:parseDate value="${item.orderDate}" pattern="yyyy-MM-dd" var="parsedDate" type="date"/>
        <tr valign="top">
            <td>${item.clientId}</td>
            <td>${item.address}</td>
            <td>${item.city}</td>
            <td>${item.postalCode}</td>
            <td>${item.country}</td>
            <td><fmt:formatDate value="${parsedDate}" type="date" pattern="yyyy-MM-dd"/></td>
            <td>
                <table class="inner_table">
                    <c:forEach items="${item.periodicals}" var="periodical" varStatus="status">
                        <tr>
                            <td>${periodical.title}</td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
