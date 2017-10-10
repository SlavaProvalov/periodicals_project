<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="pageContext" var="lang" scope="session"/>

<style>
    <%@include file="../css/menuStyle.css"%>
</style>
<c:set var="guest" value="${sessionScope.role == 'GUEST'}"/>
<c:set var="useradmin" value="${sessionScope.role == 'USER' or sessionScope.role == 'ADMIN'}"/>
<c:set var="admin" value="${sessionScope.role == 'ADMIN'}"/>
<header>
    <ul>
        <li>
            <c:if test="${useradmin}">
                <a href="${pageContext.servletContext.contextPath}/main">
                    <fmt:message key="header.main" bundle="${lang}"/>
                </a>
            </c:if>
        </li>
        <li>
            <c:if test="${guest}">
                <a href="${pageContext.servletContext.contextPath}/login_page">
                    <fmt:message key="header.login" bundle="${lang}"/>
                </a>
            </c:if>
        </li>

        <li>
            <c:if test="${guest}">
                <a href="${pageContext.servletContext.contextPath}/signUp_page">
                    <fmt:message key="header.signUp" bundle="${lang}"/></a>
            </c:if>
        </li>

        <li>
            <c:if test="${useradmin}">
                <a href="${pageContext.servletContext.contextPath}/cart">
                    <fmt:message key="header.cart" bundle="${lang}"/>
                    (<c:out value="${items}" default="${sessionScope.cartEmpty}"/>)</a>
            </c:if>
        </li>
        <li class="change_language">
            <a id="lang_en" href="${pageContext.servletContext.contextPath}/change_language?language=en">
                <fmt:message key="header.lang.en" bundle="${lang}"/>

            </a>
            <a id="lang_ru" href="${pageContext.servletContext.contextPath}/change_language?language=ru">
                <fmt:message key="header.lang.ru" bundle="${lang}"/>
            </a>
        </li>
        <li class="logout">
            <c:if test="${useradmin}">
                <a href="${pageContext.servletContext.contextPath}/logout">
                    <fmt:message key="header.logout" bundle="${lang}"/>
                </a>
            </c:if>
        </li>
        <li class="user_details">
            <c:if test="${useradmin}">
                <a href="${pageContext.servletContext.contextPath}/user_details">
                    <fmt:message key="header.user_details" bundle="${lang}"/>
                </a>
            </c:if>
        </li>
        <li class="check_all_orders">
            <c:if test="${admin}">
                <a href="${pageContext.servletContext.contextPath}/check_all_orders">
                    <fmt:message key="header.check_all_orders" bundle="${lang}"/>
                </a>
            </c:if>
        </li>
        <li class="newPeriodical">
            <c:if test="${admin}">
                <a href="${pageContext.servletContext.contextPath}/new_periodical_page">
                    <fmt:message key="header.newPeriodical" bundle="${lang}"/>
                </a>
            </c:if>
        </li>
        <li class="hi">
            <b> <fmt:message key="header.hi" bundle="${lang}"/> <c:out value="${sessionScope.userLogin}"
                                                                       default="${sessionScope.guest}"/></b>
        </li>
    </ul>
</header>

