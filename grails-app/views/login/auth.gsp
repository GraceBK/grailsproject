<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="login"/>
    <title></title>
</head>

<body>
<div class="container">
    <form method="POST" action="/base/j_spring_security_check" class="form-signin">
        <h2 class="form-signin-heading"><g:message code="grailsproject.login_form.header"/></h2>
        <g:if test="${error}"><div class="alert alert-danger" role="alert"><g:message code="grailsproject.login_form.login_error"/></div></g:if>
        <label for="j_username" class="sr-only"><g:message code="grailsproject.login_form.username"/></label>
        <input type="text" name="j_username" id="j_username" class="form-control" placeholder="<g:message code="grailsproject.login_form.username"/>" required autofocus>
        <label for="j_password" class="sr-only"><g:message code="grailsproject.login_form.password"/></label>
        <input type="password" id="j_password" name="j_password" class="form-control" placeholder="<g:message code="grailsproject.login_form.password"/>" required>
        <div class="checkbox">
            <label>
                <input type="checkbox" name="_spring_security_remember_me" value="remember-me"> <g:message code="grailsproject.login_form.remember_me"/>
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><g:message code="grailsproject.login_form.sign_in"/></button>
    </form>
</div>
</body>
</html>