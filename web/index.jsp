<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Number Quiz</title>
    <link rel="stylesheet" href="resources/main.css">
</head>
<body>
<header><h1>Welcome to the <em>number quiz</em>.</h1></header>
<main>
    <form method="post" action="${pageContext.request.contextPath}/quiz">
        <label>
            Choose the backend to use:
            <select name="backend">
                <option value="jsp">JSP</option>
                <option value="servlet">Servlet</option>
            </select>
        </label>
        <br>
        <input type="submit" id="start-button" value="Begin" name="start">
    </form>
</main>
</body>
</html>
