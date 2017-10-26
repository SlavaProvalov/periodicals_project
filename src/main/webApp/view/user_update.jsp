<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="currency" uri="/WEB-INF/custom.tld" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="pageContext" var="lang" scope="session"/>
<fmt:setBundle basename="messages" var="message" scope="session"/>
<fmt:setBundle basename="config" var="pattern" scope="session"/>
<html>
<head>
    <title>User update</title>
    <style>
        <%@include file="../css/form.css"%>
    </style>
</head>
<body>
<jsp:include page="../include/header.jsp" flush="true"/>
<form action="${pageContext.servletContext.contextPath}/user_update" method="post">

    <b><fmt:message key="user.password" bundle="${lang}"/> :</b>
    <input type="password" class="textbox" name="password" minlength="6" maxlength="16"
           pattern="<fmt:message key="pattern.password" bundle="${pattern}"/>"
           placeholder="******" required/>
    <br/>

    <b><fmt:message key="user.email" bundle="${lang}"/> :</b>
    <input type="email" class="textbox" name="email"
           pattern="<fmt:message key="pattern.email" bundle="${pattern}"/>"
           placeholder="example@mail.com"
           value="${email_prev}" required/>
    <c:if test="${emailError == 1}">
        <i class="error"><fmt:message key="message.emailExistError" bundle="${message}"/></i>
    </c:if>
    <br/>

    <b><fmt:message key="user.firstName" bundle="${lang}"/> :</b>
    <input type="text" class="textbox" name="firstName"
           pattern="<fmt:message key="pattern.name" bundle="${pattern}"/>"
           value="${firstName_prev}"/>
    <br/>

    <b><fmt:message key="user.lastName" bundle="${lang}"/> :</b>
    <input type="text" class="textbox" name="lastName"
           pattern="<fmt:message key="pattern.name" bundle="${pattern}"/>"
           value="${lastName_prev}"/>
    <br/>

    <b><fmt:message key="signUp.phone" bundle="${lang}"/> :</b>
    <input type="text" class="textbox" name="phone" minlength="8" maxlength="20"
           pattern="<fmt:message key="pattern.phone" bundle="${pattern}"/>"
           value="${phone_prev}"/>
    <br/>

    <input type="submit" class="button" value="<fmt:message key="user.update.submit" bundle="${lang}"/>">
</form>
</body>
</html>
