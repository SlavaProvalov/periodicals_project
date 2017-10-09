<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="pageContext" var="lang" scope="session"/>
<tr>
    <th class="title"><fmt:message key="periodical.title" bundle="${lang}"/></th>
    <th class="frequency"><fmt:message key="periodical.frequency" bundle="${lang}"/></th>
    <th class="description"><fmt:message key="periodical.description" bundle="${lang}"/></th>
    <th class="price"><fmt:message key="periodical.price" bundle="${lang}"/></th>
</tr>

