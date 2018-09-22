<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <title><g:message code="default.edit.label" args="[entityName]" /></title>
</head>
<body>
<a href="#edit-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
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


                <div id="edit-user" class="content scaffold-edit" role="main">
                    <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
                    <g:if test="${flash.message}">
                        <div class="message" role="status">${flash.message}</div>
                    </g:if>
                    <g:hasErrors bean="${this.user}">
                        <div class="alert alert-danger" role="alert" style="width: 30rem;">
                            <g:eachError bean="${this.user}" var="error">
                                <p <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></p>
                            </g:eachError>
                        </div>
                    </g:hasErrors>


                    <g:uploadForm controller="user" action="update" resource="${this.user}" method="PUT" enctype="multipart/form-data" datatype="file" style="width: 30rem;">
                        <div class="form-group">
                            <label for="exampleInputUsername">Username</label>
                            <g:textField name="username" value="${user.username}" class="form-control" id="exampleInputUsername" placeholder="Username"/>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword">Password</label>
                            <g:passwordField name="password" value="" class="form-control" id="exampleInputPassword" placeholder="Password"/>
                        </div>
                        <!--div class="form-group form-check">
                            <label for="exampleInputPassword">Password</label>
                    <%--g:checkBox name="password" value="${password}" class="form-control" id="exampleInputPassword" placeholder="Password"/>
                </div>
                <div class="form-group form-check">
                    <label for="exampleInputPassword">Password</label>
                    <g:checkBox name="password" value="${password}" class="form-control" id="exampleInputPassword" /--%>
                        </div-->

                        <!--div class="form-group form-check">
                            <input type="checkbox" class="form-check-input" id="exampleCheck1">
                            <label class="form-check-label" for="exampleCheck1">Check me out</label>
                        </div>


                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
                            <label class="form-check-label" for="defaultCheck1">
                                Default checkbox
                            </label>
                        </div-->


                        <div class="form-group form-check">
                            <%--g:checkBox name="password" value="${user.enabled}" class="form-check-input" id="exampleCheck1"/>
                            <label class="form-check-label" for="exampleCheck1">Enabled</label>
                            <g:field name="password" type="checkbox" class="form-check-input" id="exampleCheck1" value="${avatar}"/--%>
                        </div>
                        <!--div class="form-group">
                            <label for="avatarFileA">File input</label>
                            <%--g:field type="file" id="avatarFileA" name="avatarFile" value=""/>

                            <!--input type="file" name="avatarFile" class="form-control-file" id="avatarFileA">
                            <g:field type="file" name="avatarFile" value="${avatar}"/--%>
                        </div-->
                        <g:actionSubmit name="update" class="btn btn-primary btn-block" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                    </g:uploadForm>


                    <g:uploadForm controller="user" action="updateAvatar" datatype="file">
                        <g:hiddenField name="id" value="${user.id}"/>
                        <div class="form-group" style="padding-top: 50px">
                            <label for="avatarFileA">File input</label>
                            <g:field type="file" id="avatarFileA" name="avatarFile" value=""/>

                            <!--input type="file" name="avatarFile" class="form-control-file" id="avatarFileA"-->
                            <%--g:field type="file" name="avatarFile" value="${avatar}"/--%>
                        </div>
                        <g:submitButton name="update" class="btn btn-primary btn-block" value="${message(code: 'grailsproject.button.update.label', default: 'Update')}" />
                    </g:uploadForm>

                </div>


            </div>
        </div>
    </div>







    <%--g:uploadForm resource="${this.user}" method="PUT" style="width: 300px">
        <%--g:hiddenField name="version" value="\${this.user?.version}" /--%>
        <!--fieldset class="form-group">
            <%--f:all bean="user" except="avatar"/>
        </fieldset-->
        <div class="form-group">
            <label for="exampleFormControlFile">File input</label>
            <!--input type="file" name="avatarFile" class="form-control-file" id="exampleFormControlFile"-->
            <%--g:field type="file" name="avatarFile" value="${avatar}"/>
        </div>
        <fieldset class="buttons">
            <input class="save btn btn-primary btn-block" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
        </fieldset>
    </g:uploadForm--%>
</div>
</body>
</html>
