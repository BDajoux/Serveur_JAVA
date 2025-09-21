document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("form").addEventListener("submit", function(event) {
        var responseDiv = document.getElementById("output"); // initialise l'endroit ou sera envoyé la réponse
        var proprietaireName = document.getElementById("proprietaireName").value;
        var proprietaireSurname = document.getElementById("proprietaireSurname").value;
// Effectuer la vérification AJAX
$.ajax({
    type: "POST",
    url: "/check-proprietaire2", // URL de l'endpoint pour vérifier le nom d'utilisateur sur le serveur
    data: { proprietaireName: proprietaireName , proprietaireSurname: proprietaireSurname},
    dataType: 'json', // Indiquer que la réponse est au format JSON
    success: function(response) {
        console.log(response);
        if (response.proprietaireExists) {
            document.getElementById("error-message").style.display = "block";
        } else {
            document.getElementById("form").submit();
        }
    },
    error: function(xhr, status, error) {
        // Gérer les erreurs de la requête AJAX
        console.error(error);
    }
});
    });
});