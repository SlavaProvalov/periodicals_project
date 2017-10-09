<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="pageContext" var="lang" scope="session"/>
<html>
<head>
    <title>Success</title>
    <style>
        <%@include file="../css/text.css"%>
    </style>
    <meta http-equiv="Refresh"
          content="3;url=${redirectServlet}">
</head>
<body>
<jsp:include page="../include/header.jsp" flush="true" />
<p><b>${success}</b></p>
<br/>
<p><i>${redirect}</i></p>
</body>
</html>
