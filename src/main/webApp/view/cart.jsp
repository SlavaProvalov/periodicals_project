<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="currency" uri="/WEB-INF/custom.tld" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="pageContext" var="lang" scope="session"/>

<html>
<head>
    <title>Cart</title>
    <style>
        <%@include file="../css/tableStyle.css"%>
    </style>
</head>
<body>
<jsp:include page="../include/header.jsp" flush="true"/>

<table class="table_cool">
    <caption>${caption}</caption>

    <jsp:include page="../include/periodicalsHead.jsp" flush="true"/>

    <c:forEach items="${cartList}" var="item" varStatus="status">
        <tr valign="top">
            <td>${item.title}</td>
            <td>${item.publicationFrequency}</td>
            <c:if test="${sessionScope.language == 'en'}">
                <td>${item.enDescription}</td>
            </c:if>
            <c:if test="${sessionScope.language == 'ru'}">
                <td>${item.ruDescription}</td>
            </c:if>
            <td><currency:currency value="${item.subscriptionPrice}"
                                   currType="${sessionScope.curr_type}"
                                   rate="${sessionScope.curr_rate}"/>
            </td>
            <td><a href="${pageContext.servletContext.contextPath}/delete_from_cart?Item_id=${item.id}">
                <fmt:message key="cart.delete" bundle="${lang}"/></a>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="3"><b><fmt:message key="cart.summary" bundle="${lang}"/></b></td>
        <td><b><currency:currency value="${sessionScope.total_cost}" currType="${sessionScope.curr_type}"
                                  rate="${sessionScope.curr_rate}"/></b></td>
        <td></td>
    </tr>
</table>
<a class="checkout" href="${pageContext.servletContext.contextPath}/order_page">
    <fmt:message key="cart.checkout" bundle="${lang}"/></a>
</body>
</html>
