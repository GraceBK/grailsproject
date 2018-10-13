

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'fr.mbds.grails.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'fr.mbds.grails.UserRole'
grails.plugin.springsecurity.authority.className = 'fr.mbds.grails.Role'
grails.plugin.springsecurity.requestMap.className = 'fr.mbds.grails.UserRole'
grails.plugin.springsecurity.securityConfigType = 'Annotation'

grails.plugin.springsecurity.rest.logout.endpointUrl = '/api/logout'

grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/**',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']],
	[pattern: '/login',          access: ['permitAll']],
	[pattern: '/login/**',       access: ['permitAll']],
	[pattern: '/logout',         access: ['permitAll']],
	[pattern: '/logout/**',      access: ['permitAll']],

	[pattern: '/dbconsole/*',    access: ['ROLE_ADMIN']],
//<<<<<<< HEAD
	[pattern: '/api/**', access: ['ROLE_ADMIN', 'ROLE_USER']],
	[pattern: '/api/logout', access: ['isAuthenticated()']]
//=======
		/*[pattern: '/api/**', access: ['ROLE_ADMIN', 'ROLE_USER']]
>>>>>>> 68225fc5841b9666a2778f91a22160ebeada121e*/
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS'],
	//Traditional, stateful chain
	[
			pattern: '/api/**', access: ['ROLE_ADMIN', 'ROLE_USER'],
			filters: 'JOINED_FILTERS, -anonymousAuthenticationFilter,-exceptionTranslationFilter,-authenticationProcessingFilter,' +
					'-securityContextPersistenceFilter,-rememberMeAuthenticationFilter'
	]
]

grails.plugin.springsecurity.rest.login.active = true
grails.plugin.springsecurity.rest.login.endpointUrl = '/api/login'
grails.plugin.springsecurity.rest.login.failureStatusCode = 401
grails.plugin.springsecurity.rest.login.useJsonCredentials = true
grails.plugin.springsecurity.rest.login.usernamePropertyName = 'username'
grails.plugin.springsecurity.rest.login.passwordPropertyName = 'password'
grails.plugin.springsecurity.rest.token.storage.useMemcached = false
grails.plugin.springsecurity.rest.token.storage.useGorm = false
grails.plugin.springsecurity.rest.token.storage.useGrailsCache = false
grails.plugin.springsecurity.rest.token.generation.jwt.algorithm = 'HS256'
grails.plugin.springsecurity.rest.token.storage.jwt.useSignedJwt = true
grails.plugin.springsecurity.rest.token.storage.jwt.secret = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImFkbWluIiwicGFzc3dvcmQiOiJhYWFhIn0.Gc8l1o5crDuDaQSQtgPROVzisxRjceOyharRoZ7uPac'

// DONE Correction de Bug lors que l'utilisateur se deconnecte
grails.plugin.springsecurity.logout.postOnly = false
// DONE Page HOME apres connexion
grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/user'