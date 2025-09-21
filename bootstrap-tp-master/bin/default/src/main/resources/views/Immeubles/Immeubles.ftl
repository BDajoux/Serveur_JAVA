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
                <#list immeuble as imm>
                    <li><a href="immeubles/${imm.idImmeuble}">${imm.nom}</a> <p>Identifiant: ${imm.idImmeuble}</p></li>
                </#list>
            </ul>
        </div>

            <#if (role == 2)>
                <div class="button_container">
                <a href="immeubles/create" class="button">Ajouter un immeuble</a>
                <br>
                <a href="immeubles/delete" class="button">Supprimer un immeuble</a>
                </div>
            </#if>
        </main>

    </body>

</html>