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

    <%--sec:ifNotLoggedIn>
        <asset:stylesheet src="application.css"/>
    </sec:ifNotLoggedIn>
    <sec:ifLoggedIn--%>
        <asset:stylesheet src="bootstrap_min.css"/>
    <%--/sec:ifLoggedIn--%>

    <g:layoutHead/>
</head>
<body>

<!--div class="navbar navbar-default navbar-static-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/#">
		    <asset:image src="grails.svg" alt="Grails Logo"/>
                </a>
            </div>
            <div class="navbar-collapse collapse" aria-expanded="false" style="height: 0.8px;">
                <ul class="nav navbar-nav navbar-right">
                    <g:pageProperty name="page.nav" />
                </ul>
            </div>
        </div>
    </div-->

<sec:ifLoggedIn>
    <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark" style="min-height: 80px">
        <a class="navbar-brand" href="${createLink(uri: '/')}">
            <img src="/docs/4.1/assets/brand/bootstrap-solid.svg" width="30" height="30" class="d-inline-block align-top" alt="">
            Project Grails
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNavDropdown">

            <ul class="navbar-nav mr-auto">
                <li class="nav-item"><a class="nav-link" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <sec:ifAnyGranted roles="ROLE_ADMIN">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuUser" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            User
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuUser">
                            <g:link class="dropdown-item" action="index" controller="user"><span class="glyphicon glyphicon-list" aria-hidden="true"></span> <g:message code="default.list.label" args="[entityName]" /></g:link>
                            <g:link class="dropdown-item" action="create" controller="user"><span class="glyphicon glyphicon-list" aria-hidden="true"></span> <g:message code="default.new.label" args="[entityName]" /></g:link>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuMatch" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Match
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuMatch">
                            <g:link class="dropdown-item" action="index" controller="user"><span class="glyphicon glyphicon-list" aria-hidden="true"></span> <g:message code="default.list.label" args="[entityName]" /></g:link>
                            <g:link class="dropdown-item" action="create" controller="user"><span class="glyphicon glyphicon-list" aria-hidden="true"></span> <g:message code="default.new.label" args="[entityName]" /></g:link>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuMessage" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Message
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuMessage">
                            <g:link class="dropdown-item" action="index" controller="user"><span class="glyphicon glyphicon-list" aria-hidden="true"></span> <g:message code="default.list.label" args="[entityName]" /></g:link>
                            <g:link class="dropdown-item" action="create" controller="user"><span class="glyphicon glyphicon-star" aria-hidden="true"></span> <g:message code="default.new.label" args="[entityName]" /></g:link>
                        </div>
                    </li>
                </sec:ifAnyGranted>
            </ul>
            <g:link class="btn btn-outline-success" controller='logout'>Logout</g:link>
        </div>
    </nav>
</sec:ifLoggedIn>

<g:layoutBody/>

<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<sec:ifNotLoggedIn>
    <asset:javascript src="application.js"/>
</sec:ifNotLoggedIn>
<sec:ifLoggedIn>
    <asset:javascript src="jquery-3.3.1.slim.min.js"/>
    <asset:javascript src="popper.min.js"/>
    <asset:javascript src="bootstrap_min.js"/>
</sec:ifLoggedIn>

</body>
</html>
