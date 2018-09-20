<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<a href="#list-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<!--div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div-->
<div id="list-user" class="container content scaffold-list" role="main" style="padding-top: 70px">
    <h1><g:message code="default.list.label" args="[entityName]" />

    <!--div class="btn-group">
                    <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Trier par
                    </button>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#">Action</a>
                        <a class="dropdown-item" href="#">Another action</a>
                        <a class="dropdown-item" href="#">Something else here</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#">Separated link</a>
                    </div>
                </div-->
    </h1>

    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
<%--f:table collection="${userList}" properties="['username', 'passwordExpired', 'accountExpired', 'accountLocked', 'enabled']"/--%>

    <div class="row justify-content-center">
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
                            <g:link controller="user" action="show" class="card-link btn btn-light" id="${it.id}">Details</g:link>
                            <a href="edit.gsp" rel="external">COUCOU</a>
                        </div>
                    </th>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>
    <div class="pagination justify-content-center">
        <g:paginate total="${userCount ?: 0}" />
    </div>

    <!--nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                    <!--li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1">Previous</a>
                    </li>
                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item">
                        <a class="page-link" href="#">Next</a>
                    </li>
                    <%--g:paginate total="${userCount ?: 0}" /--%>
                </ul>
            </nav-->
</div>
</body>
</html>