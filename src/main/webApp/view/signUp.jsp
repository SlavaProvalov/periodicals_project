<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>Sign Up</title>
    <style>
        <%@include file="../css/form.css"%>
    </style>
</head>
<body>
<jsp:include page="../include/header.jsp" flush="true" />

<form action="${pageContext.servletContext.contextPath}/signUp" method="post">
    <b>${login}:</b>
    <input type="text" class="textbox" name="login" minlength="4" maxlength="16"
           pattern="${pattern_login}" value="${login_value}" placeholder="example" required/>
    <i class="error">${errorLoginExist}</i>
    <br/>
    <b>${password} :</b>
    <input type="password" class="textbox" name="password" minlength="6" maxlength="16"
           pattern="${pattern_password}" value="${password_value}" placeholder="******" required/>
    <br/>
    <b>${email} :</b>
    <input type="email" class="textbox" name="email" pattern="${pattern_email}"
           placeholder="example@mail.com" value="${email_value}" required/>
    <i class="error">${errorEmailExist}</i>
    <br/>
    <b>${name} :</b>
    <input type="text" class="textbox" name="firstName" pattern="${pattern_name}"
           value="${firstName_value}"/>
    <br/>
    <b>${lastName} :</b>
    <input type="text" class="textbox" name="lastName" pattern="${pattern_name}"
           value="${lastName_value}"/>
    <br/>
    <b>${phone} :</b>
    <br/>
    <input type="text" class="textbox" name="phone" pattern="${pattern_phone}"
           value="${phone_value}" placeholder="${phone_ph}"/>
    <br/>

    <input type="submit" class="button" value="${submit}">
</form>
</body>
</html>
