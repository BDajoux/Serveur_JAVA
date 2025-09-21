<#ftl encoding="utf-8">
    <head>
        <link rel="stylesheet" href="/css/style.css">
        <link rel="stylesheet" href="/css/header.css">
    </head>

    <body xmlns="http://www.w3.org/1999/html">
        <header>
            <div class="home">
                <a href="/accueil" class="home">Accueil</a>
                <a href="/ressources/immeubles">Immeubles</a>
                <a href="/ressources/syndicat">Syndicats</a>
                <a href="/ressources/appartements">Appartements</a>
            </div>
        </header>

        <main>
            <p>Identifiant de compte: ${user.id}<br> Prénom: ${user.firstName} Nom: ${user.lastName} <br> Nom d'utilisateur: ${user.userName}<br> Adresse mail: ${user.email}</p>
        </main>

    </body>

</html>