<!doctype html>
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

    <ul class="nav justify-content-end">
        <li class="nav-item">
            <a class="home nav-link" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">Link</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">Link</a>
        </li>
        <li class="nav-item">
            <sec:ifLoggedIn>
                <g:link controller="logout">Logout</g:link>
            </sec:ifLoggedIn>
        </li>
    </ul>

    <sec:loggedInUserInfo field="username"/>

    <sec:ifLoggedIn>
        Vous etes <sec:username/>
    </sec:ifLoggedIn>

    <g:layoutBody/>

    <!--div class="footer" role="contentinfo"></div-->

    <div id="spinner" class="spinner" style="display:none;">
        <g:message code="spinner.alt" default="Loading&hellip;"/>
    </div>

    <asset:javascript src="application.js"/>

</body>
</html>
