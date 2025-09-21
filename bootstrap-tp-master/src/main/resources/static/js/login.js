document.addEventListener('DOMContentLoaded', function() {
    var loginForm = document.getElementById('loginform');
    var responseDiv = document.getElementById('output');

    loginForm.addEventListener('submit', function(event) {
        event.preventDefault();

        var username = document.getElementById("username").value;
        if (username.length < 6) {  // Vérifier les conditions pour le champ username
            event.preventDefault();
            responseDiv.textContent = "Veuillez entrer un nom d'utilisateur";
            return; 
        }else if (username.length > 99 ) {  // Vérifie les conditions pour le champ lastname
            event.preventDefault();
            responseDiv.textContent = "Votre nom d'utilisateur est trop long";
            return;
        }
    
    
        var password = document.getElementById("password").value;
        var regex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[_\-;:!?\./\*&\$]).{12,}$/;
        if (! regex.test(password)) {    //Vérifie si le mot de passe contient bien les informations requises
            event.preventDefault();
            responseDiv.textContent = "Le mot de passe doit contenir au moins 12 caractères, dont une majuscule, une minuscule, un chiffre et un caractère spécial";
            return;
        }else if (password.length > 99 ) {  // Vérifie les conditions pour le champ lastname
            event.preventDefault();
            responseDiv.textContent = "Votre mot de passe est trop long";
            return;
        }

    // Effectuer la vérification AJAX
    console.log("o")
    $.ajax({
        type: "POST",
        url: "/check-login", // URL de l'endpoint pour vérifier le nom d'utilisateur sur le serveur
        data: { username: username, password: password },
        dataType: 'json', // Indiquer que la réponse est au format JSON
        success: function(response) {
            console.log(response);
            if (!response.isAuthenticate) {
                // Afficher un message d'erreur à l'utilisateur
                document.getElementById("error-message").style.display = "block";
                console.log("negatif")
            } else {
                // Le nom d'utilisateur n'existe pas, soumettre le formulaire
                document.getElementById("loginform").submit();
                console.log("positif")
            }
        },
        error: function(xhr, status, error) {
            // Gérer les erreurs de la requête AJAX
            console.error(error);
        }
    });
});
});