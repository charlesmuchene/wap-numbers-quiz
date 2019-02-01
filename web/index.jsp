<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Number Quiz</title>
    <link rel="stylesheet" href="resources/quiz.css">
</head>
<body>
<header><h1>Awesome number tests. Ready?</h1></header>
<main>
    <form method="post" action="${pageContext.request.contextPath}/quiz">
        <input type="submit" id="start-button" value="Begin" name="start">
    </form>
</main>
</body>
</html>
