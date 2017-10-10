<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="currency" uri="/WEB-INF/custom.tld" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="pageContext" var="lang" scope="session"/>

<html>
<head>
    <title>Welcome page</title>
    <style>
        <%@include file="../css/tableStyle.css"%>
    </style>
</head>

<body>
<jsp:include page="../include/header.jsp" flush="true"/>

<table class="table_cool">
    <caption><fmt:message key="welcome.caption" bundle="${lang}"/></caption>

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
</body>
</html>
