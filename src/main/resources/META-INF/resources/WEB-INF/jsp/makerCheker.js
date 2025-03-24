// makerChecker.js
document.addEventListener("DOMContentLoaded", function() {
    var modal = document.getElementById("makerCheckerModal");
    var btn = document.getElementById("openMakerCheckerForm");
    var span = document.getElementsByClassName("close")[0];
 
    btn.onclick = function() {
        modal.style.display = "block";
        // Initialize TinyMCE when opening the modal
        tinymce.init({
            selector: 'textarea#kfsTextContent',
            height: 400,
            plugins: 'lists advlist',
            toolbar: 'undo redo | formatselect | bold italic | alignleft aligncenter alignright | bullist numlist',
            menubar: 'edit format',
            setup: function (editor) {
                editor.on('init', function (e) {
                    // Set initial content if needed
                });
            }
        });
    }
 
    span.onclick = function() {
        modal.style.display = "none";
        tinymce.remove();  // Remove TinyMCE instance when closing the modal
    }
 
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
            tinymce.remove();  // Remove TinyMCE instance when closing the modal
        }
    }
});