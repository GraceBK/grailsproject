<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'message.label', default: 'Message')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-message" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-message" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <%--f:table collection="${messageList}" /--%>

            <table class="table table-hover">
                <thead>
                <g:sortableColumn property="author" title="Author"/>
                <g:sortableColumn property="target" title="Target"/>
                <g:sortableColumn property="content" title="Content"/>
                <g:sortableColumn property="lu" title="Lu"/>
                </thead>

                <tbody>
                <g:each in="${messageList}">
                    <tr>
                        <td><g:link controller="user" action="show" id="${it.author.id}">${it.author.username}</g:link></td>
                        <td><g:link controller="user" action="show" id="${it.target.id}">${it.target.username}</g:link></td>
                        <td><g:link controller="message" action="show" id="${it.id}">${it.content}</g:link></td>
                        <td><g:link controller="message" action="show" id="${it.id}">${it.lu}</g:link></td>
                    </tr>
                </g:each>
                </tbody>
            </table>

            <div class="pagination">
                <g:paginate total="${messageCount ?: 0}" />
            </div>
        </div>
    </body>
</html>