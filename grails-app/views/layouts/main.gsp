<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />

    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>


    <style>
        body, html {
            height: 100%;
            font-family: "Inconsolata", sans-serif;
        }
    </style>
</head>
<body style="background-color: #FFFFFF;">
<sec:ifLoggedIn>
        <nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
            <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
            <div class="collapse navbar-collapse" id="navbarsExample09">
                <ul class="navbar-nav mr-auto">
                    <sec:ifAnyGranted roles="ROLE_ADMIN">
                        <li class="nav-item"><g:link class="nav-link" action="index" controller="user"><g:message code="default.list.label" args="[entityName]"  /></g:link></li>
                        <li class="nav-item"><g:link class="nav-link" action="create" controller="user"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                    </sec:ifAnyGranted>
                </ul>
            </div>
            <ul class="navbar-nav px-3">
                <li class="nav-link">
                    <g:link class="nav-link" controller='logout'>Logout</g:link>
                </li>
            </ul>
        </nav>
</sec:ifLoggedIn>

    <g:layoutBody/>

<!--div class="footer" role="contentinfo"></div-->

<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>

<asset:javascript src="application.js"/>

</body>
</html>