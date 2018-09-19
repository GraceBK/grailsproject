<!DOCTYPE html>
<html>
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
</html>
