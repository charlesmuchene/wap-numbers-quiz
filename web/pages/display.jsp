<%--suppress ELValidationInJSP --%>
<form method='post'>
    <p>Your current score is: <span id="score-span">${score}</span></p>
    <p>Guess the next number in the sequence!</p>
    <p id="question-paragraph">${text}</p>
    <%
        boolean incorrect = (boolean) request.getAttribute("incorrect");
        boolean invalid = (boolean) request.getAttribute("invalid");

        if (incorrect) out.print("<p class='error'>Incorrect answer!</p>");
        if (invalid) out.print("<p class='error'>Invalid input! Please try again</p>");
    %>
    <p>Your answer: <input type='text' name='answer' value=''/></p>
    <p><input id="next-button" type='submit' name='next' value='Next'/></p>
</form>