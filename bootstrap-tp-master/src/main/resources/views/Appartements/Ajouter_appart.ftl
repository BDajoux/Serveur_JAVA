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
                                <li><a href="/logout" class="account"> Deconnexion</a></li>
                            <ul>
                        </li>
                    </ul>
                </div>
        </header>

        <main>
            <form action="/ressources/appartements/create" method="post" id="form">
                <div class="important-information-block">
                    <label>Numero de l'appartement<input name="numeroAppartement" type="text" placeholder="ex: 4112"></label>   
                    <label>Etage de l'appartement<input name="etageAppartement" type="text" placeholder="ex: 41"></label>   
                    <label>Superficie de l'appartement (en m2)<input name="superficieAppartement" type="text" placeholder="ex: 30"></label>
                    <label>Identifiant de l'immeuble dans lequel est situé l'appartement<input name="idImmeuble" type="text" placeholder="ex: 00254123"></label>
                </div>
                <div id="output" class="output"></div>
                <button type="submit" id="submit-btn">Envoyer</button>
            </form>
        </main>

    </body>

</html>