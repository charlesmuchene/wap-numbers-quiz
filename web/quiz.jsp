<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Number Quiz</title>
    <link rel="stylesheet" href="resources/quiz.css">
</head>
<body>
<header><h1>The Number Quiz. Enjoy!</h1></header>
<main>
<% if ((boolean) request.getAttribute("over")) {%>
<jsp:include page="pages/over.jsp"/>
<%
} else {%>
<jsp:include page="pages/display.jsp"/>
<%}%>
</main>
</body>
</html>
