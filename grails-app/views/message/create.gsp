<%@ page import="fr.mbds.grails.User" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'message.label', default: 'Message')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-message" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

    <div class="container" style="padding-top: 70px">
        <div id="h-100" role="main">
            <div class="row justify-content-center h-75 align-items-center">

                <!--div class="nav" role="navigation">
                    <ul>
                        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                    </ul>
                </div-->


                <div id="create-message" class="content scaffold-create" role="main">
                    <h1><g:message code="default.create.label" args="[entityName]" /></h1>
                    <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                    </g:if>
                    <g:hasErrors bean="${this.message}">
                    <div class="alert alert-danger" role="alert" style="width: 30rem">
                        <g:eachError bean="${this.message}" var="error">
                        <p <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></p>
                        </g:eachError>
                    </div>
                    </g:hasErrors>



                    <g:form resource="${this.message}" method="POST" style="width: 30rem">
                        <fieldset class="form">
                            <%--f:all bean="message"/--%>

                            <g:each in="${message}">
                                <div class="form-group">
                                    <label for="exampleInputContent">Content</label>
                                    <g:textField name="content" class="form-control" id="exampleInputContent" placeholder="Content"/>
                                </div>
                                <div class="form-group">
                                    <label for="author">Author</label>
                                    <g:select name="author"
                                              from="${User.list()}"
                                              value="${message.author}"
                                              optionKey="id"
                                              optionValue="username"
                                              class="custom-select my-1 mr-sm-2"/>
                                </div>
                                <div class="form-group">
                                    <label for="target">Target</label>
                                    <g:select name="target"
                                              from="${User.list()}"
                                              value="${message.target}"
                                              optionKey="id"
                                              optionValue="username"
                                              class="custom-select my-1 mr-sm-2"/>
                                </div>
                                <div class="form-group form-check">
                                    <input type="checkbox" class="form-check-input" id="exampleCheck1">
                                    <label class="form-check-label" for="exampleCheck1">${message(code: 'grailsproject.message.ack', default: 'Read')}</label>
                                </div>
                                <g:submitButton name="create" class="save btn btn-primary btn-block" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                            </g:each>


                        </fieldset>
                        <!--fieldset class="buttons">
                            <%--g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /--%>
                        </fieldset-->
                    </g:form>
                </div>


            </div>
        </div>
    </div>
    </body>
</html>
