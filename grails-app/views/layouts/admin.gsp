<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />

    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>
</head>
<body>
    <div class="nav" role="navigation">
        <ul>
            <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>

            <li><g:link class="list" action="index" controller="user"><g:message code="default.list.label" args="[entityName]"  /></g:link></li>

            <li><g:link class="create" action="create" controller="user"><g:message code="default.new.label" args="[entityName]" /></g:link></li>

            <li><g:link controller='logout'>Logout</g:link></li>
        </ul>
    </div>

    <g:layoutBody/>

    <sec:ifLoggedIn>
        Vous etes <sec:username/>
    </sec:ifLoggedIn>
</body>
</html>