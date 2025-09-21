<#ftl encoding="utf-8">
    <head>
        <link rel="stylesheet" href="/css/style.css">
        <link rel="stylesheet" href="/css/header.css">
        <link rel="stylesheet" href="/css/appartement.css">
    </head>

    <body xmlns="http://www.w3.org/1999/html">
        <header>
            <div class="home">
                <a href="/accueil" class="home">Accueil</a>
                <a href="/ressources/immeubles">Immeubles</a>
                <a href="/ressources/syndicat">Syndicats</a>
                <a href="/ressources/appartements">Appartements</a>
            </div>
            <#if (role == 0)>
                <a href="/login" class="account">Connectez-vous</a>
            <#else>
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
            </#if>
        </header>

        <main>
    <div class="liste">
        <ul>
            <#list syndicat as syndic>
                <li><a href="syndicat/${syndic.idSyndicat}">${syndic.nom}</a> <p>Identifiant: ${syndic.idSyndicat}</p></li>
            </#list>
        </ul>
    </div>

        <#if (role == 2)>
        <div class="button_container">
            <a href="syndicat/create" class="button">Creer un syndicat</a>
            <br>
            <a href="syndicat/delete" class="button">Supprimer un syndicat</a>
            </div>
        </#if>

        </main>

    </body>

</html>