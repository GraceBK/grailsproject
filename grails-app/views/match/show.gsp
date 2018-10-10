<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'match.label', default: 'Match')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-match" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
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


                <div id="show-match" class="content scaffold-show" role="main">
                    <h1><g:message code="default.show.label" args="[entityName]" /></h1>

                    <div class="card" style="width: 30rem;">


                        <g:if test="${flash.message}">
                            <div class="message" role="status">${flash.message}</div>
                        </g:if>


                        <%--f:display bean="match" /--%>

                        <div class="card-body">
                            <g:each in="${match}">
                                <div class="card-title">
                                    Winner : <g:link>${it.winner.username}</g:link> <g:link controller="user" action="show" class="card-link btn btn-info" id="${it.winner.id}">${it.winner.username}</g:link>
                                </div>
                                <div class="card-title">
                                    Winner Score : ${it.winnerScore}
                                </div>
                                <div class="card-title">
                                    Looser : <g:link>${it.looser.username}</g:link> <g:link controller="user" action="show" class="card-link btn btn-info" id="${it.looser.id}">${it.looser.username}</g:link>
                                </div>
                                <div class="card-title">
                                    Looser Score : ${it.looserScore}
                                </div>
                            </g:each>
                        </div>

                        <div class="card-body text-center">

                            <g:form resource="${this.match}" method="DELETE">
                                <fieldset class="buttons">
                                    <g:link class="card-link btn btn-primary" action="edit" resource="${this.match}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                                    <input class="card-link btn btn-danger" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                                </fieldset>
                            </g:form>

                        </div>


                    </div>

                </div>

            </div>
        </div>
    </div>




    </body>
</html>
