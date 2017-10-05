<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>Log In</title>
    <style>
        <%@include file="../css/form.css"%>
    </style>
</head>
<body>
<jsp:include page="../include/header.jsp" flush="true" />
<form name="loginForm" method="post" action="${pageContext.servletContext.contextPath}/login">
    <br/>
    <b>${login} :<b/>
    <input type="text" name="login" class="textbox" minlength="4" maxlength="16" pattern="${pattern_login}" value="${login_value}"
           placeholder="example" required/>
    <br/>
    <b>${password} :</b>
    <input type="password" name="password" class="textbox"  minlength="6" maxlength="16" pattern="${pattern_password}" value="${password_value}"
           placeholder="******" required/>
    <br/>
    <i class="error">${errorLoginPassMessage}</i>
    <br/>
    <input type="submit" class="button" value="${submit}"/>
</form>
<hr/>
</body>
</html>
