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
                                <li><a href="/logout" class="account"> Deconnexion</a></li>
                            <ul>
                        </li>
                    </ul>
                </div>
            </#if>
        </header>

        <main>

            <a href="../appartements"> &lt;&nbsp;&nbsp;&nbsp;&nbsp;Retour à la liste des appartements</a>

            <p>Identifiant: ${appart.idAppartement} <br>Appartement numero: ${appart.numeroAppartement} <br>Etage: ${appart.etage}<br> Superficie: ${appart.superficie} m2</p>

            <p>Appartient à l'immeuble: <a href="../immeubles/${appart.idImmeuble}">${appart.idImmeuble}</a></p>

            <p>
                <#if prop.idPersonne?exists>
                    Propriétaire actuel: ${prop.nom} ${prop.prenom} ${prop.email} ${prop.telephone}
                    <#if role == 2>
                        <form action="/ressources/appartements/delete/proprietaire" method="post">
                            <button type="submit" class="button">Supprimer</button>
                            <input type="hidden" name="idAppartement" value="${appart.idAppartement}">
                            <input type="hidden" name="idProprietaire" value="${prop.idPersonne}">
                        </form>
                    </#if>
                <#else>
                    Aucun propriétaire actuellement
                    <#if role == 2>
                        <a href="create/proprietaire">Ajouter</a>
                    </#if>
                </#if>
            </p>

            <p>
                <#if loc.idPersonne?exists>
                    Locataire actuel: ${loc.nom} ${loc.prenom} ${loc.email} ${loc.telephone}
                    <#if role == 2>
                        <a href="" class="button">Supprimer</a>
                    </#if>
                <#else>
                    Aucun locataire actuellement
                    <#if role == 2>
                        <a href="">Ajouter</a>
                    </#if>
                </#if>
            </p>

        </main>

    </body>

</html>