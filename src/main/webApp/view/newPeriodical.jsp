<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>Create New Periodical</title>
    <style>
        <%@include file="../css/form.css"%>
        <%@include file="../css/menuStyle.css"%>
    </style>
</head>
<body>
<jsp:include page="../include/header.jsp" flush="true" />
<form action="${pageContext.servletContext.contextPath}/new_periodical" method="post">
    <b>${title} :</b>
    <input type="text" class="textbox" name="title" minlength="1" maxlength="32" pattern="${pattern_title}"
           placeholder="some magazine" required/>
    <br/>
    <b>${frequency} :</b>
    <input type="number" class="textbox" name="frequency"
           placeholder="30" required/>
    <br/>
    <b>${price} :</b>
    <input type="text" class="textbox" name="price" minlength="3" pattern="${pattern_price}" placeholder="999"
           required/>
    <br/>
    <b>${ruDescription} :</b>
    <textarea rows="5" cols="75" name="ruDescription" placeholder="Описание по-русски" required></textarea>
    <br/>
    <b>${enDescription} :</b>
    <textarea rows="5" cols="75" name="enDescription" placeholder="English description" required></textarea>
    <br/>
    <input type="submit" class="button" value="${submit}">

</body>
</html>
