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

    <div class="w3-top" role="navigation">
        <div class="w3-row w3-padding w3-black">
            <sec:ifLoggedIn>
                <div class="w3-col s3">
                    <a class="w3-button w3-block w3-black" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
                </div>

                <sec:ifAnyGranted roles="ROLE_ADMIN">
                    <div class="w3-col s3"><g:link class="w3-button w3-block w3-black" action="index" controller="user"><g:message code="default.list.label" args="[entityName]"  /></g:link></div>
                    <div class="w3-col s3"><g:link class="w3-button w3-block w3-black" action="create" controller="user"><g:message code="default.new.label" args="[entityName]" /></g:link></div>
                </sec:ifAnyGranted>

                <div class="w3-col s3"><g:link class="w3-button w3-block w3-black" controller='logout'>Logout</g:link></div>
            </sec:ifLoggedIn>
        </div>
    </div>

<div style="padding-top: 2.5%">
    <g:layoutBody/>
</div>

<!--div class="footer" role="contentinfo"></div-->

<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>

<asset:javascript src="application.js"/>

</body>
</html>