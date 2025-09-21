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
            <form action="/ressources/syndicat/create" method="post" id="form">
                <div class="important-information-block">
                    <label>Nom du syndicat<input name="nomSyndicat" type="text" placeholder="ex: Cegadim"></label>   
                    <label>Adresse du syndicat<input name="adresseSyndicat" type="text" placeholder="ex: 41 rue Corot"></label>   
                    <label>Referent du syndicat<input name="referentSyndicat" type="text" placeholder="ex: Dupont"></label>
                    <label>Telephone du syndicat<input name="telephoneSyndicat" type="text" placeholder="ex: 0652485259"></label>
                    <label>Email du syndicat<input name="emailSyndicat" type="text" placeholder="ex: email@gmail.com"></label>
                </div>
                <div id="output" class="output"></div>
                <button type="submit" id="submit-btn">Envoyer</button>
            </form>
        </main>

    </body>

</html>