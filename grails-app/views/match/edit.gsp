<%@ page import="fr.mbds.grails.User" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'match.label', default: 'Match')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#edit-match" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

    <div class="container" style="padding-top: 70px">
        <div id="h-100" role="main">
            <div class="row justify-content-center h-75 align-items-center">


                <!--div class="nav" role="navigation">
                    <ul>
                        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                    </ul>
                </div-->
                <div id="edit-match" class="content scaffold-edit" role="main">
                    <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
                    <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                    </g:if>
                    <g:hasErrors bean="${this.match}">
                    <div class="alert alert-danger" role="alert" style="width: 30rem">
                        <g:eachError bean="${this.match}" var="error">
                        <p <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></p>
                        </g:eachError>
                    </div>
                    </g:hasErrors>
                    <g:form resource="${this.match}" method="PUT" style="width: 30rem">
                        <g:hiddenField name="version" value="${this.match?.version}" />
                        <fieldset class="form">
                            <%--f:all bean="match"/--%>


                            <g:each in="${match}">
                                <div class="form-group">
                                    <label for="exampleInput1">Winner Score</label>
                                    <g:field type="number" name="winnerScore" class="form-control" id="exampleInput1" value="${match.winnerScore}" placeholder="Winner Score"/>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInput2">Looser Score</label>
                                    <g:field type="number" name="looserScore" class="form-control" id="exampleInput2" value="${match.looserScore}" placeholder="Looser Score"/>
                                </div>
                                <div class="form-group">
                                    <label for="winner">Winner</label>
                                    <g:select name="winner"
                                              from="${User.list()}"
                                              value="${match.winner.id}"
                                              optionKey="id"
                                              optionValue="username"
                                              class="custom-select my-1 mr-sm-2"/>
                                </div>
                                <div class="form-group">
                                    <label for="looser">Looser</label>
                                    <g:select name="looser"
                                              from="${User.list()}"
                                              value="${match.looser.id}"
                                              optionKey="id"
                                              optionValue="username"
                                              class="custom-select my-1 mr-sm-2"/>
                                </div>
                                <g:submitButton name="create" class="save btn btn-primary btn-block" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                            </g:each>


                        </fieldset>
                        <!--fieldset class="buttons">
                            <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                        </fieldset-->
                    </g:form>
                </div>
            </div>
        </div>
    </div>
    </body>
</html>
