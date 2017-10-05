<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<style>
    <%@include file="../css/menuStyle.css"%>
</style>
<header>
    <ul>
        <li>
            <a href="${pageContext.servletContext.contextPath}/login_page">${login}</a>
        </li>
        <li>
            <a href="${pageContext.servletContext.contextPath}/signUp_page">${signUp}</a>
        </li>
        <li>
            <a href="${pageContext.servletContext.contextPath}/cart">${cart}${items}</a>
        </li>
        <li class="logout">
            <a href="${pageContext.servletContext.contextPath}/logout">${logout}</a>
        </li>
        <li class="user_details">
            <a href="${pageContext.servletContext.contextPath}/user_details">${user_details}</a>
        </li>
        <li class="newPeriodical">
            <a href="${pageContext.servletContext.contextPath}/new_periodical_page">${newPeriodical}</a>
        </li>
        <li class="hi">
            <b>${hi}, ${userLogin}</b>
        </li>

    </ul>
</header>

