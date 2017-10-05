<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>User details</title>
    <style>
        <%@include file="../css/menuStyle.css"%>
        <%@include file="../css/tableStyle.css"%>
        <%@include file="../css/text.css"%>
    </style>
</head>
<body>
<jsp:include page="../include/header.jsp" flush="true" />
<br/>
<p>${user_login}: ${login}</p>
<p>${user_role}: ${role}</p>
<p>${user_firstName}: ${firstName}</p>
<p>${user_lastName}: ${lastName}</p>
<p>${user_email}: ${email}</p>
<p>${user_phoneNumber}: ${phoneNumber}</p>
<br/>
<table class="table_cool">
    <caption>${caption}</caption>
    <tr>
        <th>${title}</th>
        <th>${frequency}</th>
        <th>${description}</th>
        <th>${price}</th>
    </tr>
    <c:forEach items="${periodicalsList}" var="Item" varStatus="status">
        <tr valign="top">
            <td>${Item.title}</td>
            <td>${Item.publicationFrequency}</td>
            <td>${Item.description}</td>
            <td>${Item.subscriptionPrice}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
