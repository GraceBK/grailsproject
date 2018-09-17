<%@ page import="fr.mbds.tp.User" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="backend"/>
    <title></title>
</head>

<body>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <h1 class="page-header"><g:message code="grailsproject.users.list"/></h1>
    <g:include view="user/_users_list.gsp" model="[users:users]"/>
</div>

</body>
</html>