<#ftl encoding="utf-8">
    <head>
        <link rel="stylesheet" href="../css/register.css">
        <link rel="stylesheet" href="../css/header.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="../js/register.js" defer></script>
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
            <form action="/register" method="post" id="form">
                <div class = "information-block">
                    <div class="information-subblock">
                        <label>Nom<input name="lastName" id="lastname" type="text" placeholder="ex: Dupond"></label>                 
                    </div>
                    <div class="information-subblock">
                        <label>Prénom<input name="firstName" id="firstname" type="text" placeholder="ex: Jean"></label>
                    </div>
                </div>
                <div class="important-information-block">
                    <label>Adresse email<input name="email" id="email" type="text" placeholder="ex: jean.dupond@gmail.com"></label>   
                    <label>Nom d'utilisateur<input name="userName" id="username" type="text" placeholder="ex: jeanDupond8"></label>
                    <label>Mot de passe<input name="password" id="password" type="password" placeholder="1 Maj., 1 Min., 1 Chiffre, 1 Caractère Spécial (*/!#.,;?)"></label>
                </div>
                <div id="error-message" style="color: red; display: none;">Le nom d'utilisateur existe déjà.</div>
                <div id="output" class="output"></div>
                <button type="submit" id="submit-btn">Envoyer</button>
            </form>
        </main>

    </body>

</html>
