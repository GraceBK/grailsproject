package fr.mbds.tp

import grails.plugin.springsecurity.SpringSecurityService

class LoginController {

    SpringSecurityService springSecurityService

    def auth(){
        User user = springSecurityService.getCurrentUser()
        if (user){
            redirect(controller:'user', action:'list')
        }else {
            render(view: '/login/auth', model: [error:params.login_error])
        }
    }

    def index() { }
}
