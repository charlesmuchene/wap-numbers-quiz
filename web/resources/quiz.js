window.onload = function() {
    const hintButton = document.getElementById('hint-button');
    const hint = document.getElementById('hint');
    hintButton.addEventListener('click', function() {
        hint.style.display = "inline";
    });
};