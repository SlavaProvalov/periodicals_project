<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>Order</title>
    <style>
        <%@include file="../css/menuStyle.css"%>
        <%@include file="../css/form.css"%>
    </style>
</head>
<body>
<jsp:include page="../include/header.jsp" flush="true" />
<form action="${pageContext.servletContext.contextPath}/order" method="post">
    <b>${address} :</b>
    <input type="text" class="textbox" name="address"
           pattern="${pattern_address}" value="${address_value}"  required/>
    <br/>
    <b>${postalCode} :</b>
    <input type="text" class="textbox" name="postalCode" pattern="${pattern_postalCode}"
           value="${postalCode_value}" />
    <br/>
    <b>${city} :</b>
    <input type="text" class="textbox" name="city"
           pattern="${pattern_city}" value="${city_value}"  required/>
    <br/>
    <b>${country} :</b>
    <input type="text" class="textbox" name="country" pattern="${pattern_country}"
           value="${country_value}"/>
    <br/>
    <input type="submit" class="button" value="${submit}">
</form>
</body>
</html>
