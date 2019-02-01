<%--suppress ELValidationInJSP --%>
<form method='post'>
    <h3>Have fun with NumberQuiz!</h3>
    <p>Your current score is: ${score}</p>
    <p>Guess the next number in the sequence!</p>
    <p>${text}</p>

    <%
        boolean incorrect = (boolean) request.getAttribute("incorrect");
        boolean invalid = (boolean) request.getAttribute("invalid");

        if (incorrect) out.print("<p style='color:red'>Incorrect answer!</p>");
        if (invalid) out.print("<p style='color:red'>Invalid input! Please try again</p>");
    %>

    <p>Your answer:<input type='text' name='answer' value=''/></p>
    <p><input type='submit' name='next' value='Next'/></p>
</form>
