<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Number Quiz</title>
</head>
<body>
This is served by JSP
<% if ((boolean) request.getAttribute("over")) {%>
<jsp:include page="pages/over.jsp"/>
<%
} else {%>
<jsp:include page="pages/display.jsp"/>
<%}%>

</body>
</html>
