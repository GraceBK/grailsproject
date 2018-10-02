

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'fr.mbds.grails.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'fr.mbds.grails.UserRole'
grails.plugin.springsecurity.authority.className = 'fr.mbds.grails.Role'
grails.plugin.springsecurity.requestMap.className = 'fr.mbds.grails.UserRole'
grails.plugin.springsecurity.securityConfigType = 'Annotation'
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

	[pattern: '/dbconsole/*',    access: ['ROLE_ADMIN']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

// DONE Correction de Bug lors que l'utilisateur se deconnecte
grails.plugin.springsecurity.logout.postOnly = false
// DONE Page HOME apres connexion
grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/user'