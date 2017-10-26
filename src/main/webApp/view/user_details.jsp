<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="currency" uri="/WEB-INF/custom.tld" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="pageContext" var="lang" scope="session"/>
<fmt:setBundle basename="messages" var="message" scope="session"/>
<html>
<head>
    <title>User details</title>
    <style>
        <%@include file="../css/tableStyle.css"%>
        <%@include file="../css/text.css"%>
    </style>
</head>
<body>
<jsp:include page="../include/header.jsp" flush="true"/>
<br/>
<p><fmt:message key="user.details.login" bundle="${lang}"/>: ${sessionScope.userLogin}</p>
<fmt:parseDate value="${signUpDate}" pattern="yyyy-MM-dd" var="parsedDate" type="date"/>
<p><fmt:message key="user.signUpDate" bundle="${lang}"/>:
    <fmt:formatDate value="${parsedDate}" type="date" pattern="yyyy-MM-dd"/></p>
<p><fmt:message key="user.role" bundle="${lang}"/>: ${sessionScope.role}</p>
<p><fmt:message key="user.firstName" bundle="${lang}"/>: ${firstName}</p>
<p><fmt:message key="user.lastName" bundle="${lang}"/>: ${lastName}</p>
<p><fmt:message key="user.email" bundle="${lang}"/>: ${email}</p>
<p><fmt:message key="user.phoneNumber" bundle="${lang}"/>: ${phoneNumber}</p>

<br/>
<a class="button" href="${pageContext.servletContext.contextPath}/user_update_page">
    <fmt:message key="user.update" bundle="${lang}"/>
</a>
<br/>
<c:if test="${listSize > 0}">
    <table class="table_cool">
        <caption><fmt:message key="user.caption" bundle="${lang}"/></caption>

        <jsp:include page="../include/periodicalsHead.jsp" flush="true"/>

        <c:forEach items="${periodicalsList}" var="item" varStatus="status">
            <tr valign="top">
                <td>${item.title}</td>
                <td>${item.publicationFrequency}</td>
                <c:if test="${sessionScope.language == 'en'}">
                    <td>${item.enDescription}</td>
                </c:if>
                <c:if test="${sessionScope.language == 'ru'}">
                    <td>${item.ruDescription}</td>
                </c:if>
                <td><currency:currency value="${item.subscriptionPrice}"
                                       currType="${sessionScope.curr_type}"
                                       rate="${sessionScope.curr_rate}"/>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
