<!DOCTYPE html>
<html>
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
<div class="container" style="padding-top: 70px">
    <div id="h-100" role="main">
        <div class="row justify-content-center h-75 align-items-center">



            <div id="create-user" class="content scaffold-create" role="main">
                <h1><g:message code="default.create.label" args="[entityName]" /></h1>
                <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                </g:if>
                <g:hasErrors bean="${this.user}">
                    <div class="alert alert-danger" role="alert" style="width: 30rem">
                        <g:eachError bean="${this.user}" var="error">
                            <p <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></p>
                        </g:eachError>
                    </div>
                </g:hasErrors>
            <!-- TODO -->
                <%--g:uploadForm controller="user" action="save" resource="${this.user}" method="POST">
                    <f:all bean="user"/>
                    <div id="div1" ondrop="drop(event)" ondragover="allowDrop(event)" style="height: 200px; width: 200px; border: 2px dashed #007bff; background-size:50% auto; background-position: center; background-repeat: no-repeat; background-image: url('${resource(dir: "images/", file: "dragNdrop.svg")}');">
                        Drag'n'drop an image here
                    </div>

                    <input type="file" id="file" name="avatarFile">
                %{--<g:field type="file" name="avatarFile"/>--}%
                    <g:submitButton name="create" class="save"
                                    value="${message(code: 'default.button.create.label', default: 'Create')}"/>
                    </fieldset>
                </g:uploadForm--%>

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

                <g:uploadForm controller="user" action="save" resource="${this.user}" method="POST" style="width: 30rem">
                    <div class="form-group">
                        <label for="exampleInputUsername">Username</label>
                        <g:textField name="username" value="${username}" class="form-control" id="exampleInputUsername" placeholder="Username"/>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword">Password</label>
                        <g:passwordField name="password" value="${password}" class="form-control" id="exampleInputPassword" placeholder="Password"/>
                    </div>
                    <div class="form-group">
                        <!--label for="exampleFormControlFile">File input</label-->
                        <%--g:field type="file" name="avatarFile" value="${avatar}"/--%>
                        <div id="div1" ondrop="drop(event)" ondragover="allowDrop(event)" style="height: 200px; width: 30rem; border: 2px dashed #007bff; background-size:30% auto; background-position: center; background-repeat: no-repeat; background-image: url('${resource(dir: "images/", file: "dragNdrop.svg")}');">
                            Drag'n'drop an image here
                        </div>

                        <input type="file" id="file" name="avatarFile">
                    </div>
                    <g:submitButton name="create" class="save btn btn-primary btn-block" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </g:uploadForm>
            </div>

        </div>
    </div>
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
