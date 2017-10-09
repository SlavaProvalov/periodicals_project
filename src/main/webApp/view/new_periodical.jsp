<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="pageContext" var="lang" scope="session"/>
<fmt:setBundle basename="config" var="pattern" scope="session"/>
<html>
<head>
    <title>Create New Periodical</title>
    <style>
        <%@include file="../css/form.css"%>
    </style>
</head>
<body>
<jsp:include page="../include/header.jsp" flush="true"/>
<form action="${pageContext.servletContext.contextPath}/new_periodical" method="post">

    <b><fmt:message key="periodical.title" bundle="${lang}"/>: </b>
    <input type="text" class="textbox" name="title" minlength="1" maxlength="32" pattern="<fmt:message key="pattern.title" bundle="${pattern}"/>"
           placeholder="some magazine" required/>
    <br/>
    <b><fmt:message key="periodical.frequency" bundle="${lang}"/>: </b>
    <input type="number" class="textbox" name="frequency"
           placeholder="days" required/>
    <br/>

    <b><fmt:message key="periodical.price" bundle="${lang}"/>: </b>
    <input type="text" class="textbox" name="price" minlength="3" pattern="<fmt:message key="pattern.price" bundle="${pattern}"/>" placeholder="0.99"
           required/>
    <br/>
    <b><fmt:message key="newPeriodical.ruDescription" bundle="${lang}"/>:</b>
    <textarea class="message" rows="7" cols="50" name="ruDescription" placeholder="Описание по-русски" maxlength="450" required></textarea>
    <br/>
    <b><fmt:message key="newPeriodical.enDescription" bundle="${lang}"/>:</b>
    <textarea class="message" rows="7" cols="50" name="enDescription" placeholder="English description" maxlength="450" required></textarea>
    <br/>
    <input type="submit" class="button" value="<fmt:message key="newPeriodical.submit" bundle="${lang}"/>">
</form>
</body>
</html>
