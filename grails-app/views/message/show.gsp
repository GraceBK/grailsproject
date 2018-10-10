<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'message.label', default: 'Message')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-message" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
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



                <div id="show-message" class="content scaffold-show" role="main">
                    <h1><g:message code="default.show.label" args="[entityName]" /></h1>


                    <div class="card" style="width: 30rem;">

                        <g:if test="${flash.message}">
                            <div class="message" role="status">${flash.message}</div>
                        </g:if>
                        <%--f:display bean="message" /--%>

                        <div class="card-body">
                            <g:each in="${message}">
                                <div class="card-title">
                                    Author : <g:link>${it.author.username}</g:link> <g:link controller="user" action="show" class="card-link btn btn-info" id="${it.author.id}">${it.author.username}</g:link>
                                </div>
                                <div class="card-title">
                                    Target : <g:link>${it.target.username}</g:link> <g:link controller="user" action="show" class="card-link btn btn-info" id="${it.target.id}">${it.target.username}</g:link>
                                </div>
                                <p>Content : ${it.content}</p>
                                <p>Lu : ${it.lu}</p>
                            </g:each>
                        </div>

                        <div class="card-body text-center">
                            <g:form resource="${this.message}" method="DELETE">
                                <fieldset class="buttons">
                                    <g:link class="card-link btn btn-primary" action="edit" resource="${this.message}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
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
