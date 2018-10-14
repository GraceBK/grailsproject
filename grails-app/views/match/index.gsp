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
                    <g:sortableColumn property="id" title="Match N°"/>
                    <g:sortableColumn property="winner" title="Winner"/>
                    <g:sortableColumn property="winnerScore" title="Winner Score"/>
                    <g:sortableColumn property="looserScore" title="Looser Score"/>
                    <g:sortableColumn property="looser" title="Looser"/>
                </thead>

                <tbody>
                    <g:each in="${matchList}">
                        <tr>
                            <td>
                                <div class="card-body" style="padding-left: 5px; padding-top: 5px">
                                    <g:link controller="match" action="show" class="card-link btn btn-info" id="${it.id}">Details du match ${it.id}</g:link>
                                </div>
                            </td>
                            <td>
                                <g:if test="${it.winner.avatar == null}">
                                    <g:img class="img-thumbnail rounded-circle" width="60" dir="avatar_profile" file="default_profile.jpg"/>
                                </g:if>
                                <g:if test="${it.winner.avatar != null}">
                                    <img class="img-thumbnail rounded-circle" width="60" src="${it.winner.avatar}" alt="${it.winner.username}">
                                </g:if>
                                <g:link controller="user" action="show" id="${it.winner.id}">${it.winner.username}</g:link>
                            </td>
                            <td><h3>${it.winnerScore}</h3></td>
                            <td><h3>${it.looserScore}</h3></td>
                            <td>
                                <g:if test="${it.looser.avatar == null}">
                                    <g:img class="img-thumbnail rounded-circle" width="60" dir="avatar_profile" file="default_profile.jpg"/>
                                </g:if>
                                <g:if test="${it.looser.avatar != null}">
                                    <img class="img-thumbnail rounded-circle" width="60" src="${it.looser.avatar}" alt="${it.looser.username}">
                                </g:if>
                                <g:link controller="user" action="show" id="${it.looser.id}">${it.looser.username}</g:link>
                            </td>
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