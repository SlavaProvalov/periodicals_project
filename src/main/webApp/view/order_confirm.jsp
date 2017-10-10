<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="currency" uri="/WEB-INF/custom.tld" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="pageContext" var="lang" scope="session"/>
<html>
<head>
    <title>Order Confirm</title>
    <style>
        <%@include file="../css/tableStyle.css"%>
        <%@include file="../css/text.css"%>
    </style>
</head>
<body>
<jsp:include page="../include/header.jsp" flush="true"/>
<br/>
<p><b><fmt:message key="order.thankYou" bundle="${lang}"/></b></p>
<br/>
<table class="table_cool">
    <caption><fmt:message key="cart.caption" bundle="${lang}"/></caption>

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
        </tr>
    </c:forEach>
    <tr>
        <td colspan="3"><b><fmt:message key="cart.summary" bundle="${lang}"/></b></td>
        <td><b><currency:currency value="${sessionScope.total_cost}" currType="${sessionScope.curr_type}"
                                  rate="${sessionScope.curr_rate}"/></b></td>
    </tr>
</table>
<br/>
<p><fmt:message key="order.address" bundle="${lang}"/>: ${sessionScope.address_value}</p>
<p><fmt:message key="order.postalCode" bundle="${lang}"/>: ${sessionScope.postalCode_value}</p>
<p><fmt:message key="order.city" bundle="${lang}"/>: ${sessionScope.city_value}</p>
<p><fmt:message key="order.country" bundle="${lang}"/>: ${sessionScope.country_value}</p>

</body>
</html>
