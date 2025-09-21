document.addEventListener("DOMContentLoaded", function() {
document.getElementById("form").addEventListener("submit", function(event) {
    var responseDiv = document.getElementById("output"); // initialise l'endroit ou sera envoyé la réponse

    var lastname = document.getElementById("lastname").value;
    if (lastname === "") {  // Vérifie les conditions pour le champ lastname
        event.preventDefault();
        responseDiv.textContent = "Veuillez entrer un nom";
        return;
    }else if (lastname.length > 99 ) {  // Vérifie les conditions pour le champ lastname
        event.preventDefault();
        responseDiv.textContent = "Votre nom est trop long";
        return;
    }
    
    var firstname = document.getElementById("firstname").value;    
    if (firstname === "") { // Vérifie les conditions pour le champ firstname
        event.preventDefault();
        responseDiv.textContent = "Veuillez entrer un prénom";
        return; 
    }else if (firstname.length > 99 ) {  // Vérifie les conditions pour le champ lastname
        event.preventDefault();
        responseDiv.textContent = "Votre prénom est trop long";
        return;
    }


    var email = document.getElementById("email").value;
    if (!(/@.*\./).test(email)) {    // Vérifie si l'adresse email contient un caractère "@"
        event.preventDefault();
        responseDiv.textContent = "Veuillez entrer une adresse email valide";
        return; 
    }else if (email.length > 99 ) {  // Vérifie les conditions pour le champ lastname
        event.preventDefault();
        responseDiv.textContent = "Votre email est trop long";
        return;
    }

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

    event.preventDefault(); // Empêche l'envoi du formulaire par défaut

    // Récupérer la valeur du nom d'utilisateur
    var username = document.getElementById("username").value;

    // Effectuer la vérification AJAX
    $.ajax({
        type: "POST",
        url: "/check-username", // URL de l'endpoint pour vérifier le nom d'utilisateur sur le serveur
        data: { username: username },
        dataType: 'json', // Indiquer que la réponse est au format JSON
        success: function(response) {
            console.log(response);
            if (response.usernameExists) {
                // Afficher un message d'erreur à l'utilisateur
                document.getElementById("error-message").style.display = "block";
                console.log("negatif")
            } else {
                // Le nom d'utilisateur n'existe pas, soumettre le formulaire
                document.getElementById("form").submit();
                console.log("positif", response.usernameExists)
            }
        },
        error: function(xhr, status, error) {
            // Gérer les erreurs de la requête AJAX
            console.error(error);
        }
    });
});
});