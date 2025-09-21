<#ftl encoding="utf-8">
    <head>
        <link rel="stylesheet" href="/css/login.css">
        <link rel="stylesheet" href="/css/register.css">
        <link rel="stylesheet" href="/css/header.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="/js/login.js" defer></script>
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
            <div class="form-box">
            <form id="loginform" action="/login" method="post">
                <label for="userName">Nom d'utilisateur</label>
                <input name="userName" id="username" type="text"><br>

                <label for="password">Mot de passe</label>
                <input name="password" id="password" type="password"><br>

                <div id='output'></div>
                <div id='error-message'>Le nom d'utilisateur ou le mot de passe est incorrect</div>

                <button type="submit">Se connecter</button>
            </form>
            </div>

            <div class="transi_box"></div>  

        </main>

    </body>

</html>