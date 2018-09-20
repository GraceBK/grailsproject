<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="login"/>
    <title></title>
    <asset:stylesheet src="bootstrap_min.css"/>
</head>

<body>
    <div class="container h-100">
        <div class="row justify-content-center h-75 align-items-center">
            <div>
                <div class="card">
                    <div class="card-body">
                        <form method="POST" action="${postUrl ?:'/login/authenticate'}" class="form-signin" style="width: 300px">
                            <h2 class="form-signin-heading"><g:message code="grailsproject.login_form.header"/></h2>
                            <g:if test="${error}"><div class="alert alert-danger" role="alert"><g:message code="grailsproject.login_form.login_error"/></div></g:if>
                            <div class="form-group">
                                <label for="username" class="sr-only"><g:message code="grailsproject.login_form.username"/></label>
                                <input type="text" name="username" id="username" class="form-control" placeholder="<g:message code="grailsproject.login_form.username"/>" required autofocus>
                            </div>
                            <div class="form-group">
                                <label for="password" class="sr-only"><g:message code="grailsproject.login_form.password"/></label>
                                <input type="password" id="password" name="password" class="form-control" placeholder="<g:message code="grailsproject.login_form.password"/>" required>
                            </div>
                            <div class="form-check">
                                <label>
                                    <input type="checkbox" name="remember_me" value="remember-me" class="form-check-input"> <g:message code="grailsproject.login_form.remember_me"/>
                                </label>
                            </div>
                            <button class="btn btn-lg btn-primary btn-block" type="submit"><g:message code="grailsproject.login_form.sign_in"/></button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>