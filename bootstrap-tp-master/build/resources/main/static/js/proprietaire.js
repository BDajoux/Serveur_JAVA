document.addEventListener('DOMContentLoaded', function() { // FICHIER A IMPORT DANS LE FORMULAIRE AVEC ID UNIQUEMENT
    var proprietaireForm = document.getElementById('proprietaireform');
    var responseDiv = document.getElementById('output');

    proprietaireForm.addEventListener('submit', function(event) {
        var proprietaire = document.getElementById("proprietaireId").value;
        if (proprietaire.length < 8) {  // Vérifier les conditions pour le champ proprietaire
            event.preventDefault();
            responseDiv.textContent = "Veuillez entrer un ID";
            return; 
        }else if (proprietaire.length > 8 ) {
            event.preventDefault();
            responseDiv.textContent = "Votre ID est trop long";
            return;
        }

        // Effectuer la vérification AJAX
    $.ajax({
        type: "POST",
        url: "/check-proprietaire", // URL de l'endpoint pour vérifier le nom d'utilisateur sur le serveur
        data: { proprietaire: proprietaire },
        dataType: 'json', // Indiquer que la réponse est au format JSON
        success: function(response) {
            console.log(response);
            if (!response.proprietaireExists) {
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