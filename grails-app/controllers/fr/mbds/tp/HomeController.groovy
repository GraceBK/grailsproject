package fr.mbds.tp

class HomeController {

    def springSecurityService
    def userService

    def index() {
        params.max = Math.min(5 ?: 10, 100)

        for(def authority : springSecurityService.getPrincipal().getAuthorities()) {
            if(authority.getAuthority() == 'ROLE_ADMIN') {
                respond userService.list(params), model:[userCount: userService.count()]
                return
            } else {
                render(view: '/home/index2')
                return
            }
        }
        //return 'You are not an admin'
    }

    def create() {
        respond new User(params)
        //render("")
    }

    def show(Long id) {
        respond userService.get(id)
    }
}
