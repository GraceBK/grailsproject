<%@ page import="fr.mbds.tp.Picture" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div id="create-user" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.user}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.user}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
<<<<<<< HEAD
            <g:form resource="${this.user}" method="POST">
                <fieldset class="form">
                    <f:all bean="user" except="featuredImageUrl"/>
                </fieldset>
=======
            <g:form resource="${this.user}" method="POST" action="save" controller="picture"><!-- controller="picture" -->
                <!--input type="file" name="file">
                <label for="username">Identifiant</label>
                <input type="text" name="username" id="username" placeholder="Identifiant">
                <label for="password">Mot de passe</label>
                <input type="password" name="password" id="password" placeholder="Identifiant"-->

                <%--f:all bean="user" except="['addMatch', 'password']"/--%>

                <%--f:all bean="user"/--%>

>>>>>>> 9d02c570024d6938360842d53f2c2f3a50a8df27
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
