<!DOCTYPE html>
<html>
<<<<<<< HEAD
    <head>

        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

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


            <g:uploadForm controller="user" action="save" resource="${this.user}" method="POST">
                <f:all bean="user"/>
                <div id="div1" ondrop="drop(event)" ondragover="allowDrop(event)" style="height: 200px; width: 200px; border: 2px dashed red">Drag'n'drop an image here
                </div>
                <input type="file" id="file" name="avatarFile">
                    %{--<g:field type="file" name="avatarFile"/>--}%
                    <g:submitButton name="create" class="save"
                                    value="${message(code: 'default.button.create.label', default: 'Create')}"/>
                </fieldset>
            </g:uploadForm>
        </div>
    <script>
        function allowDrop(ev) {
            ev.preventDefault();
        }

        function drop(ev) {
            ev.preventDefault();
            ev.dataTransfer.files[0];
            document.getElementById('file').files = ev.dataTransfer.files;
            console.log(document.getElementById('file').files)

        }
    </script>

    </body>
=======
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <title><g:message code="default.create.label" args="[entityName]" /></title>
</head>
<body>
<a href="#create-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<!--div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div-->
<div id="create-user" class="container content scaffold-create" role="main" style="padding-top: 70px">
    <h1><g:message code="default.create.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.user}">
        <ul class="alert alert-danger" role="alert">
            <g:eachError bean="${this.user}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>

<%--g:uploadForm controller="user" action="save" resource="${this.user}" method="POST">
    <fieldset class="form-group">
        <f:all bean="user" except="['avatar', 'passwordExpired', 'accountLocked', 'accountExpired', 'enabled']"/>
    </fieldset>
    <fieldset class="form-group">
        <g:field type="file" name="avatarFile"/>
    </fieldset>
    <fieldset class="buttons">
        <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
    </fieldset>
</g:uploadForm--%>

    <g:uploadForm controller="user" action="save" resource="${this.user}" method="POST" style="width: 300px">
        <div class="form-group">
            <label for="exampleInputUsername">Username</label>
            <g:textField name="username" value="${username}" class="form-control" id="exampleInputUsername" placeholder="Username"/>
            <!--input type="text" value="\${fieldValue(bean: user, field: 'username')}" class="form-control" id="exampleInputUsername" placeholder="Username"-->
            <%--f:field bean="user" property="username" class="form-control" id="exampleInputUsername"/--%>
            <%--f:all bean="user" except="['avatar', 'passwordExpired', 'accountLocked', 'accountExpired', 'enabled']"/--%>
        </div>
        <div class="form-group">
            <label for="exampleInputPassword">Password</label>
            <g:passwordField name="password" value="${password}" class="form-control" id="exampleInputPassword" placeholder="Password"/>
            <!--input type="password" value="\${fieldValue(bean: user, field: 'password')}" class="form-control" id="exampleInputPassword" placeholder="Password"-->
            <%--f:field bean="user" property="password" class="form-control" id="exampleInputPassword"/--%>
        </div>
        <div class="form-group">
            <label for="exampleFormControlFile">File input</label>
            <!--input type="file" name="avatarFile" class="form-control-file" id="exampleFormControlFile"-->
            <g:field type="file" name="avatarFile" value="${avatar}"/>
        </div>
        <g:submitButton name="create" class="save btn btn-primary btn-block" value="${message(code: 'default.button.create.label', default: 'Create')}" />
    </g:uploadForm>
</div>
</body>
>>>>>>> 9945900f3aa0d005945a282dd8a7e6f9b82dcd56
</html>
