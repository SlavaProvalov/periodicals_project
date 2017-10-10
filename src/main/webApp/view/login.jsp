<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="pageContext" var="lang" scope="session"/>
<fmt:setBundle basename="config" var="pattern" scope="session"/>
<fmt:setBundle basename="messages" var="message" scope="session"/>

<html>
<head>
    <title>Log In</title>
    <style>
        <%@include file="../css/form.css"%>
    </style>
</head>
<body>
<jsp:include page="../include/header.jsp" flush="true"/>
<form name="loginForm" method="post" action="${pageContext.servletContext.contextPath}/login">
    <br/>
    <c:if test="${errorLogin==1}">
        <p><i class="error"><fmt:message key="message.loginError" bundle="${message}"/></i></p>
    </c:if>
    <p><b><fmt:message key="user.login" bundle="${lang}"/> </b></p>
    <input type="text" name="login" class="textbox" minlength="4" maxlength="16"
           pattern="<fmt:message key="pattern.login" bundle="${pattern}"/>" value="${login_value}"
           placeholder="name" required/>
    <br/>
    <c:if test="${errorPassword==1}">
        <p><i class="error"><fmt:message key="message.passwordError" bundle="${message}"/></i></p>
    </c:if>
    <p><b><fmt:message key="user.password" bundle="${lang}"/> </b></p>
    <input type="password" name="password" class="textbox" minlength="6" maxlength="16"
           pattern="<fmt:message key="pattern.password" bundle="${pattern}"/>" value="${password_value}"
           placeholder="********" required/>
    <br/>
    <input type="submit" class="button" value="<fmt:message key="login.submit" bundle="${lang}"/>"/>
</form>
</body>
</html>
