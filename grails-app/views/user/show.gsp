<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div id="show-user" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <!--
            'Username', 'miniatures', 'matchLost', 'matchWon', 'passwordExpired', 'accountExpired', 'accountLocked', 'messageSent', 'messageReceived', 'enabled'
            -->
            <%--f:all bean="user" except="['matchWon', 'password']"/--%>
            <f:display bean="user" except="['password']"/>
            <g:form resource="${this.user}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="featuredImage" resource="${this.user}"><g:message code="default.button.edit.label" default="Edit Featured Image" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
