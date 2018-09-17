package fr.mbds.tp

import grails.plugin.springsecurity.SpringSecurityService
import grails.validation.ValidationException
import org.springframework.boot.SpringApplicationRunListener

import static org.springframework.http.HttpStatus.*

class UserController {

    UserService userService
    SpringSecurityService springSecurityService
    ProjectService projectService
    ImageService imageService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    def editFeaturedImage(Long id){
        User user = userService.get(id)
        if (!user){
            notFound()
            return
        }
        [user: user]
    }

    /*def uploadFeaturedImage(FeaturedImageCommand cmd){
        if (cmd.hasErrors()){
            respond(cmd, model: [user: cmd], view: 'editFeaturedImage')
            return
        }

        User user = imageService.uploadFeaturedImage(cmd)

        if (user == null){
            notFound()
            return
        }

        if (user.hasErrors()){
            respond(user, model: [user: user], view: 'editFeaturedImage')
            return
        }

        Locale locale = request.locale
        flash.message = crudMessageService.message(CRUD.UPDATE, domainName(locale), user.id, locale)
        redirect user
    }*/


    def list( ) {
        User me = springSecurityService.getCurrentUser()
        if( me ) {
            if (me.getAuthorities()[0].authority == 'ROLE_ADMIN') {
                def users = User.withCriteria {
                    order('username', 'asc')
                }
                render(view: '/user/list', model: [me: me, users: users])
            }
        }
//            else if( me.getAuthorities()[0].authority == 'ROLE_USER' ) {
////                Role adminRole = Role.findByAuthority('ROLE_ADMIN')
//                Role modRole = Role.findByAuthority('ROLE_USER')
//                def roles = UserRole.findAllByRole(modRole)
//render( view:'/user/list', model:[me:me, users:roles.user])
//            }
//        }
    }
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond userService.list(params), model:[userCount: userService.count()]
        log.info("coucou")
    }

    def show(Long id) {
        respond userService.get(id)
    }

    def create() {
        respond new User(params)
    }

    def save(User user) {
        if (user == null) {
            notFound()
            return
        }

        String uploadFeaturedImageUrl = imageService.uploadFeaturedImage(params.featuredImageFile)
        user.featuredImageUrl = uploadFeaturedImageUrl;

        try {
            userService.save(user)
        } catch (ValidationException e) {
            respond user.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*' { respond user, [status: CREATED] }
        }
    }

    def edit(Long id) {
        User it = springSecurityService.getCurrentUser()
        User user1 = User.get(id)
        if(it){
            if(projectService.hasAUthorityOn(it, user1)){
                render(view: "/user/edit", model: [me:it, user:user1])
            }else {
                response.sendError(403)
            }
        }else {
            response.sendError(404)
        }
        //respond userService.get(id)
    }

    def update(User user) {
        //User it = springSecurityService.getCurrentUser()
        //User user1 = user
        if (user == null) {
            notFound()
            return
        }

        try {
            userService.save(user)
        } catch (ValidationException e) {
            respond user.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*'{ respond user, [status: OK] }
        }
    }

    def delete(Long id) {
        User it = springSecurityService.getCurrentUser()
        User user1 = User.get(id)

        if (it){
            if(projectService.myRoleIs(it, 'ROLE_ADMIN')){
                user1.matchLost.collect().each {Match match ->
                    user1.removeFromMatchLost(match)
                    user1.save()
                }

                user1.matchWon.collect().each {Match match ->
                    user1.removeFromMatchWon(match)
                    user1.save()
                }

                user1.messageReceived.collect().each {Message message ->
                    user1.removeFromMessageReceived(message)
                    user1.save()
                }

                user1.messageSent.collect().each {Message message ->
                    user1.removeFromMatchLost(message)
                    user1.save()
                }

                user1.miniatures = null;
                user1.save()
                user1.delete()
                redirect(controller:"user", action: "list")
            }else{
                response.sendError(403)
            }
        }else {
            response.sendError(404)
        }

//        if (id == null) {
//            notFound()
//            return
//        }
//
//        userService.delete(id)
//
//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
//                redirect action:"index", method:"GET"
//            }
//            '*'{ render status: NO_CONTENT }
//        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
