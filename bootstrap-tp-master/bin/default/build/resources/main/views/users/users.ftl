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
            <#if !connected>
                <a href="/login" class="account">Connectez-vous</a>
            <#else>
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
            </#if>
        </header>

        <main>
            <ul>
                <#list users as user>
                    <li>${user.id} - ${user.firstName} ${user.lastName} ${user.password} ${user.userName} ${user.email} ${user.role}</li>
                </#list>
            </ul>
        </main>

    </body>

</html>
