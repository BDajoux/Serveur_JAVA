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
            <#if (role == 0)>
                <a href="/login" class="account">Connectez-vous</a>
            <#else>
                <div class="menu">
                    <ul>
                        <li>
                            <a href="/account" class="account">Mon compte</a>
                            <ul>
                                <li><a href="/logout" class="account"> Deconnection</a></li>
                            <ul>
                        </li>
                    </ul>
                </div>
            </#if>
        </header>
        <main>
            <a href="../syndicat"> &lt;&nbsp;&nbsp;&nbsp;&nbsp;Retour à la liste des syndicats</a>

            <p>${syndicat.nom} <br> Identifiant: ${syndicat.idSyndicat} <br> Adresse: ${syndicat.adresse} </p>

            <p>Liste des immeubles rattachés à ce syndicat</p>
            <ul>
                <#list immeubles as imm>
                    <li> <a href="../immeubles/${imm.idImmeuble}">${imm.idImmeuble}</a></li>
                </#list>
            </ul>

        </main>

    </body>

</html>