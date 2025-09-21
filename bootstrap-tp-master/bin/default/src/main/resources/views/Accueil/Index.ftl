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
            <#if role == 0>
                <a href="/login" class="account">Connectez-vous</a>
                
            <#else>
                <div class="menu">
                    <ul>
                        <li>
                            <a href="/account" class="account">Mon compte</a>
                            <ul>
                                <li><a href="/logout" class="account">Déconnexion</a></li>
                            <ul>
                        </li>
                    </ul>
                </div>
            </#if>
        </header>


        <main>
            <h1>ImmoMaster</h1>

            <p>Bienvenue sur ImmoMaster, le site qui vous permet de gerer les immeubles appartenant a la collectivité de Clermont-Ferrand</p>

            <p>Vous pouvez y retrouver la liste des immeubles, appartements et syndicats, avec le detail de chacun, ainsi que les noms et coordonnées des actuels propriétaires et locataires</p>

            <#if role == 2>
                <a href="/register">Creer un nouveau compte</a>
            </#if>
        </main>

    </body>

</html>