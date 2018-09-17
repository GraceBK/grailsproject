<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <%--sec:ifAnyGranted roles="ROLE_ADMIN">
            <meta name="layout" content="admin" />
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles="ROLE_USER">
            <meta name="layout" content="other" />
        </sec:ifAnyGranted--%>
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <sec:ifAnyGranted roles="ROLE_ADMIN">
            <div id="list-user" class="content scaffold-list" role="main">
                <h1><g:message code="default.list.label" args="[entityName]" /></h1>
                <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                </g:if>
                <f:table collection="${userList}" properties="['Username', 'miniatures', 'matchLost', 'matchWon', 'passwordExpired', 'accountExpired']" />

                <div class="pagination">
                    <g:paginate total="${userCount ?: 0}" />
                </div>
            </div>
        </sec:ifAnyGranted>
    </body>
</html>