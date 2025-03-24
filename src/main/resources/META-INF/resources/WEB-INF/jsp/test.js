// Existing script.js content
// Add your existing script.js content here
 
// Modal related code
document.addEventListener("DOMContentLoaded", function() {
    var modal = document.getElementById("makerCheckerModal");
    var btn = document.getElementById("openMakerCheckerForm");
    var span = document.getElementsByClassName("close")[0];
    var iframe = document.getElementById("makerCheckerFrame");
 
    btn.onclick = function() {
        iframe.src = "makerChecker.htm";
        modal.style.display = "block";
    }
 
    span.onclick = function() {
        modal.style.display = "none";
        iframe.src = "about:blank";
    }
 
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
            iframe.src = "about:blank";
        }
    }
});