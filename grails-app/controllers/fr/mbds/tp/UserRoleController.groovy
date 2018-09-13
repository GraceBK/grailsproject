package fr.mbds.tp

import grails.plugin.springsecurity.SpringSecurityService
import grails.validation.ValidationException

import javax.jws.soap.SOAPBinding

import static org.springframework.http.HttpStatus.*

class UserRoleController {

    UserRoleService userRoleService
    SpringSecurityService springSecurityService
    ProjectService projectService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond userRoleService.list(params), model:[userRoleCount: userRoleService.count()]
    }

    def show(Long id) {
        respond userRoleService.get(id)
    }

    def create() {
        respond new UserRole(params)
    }

    def save(UserRole userRole) {
        if (userRole == null) {
            notFound()
            return
        }

        try {
            userRoleService.save(userRole)
        } catch (ValidationException e) {
            respond userRole.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userRole.label', default: 'UserRole'), userRole.id])
                redirect userRole
            }
            '*' { respond userRole, [status: CREATED] }
        }
    }

    def edit(Long id) {
        User me = springSecurityService.getCurrentUser()
        User user = User.get(id)

        if (user){
            if (projectService.hasAUthorityOn(me, user)){
                render(view: "/user/edit", model: [me:me, user:user])
            }else {
                response.sendError(403)
            }
        }
        //respond userRoleService.get(id)
    }

    def update(UserRole userRole) {
        if (userRole == null) {
            notFound()
            return
        }

        try {
            userRoleService.save(userRole)
        } catch (ValidationException e) {
            respond userRole.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'userRole.label', default: 'UserRole'), userRole.id])
                redirect userRole
            }
            '*'{ respond userRole, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        userRoleService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'userRole.label', default: 'UserRole'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userRole.label', default: 'UserRole'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
