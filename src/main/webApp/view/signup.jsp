<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="pageContext" var="lang" scope="session"/>
<fmt:setBundle basename="messages" var="message" scope="session"/>
<fmt:setBundle basename="config" var="pattern" scope="session"/>
<html>
<head>
    <title>Sign Up</title>
    <style>
        <%@include file="../css/form.css"%>
    </style>
</head>
<body>
<jsp:include page="../include/header.jsp" flush="true"/>

<form action="${pageContext.servletContext.contextPath}/signUp" method="post">
    <c:if test="${errorLogin == 1}">
        <i class="error"><fmt:message key="message.loginExistError" bundle="${message}"/></i>
    </c:if>
    <b><fmt:message key="user.login" bundle="${lang}"/>:</b>
    <input type="text" class="textbox" name="login" minlength="4" maxlength="16"
           pattern="<fmt:message key="pattern.login" bundle="${pattern}"/>"
           value="${login_value}" placeholder="example99" required/>
    <br/>

    <b><fmt:message key="user.password" bundle="${lang}"/> :</b>
    <input type="password" class="textbox" name="password" minlength="6" maxlength="16"
           pattern="<fmt:message key="pattern.password" bundle="${pattern}"/>"
           value="${password_value}" placeholder="******" required/>
    <br/>

    <c:if test="${errorEmail == 1}">
        <i class="error"><fmt:message key="message.emailExistError" bundle="${message}"/></i>
    </c:if>
    <b><fmt:message key="user.email" bundle="${lang}"/> :</b>
    <input type="email" class="textbox" name="email"
           pattern="<fmt:message key="pattern.email" bundle="${pattern}"/>"
           placeholder="example@mail.com" value="${email_value}" required/>
    <br/>

    <b><fmt:message key="user.firstName" bundle="${lang}"/> :</b>
    <input type="text" class="textbox" name="firstName"
           pattern="<fmt:message key="pattern.name" bundle="${pattern}"/>"
           value="${firstName_value}"/>
    <br/>

    <b><fmt:message key="user.lastName" bundle="${lang}"/> :</b>
    <input type="text" class="textbox" name="lastName"
           pattern="<fmt:message key="pattern.name" bundle="${pattern}"/>"
           value="${lastName_value}"/>
    <br/>

    <b><fmt:message key="signUp.phone" bundle="${lang}"/> :</b>
    <input type="tel" class="textbox" name="phone"
           pattern="<fmt:message key="pattern.phone" bundle="${pattern}"/>"
           value="${phone_value}" placeholder="<fmt:message key="signUp.phoneStandard" bundle="${lang}"/>"/>
    <br/>

    <input type="submit" class="button" value="<fmt:message key="signUp.submit" bundle="${lang}"/>">
</form>
</body>
</html>
