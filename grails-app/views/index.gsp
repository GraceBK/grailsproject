<!doctype html>
<html>
<head>
    <!-- https://www.oodlestechnologies.com/blogs/Using-Spring-security-taglibs-in-Grails -->
        <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <title>Welcome to Grails</title>
</head>
<body>

        <div class="container" id="content" role="main" style="margin-top:30px">
            <div class="row">
                <sec:ifNotLoggedIn>
                    <section class="row colset-2-its">
                        <h1>Welcome to Grails</h1>
                        <p>
                            Congratulations, you have successfully started your first Grails application! At the moment
                            this is the default page, feel free to modify it to either redirect to a controller or display
                            whatever content you may choose. Below is a list of controllers that are currently deployed in
                            this application, click on each to execute its default action:
                        </p>
                        <sec:ifNotLoggedIn>
                            <g:link controller="login" action="auth">
                                <div class="alert alert-primary" role="alert">Login</div>
                            </g:link>
                        </sec:ifNotLoggedIn>

                    </section>
                </sec:ifNotLoggedIn>

                <sec:ifLoggedIn>
                    <sec:ifLoggedIn>
                        <div class="container-fluid">
                            <div class="row">
                                <nav class="col-md-2 d-none d-md-block bg-light sidebar">
                                    <div class="sidebar-sticky">
                                        <ul class="nav-item">
                                            <li class="nav-link"><g:link class="nav-link" action="index" controller="user"><g:message code="default.list.label" args="[entityName]"  /></g:link></li>
                                        </ul>
                                        <ul class="nav-item">
                                            <li class="nav-link"><g:link class="nav-link" action="create" controller="user"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                                        </ul>
                                    </div>
                                </nav>
                                <main class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
                                    <div class="col-sm-4">
                                        <h2><g:message code="grailsproject.home.about.me"/></h2>
                                        <h5><sec:username/></h5>
                                        <div class="fakeimg" style="height: 200px;background: #aaa;">Fake Image</div>
                                        <hr class="d-sm-none">
                                    </div>
                                </main>
                            </div>
                        </div>
                    </sec:ifLoggedIn>
                </sec:ifLoggedIn>
            </div>
        </div>

</body>
</html>
