<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>
</head>
<body>
    <!--content tag="nav">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Application Status <span class="caret"></span></a>
            <ul class="dropdown-menu">
                <li><a href="#">Environment: ${grails.util.Environment.current.name}</a></li>
                <li><a href="#">App profile: ${grailsApplication.config.grails?.profile}</a></li>
                <li><a href="#">App version:
                    <g:meta name="info.app.version"/></a>
                </li>
                <li role="separator" class="divider"></li>
                <li><a href="#">Grails version:
                    <g:meta name="info.app.grailsVersion"/></a>
                </li>
                <li><a href="#">Groovy version: ${GroovySystem.getVersion()}</a></li>
                <li><a href="#">JVM version: ${System.getProperty('java.version')}</a></li>
                <li role="separator" class="divider"></li>
                <li><a href="#">Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Artefacts <span class="caret"></span></a>
            <ul class="dropdown-menu">
                <li><a href="#">Controllers: ${grailsApplication.controllerClasses.size()}</a></li>
                <li><a href="#">Domains: ${grailsApplication.domainClasses.size()}</a></li>
                <li><a href="#">Services: ${grailsApplication.serviceClasses.size()}</a></li>
                <li><a href="#">Tag Libraries: ${grailsApplication.tagLibClasses.size()}</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Installed Plugins <span class="caret"></span></a>
            <ul class="dropdown-menu">
                <g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
                    <li><a href="#">${plugin.name} - ${plugin.version}</a></li>
                </g:each>
            </ul>
        </li>
    </content-->

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

            <!--div id="controllers" role="navigation">
                <h2>Available Controllers:</h2>
                <ul>
                    <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                        <li class="controller">
                            <g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link>
                        </li>
                    </g:each>
                </ul>
            </div-->
            <sec:ifNotLoggedIn>
                <g:link controller="login" action="auth">
                    <div class="alert alert-link" role="alert">Login</div>
                </g:link>
            </sec:ifNotLoggedIn>
        </section>
    </sec:ifNotLoggedIn>

    <sec:ifLoggedIn>
        <h1 style="margin-top: 100px">AZERTY</h1>
        <h2><g:message code="grailsproject.home.about.me"/></h2>
        <h2><sec:username/></h2>
        <h2><f:display bean="user"/></h2>
        <img src="http://localhost:8888/22537642-bf51-4c4a-b852-2ffddc591c68.jpg"/>
        <sec:loggedInUserInfo field="username"/>

        <img src="${sec.loggedInUserInfo([field:'username'])}"/>


        <g:set var="photo"  value="${sec.loggedInUserInfo([field:'username'])}"/>
        <g:set var="userId"  value="${sec.loggedInUserInfo([field:'id'])}"/>

        <g:if test="${photo != null  && !(photo.empty)}" >
            <g:link controller="userAccount" action="myInfo" id="${userId}">
                <img id="profile_photo" src="${createLink(controller:'image', action:'profilePhoto', id:photo, params:[maxWidth:190.0,maxHeight:190.0])}" alt="${photo}" />
            </g:link>
        </g:if>
        <g:else>
            <g:link controller="userAccount" action="myInfo" id="${id}">
                <img id="profile_photo" src="${resource(dir:'images', file:'no_image_blue.gif')}" alt="No Image" width="200" height="200"/>
            </g:link>
        </g:else>


    </sec:ifLoggedIn>
    </div>

</body>
</html>
