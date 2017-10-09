<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="messages" var="message" scope="session"/>
<html>
<head>
    <title>Error Page</title>
    <style>
        <%@include file="../css/text.css"%>
    </style>
    <meta http-equiv="Refresh"
          content="5;url=${redirectServlet}">
</head>
<body>
<jsp:include page="../include/header.jsp" flush="true"/>
<p><fmt:message key="message.errorTitle" bundle="${message}"/></p>
<p><fmt:message key="message.errorCode" bundle="${message}"/><c:out value="${errorCode}"/></p>
<p><fmt:message key="message.dbError" bundle="${message}"/> <c:out value="${errorState}"/></p>
<p><c:out value="${errorMessage}"/></p>
<p><fmt:message key="message.error.redirect" bundle="${message}"/></p>

</body>
</html>
