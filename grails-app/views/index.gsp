<!doctype html>
<html>
<head>
    <!-- https://www.oodlestechnologies.com/blogs/Using-Spring-security-taglibs-in-Grails -->
    <sec:ifNotLoggedIn>
        <meta name="layout" content="main"/>
    </sec:ifNotLoggedIn>
    <sec:ifLoggedIn>
        <sec:ifAnyGranted roles="ROLE_ADMIN">
            <meta name="layout" content="admin"/>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles="ROLE_USER">
            <meta name="layout" content="other"/>
        </sec:ifAnyGranted>
    </sec:ifLoggedIn>

    <title>Welcome to Grails</title>
</head>
<body>

        <div id="content" role="main">
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
                        <g:link controller="login" action="auth">Login</g:link>
                    </sec:ifNotLoggedIn>

                    <!--div id="controllers" role="navigation">
                        <h2>Available Controllers:</h2>
                        <ul>
                            <%--g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                                <li class="controller">
                                    <g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link>
                                </li>
                            </g:each--%>
                        </ul>
                    </div-->
                </section>
            </sec:ifNotLoggedIn>
        </div>

</body>
</html>
