<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'match.label', default: 'Match')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-match" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-match" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <%--f:table collection="${matchList}" /--%>

            <table class="table table-hover">
                <thead>
                    <g:sortableColumn property="winner" title="Winner"/>
                    <g:sortableColumn property="winnerScore" title="Winner Score"/>
                    <g:sortableColumn property="looserScore" title="Looser Score"/>
                    <g:sortableColumn property="looser" title="Looser"/>
                </thead>

                <tbody>
                    <g:each in="${matchList}">
                        <tr>
                            <td><g:link controller="user" action="show" id="${it.winner.id}">${it.winner.username}</g:link></td>
                            <td><g:link controller="match" action="show" id="${it.id}">${it.winnerScore}</g:link></td>
                            <td><g:link controller="match" action="show" id="${it.id}">${it.looserScore}</g:link></td>
                            <td><g:link controller="user" action="show" id="${it.looser.id}">${it.looser.username}</g:link></td>
                        </tr>
                    </g:each>
                </tbody>
            </table>

            <div class="pagination">
                <g:paginate total="${matchCount ?: 0}" />
            </div>
        </div>
    </body>
</html>