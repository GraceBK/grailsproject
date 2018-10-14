<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
    <a href="#list-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
    <div id="list-user" class="container content scaffold-list" role="main" style="padding-top: 70px">
        <sec:ifAnyGranted roles="ROLE_ADMIN">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
        </sec:ifAnyGranted>

        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
    <%--f:table collection="${userList}" properties="['username', 'passwordExpired', 'accountExpired', 'accountLocked', 'enabled']"/--%>

        <div class="row justify-content-center">
    <sec:ifAnyGranted roles="ROLE_ADMIN">
            <table class="table table-borderless">
                <thead>
                <tr>
                    <%--g:sortableColumn property="avatar" title="Avatar"/--%>
                    <td></td>
                    <g:sortableColumn property="username" title="Trier par ordre Croissant/Decroissant"/>
                </tr>
                </thead>
                <tbody>
                <g:each in="${userList}">
                    <g:if test="${it.enabled == true}">
                        <tr>
                            <td width="200">
                                <g:if test="${it.avatar == null}">
                                    <g:img class="img-fluid" dir="avatar_profile" file="default_profile.jpg"/>
                                </g:if>
                                <g:if test="${it.avatar != null}">
                                    <img class="img-fluid" src="${it.avatar}">
                                </g:if>
                            </td>
                            <script>
                                $(function () {
                                    $(".content a").each(function () {
                                        $(this).attr("rel", "external")
                                    })
                                })
                            </script>
                            <th style="padding-left: 0;">
                                <div class="card-body" style="padding-left: 5px; padding-top: 5px">
                                    <h5 class="card-title">${it.username}</h5>

                                    <p class="card-text">${it.getAuthorities().authority}</p>
                                    <g:link controller="user" action="show" class="card-link btn btn-info" id="${it.id}">Details</g:link>
                                    <g:link controller="user" action="edit" class="card-link btn btn-primary" id="${it.id}">Edit</g:link>
                                    <g:link controller="user" action="justDeleteMe" class="card-link btn btn-danger" id="${it.id}">Delete</g:link>
                                    <!--a href="edit.gsp" rel="external">COUCOU</a-->
                                </div>
                            </th>
                        </tr>
                    </g:if>
                </g:each>
                </tbody>
            </table>
    </sec:ifAnyGranted>
    <sec:ifAnyGranted roles="ROLE_USER">
        coucou

        <div class="card" style="width: 30rem;">
            <g:each in="${user}">
                <g:if test="${it.avatar == null}">
                    <g:img class="card-img-top" dir="avatar_profile" file="default_profile.jpg"/>
                </g:if>
                <g:if test="${it.avatar != null}">
                    <img class="card-img-top" src="${it.avatar}" alt="${it.username}">
                </g:if>
                <div class="card-body">
                    <h5 class="card-title">Username : ${it.username}</h5>
                    <!--p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p-->
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">Password Expired : ${it.accountExpired}</li>
                    <li class="list-group-item">Account Locked : ${it.accountLocked}</li>
                    <li class="list-group-item">Account Expired : ${it.passwordExpired}</li>
                    <li class="list-group-item">Enabled : ${it.enabled}</li>
                </ul>
            </g:each>

            <div class="card-body">
                <g:form resource="${this.user}" method="DELETE">
                    <fieldset class="buttons">
                        <g:link class="card-link btn btn-primary" action="edit" resource="${this.user}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                        <input class="card-link btn btn-danger" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    </fieldset>
                </g:form>
            </div>
        </div>

    </sec:ifAnyGranted>
        </div>
        <div class="pagination justify-content-center">
            <g:paginate total="${userCount ?: 0}" />
        </div>
    </div>
</body>
</html>