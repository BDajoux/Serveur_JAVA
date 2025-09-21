<#ftl encoding="utf-8">
    <head>
        <link rel="stylesheet" href="/css/register.css">
        <link rel="stylesheet" href="/css/header.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    </head>

    <body xmlns="http://www.w3.org/1999/html">
        <header>
            <div class="home">
                <a href="/accueil" class="home">Accueil</a>
                <a href="/ressources/immeubles">Immeubles</a>
                <a href="/ressources/syndicat">Syndicats</a>
                <a href="/ressources/appartements">Appartements</a>
            </div>
            <div class="menu">
                    <ul>
                        <li>
                            <a href="/account" class="account">Mon compte</a>
                            <ul>
                                <li><a href="/logout" class="account">Deconnexion</a></li>
                            <ul>
                        </li>
                    </ul>
                </div>
        </header>

        <main>
            <form action="/ressources/immeubles/create" method="post" id="form">
                <div class="important-information-block">
                    <label>Nom de l'immeuble<input name="nomImmeuble" type="text" placeholder="ex: Province de France"></label>   
                    <label>Adresse de l'immeuble<input name="adresseImmeuble" type="text" placeholder="ex: 2 rue de Clermont"></label>
                    <label>Nombre d'appartement dans l'immeuble<input name="nbAppart" type="text" placeholder="ex: 30"></label>
                    <label>Identifiant du syndicat de l'immeuble<input name="idSyndicat" type="text" placeholder="ex: 00254123"></label>
                </div>
                <div id="output" class="output"></div>
                <button type="submit" id="submit-btn">Envoyer</button>
            </form>
        </main>

    </body>

</html>