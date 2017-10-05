<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>Success</title>
    <meta http-equiv="Refresh"
          content="3;url=${redirectServlet}">
</head>
<body>
<jsp:include page="../include/header.jsp" flush="true" />
<b>${success}</b>
<br/>
<i>${redirect}</i>
</body>
</html>
