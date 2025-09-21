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
                                <li><a href="/logout" class="account"> Deconnectez-vous</a></li>
                            <ul>
                        </li>
                    </ul>
                </div>
        </header>

        <main>
            <form action="/ressources/appartements/create/proprietaire" method="post" id="form">
                <div class="important-information-block">
                    <input type="hidden" name="selectProprioById" value="false">
                    <label>Nom<input name="nom" id="email" type="text" placeholder="ex: Province de France"></label>   
                    <label>Prenom<input name="Prenom" id="username" type="text" placeholder="ex: 2 rue de Clermont"></label>
                    <label>email<input name="idSyndicat" id="password" type="text" placeholder="ex: 00254123"></label>
                    <label>telephone<input name="idSyndicat" id="password" type="text" placeholder="ex: 00254123"></label>
                </div>
                <div id="output" class="output"></div>
                <button type="submit" id="submit-btn">Envoyer</button>
            </form>
        </main>

    </body>

</html>