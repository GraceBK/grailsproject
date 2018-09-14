package fr.mbds.tp

class HomeController {

    def springSecurityService

    def index() {
        for(def authority : springSecurityService.getPrincipal().getAuthorities()) {
            if(authority.getAuthority() == 'ROLE_ADMIN') {
                render(view: '/home/index')
                return
            } else {
                render(view: '/home/index2')
                return
            }
        }
        //return 'You are not an admin'
    }
}
