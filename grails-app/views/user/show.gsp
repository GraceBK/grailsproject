<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
<a href="#show-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<!--div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div-->

    <div class="container" style="padding-top: 70px">
        <div id="h-100" role="main">
            <div class="row justify-content-center h-75 align-items-center">
                <div id="show-user" class="content scaffold-show" role="main">
                    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
                    <div class="card" style="width: 30rem;">
                        <g:each in="${user}">
                            <g:if test="${it.avatar == null}">
                                <g:img class="card-img-top" dir="avatar_profile" file="default_profile.jpg"/>
                            </g:if>
                            <g:if test="${it.avatar != null}">
                                <img class="card-img-top" src="${it.avatar}" alt="${it.username}">
                            </g:if>
                            <div class="card-body">
                                <h5 class="card-title">Username : ${it.username}</h5>
                                <!--p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p-->
                            </div>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">Password Expired : ${it.accountExpired}</li>
                                <li class="list-group-item">Account Locked : ${it.accountLocked}</li>
                                <li class="list-group-item">Account Expired : ${it.passwordExpired}</li>
                                <li class="list-group-item">Enabled : ${it.enabled}</li>
                            </ul>
                        </g:each>

                        <div class="card-body">
                            <g:form resource="${this.user}" method="DELETE">
                                <fieldset class="buttons">
                                    <g:link class="card-link btn btn-primary" action="edit" resource="${this.user}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                                    <input class="card-link btn btn-danger" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                                </fieldset>
                            </g:form>
                        </div>
                    </div>

                    <g:if test="${flash.message}">
                        <div class="message" role="status">${flash.message}</div>
                    </g:if>
                    <%--f:display bean="user" except="['password']"/--%>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
