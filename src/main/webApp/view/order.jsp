<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="pageContext" var="lang" scope="session"/>
<fmt:setBundle basename="config" var="pattern" scope="session"/>
<html>
<head>
    <title>Order</title>
    <style>
        <%@include file="../css/form.css"%>
    </style>
</head>
<body>
<jsp:include page="../include/header.jsp" flush="true"/>
<form action="${pageContext.servletContext.contextPath}/order" method="post">

    <b><fmt:message key="order.address" bundle="${lang}"/>: </b>
    <input type="text" class="textbox" name="address"
           pattern="<fmt:message key="pattern.address" bundle="${pattern}"/>"
           value="${address_value}" required/>
    <br/>

    <b><fmt:message key="order.postalCode" bundle="${lang}"/>: </b>
    <input type="text" class="textbox" name="postalCode"
           pattern="<fmt:message key="pattern.postalCode" bundle="${pattern}"/>"
           value="${postalCode_value}"/>
    <br/>

    <b><fmt:message key="order.city" bundle="${lang}"/>: </b>
    <input type="text" class="textbox" name="city"
           pattern="<fmt:message key="pattern.city" bundle="${pattern}"/>"
           value="${city_value}" required/>
    <br/>

    <b><fmt:message key="order.country" bundle="${lang}"/>: </b>
    <input type="text" class="textbox" name="country"
           pattern="<fmt:message key="pattern.country" bundle="${pattern}"/>"
           value="${country_value}"/>
    <br/>

    <input type="submit" class="button" value="<fmt:message key="order.submit" bundle="${lang}"/>">
</form>
</body>
</html>
