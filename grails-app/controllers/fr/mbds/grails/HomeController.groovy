package fr.mbds.grails

class HomeController {

    def springSecurityService

    def index() {
        for (def authority : springSecurityService.getPrincipal().getAuthorities()) {
            if (authority.getAuthority() == 'ROLE_ADMIN') {
                render(view: '/user/index')
                return
            } else {
                render(view: '')
                return
            }
        }
    }
}
