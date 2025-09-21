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
                                <li><a href="/logout" class="account">Deconnexion</a></li>
                            <ul>
                        </li>
                    </ul>
                </div>
            </#if>
        </header>

        <main>
        
            <a href="../immeubles"> &lt;&nbsp;&nbsp;&nbsp;&nbsp;Retour à la liste des immeubles</a>

            <p>${immeuble.nom} <br> Identifiant: ${immeuble.idImmeuble} <br>Adresse: ${immeuble.adresse}</p>

            <p>Gestion par le syndicat: <a href="../syndicat/${immeuble.syndicat}">${immeuble.syndicat}</a></p>

            <p>Liste des appartements de l'immeuble</p>
            <ul>
                <#list appartements as appart>
                    <li> <a href="../appartements/${appart.idAppartement}">${appart.idAppartement}</a></li>
                </#list>
            </ul>

        </main>

    </body>

</html>