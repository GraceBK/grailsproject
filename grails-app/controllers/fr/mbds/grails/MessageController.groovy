package fr.mbds.grails

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class MessageController {

    MessageService messageService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond messageService.list(params), model:[messageCount: messageService.count()]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def show(Long id) {
        respond messageService.get(id)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def create() {
        respond new Message(params)
    }

    @Transactional
    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def save(Message message) {
        if (message == null) {
            notFound()
            return
        }

        try {
            messageService.save(message)
        } catch (ValidationException e) {
            respond message.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                //flash.message = (String)message(code: 'default.created.message', args: [message(code: 'match.label', default: 'Match'), match.id])
                redirect message
            }
            '*' { respond message, [status: CREATED] }
        }
    }

    @Transactional
    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def edit(Long id) {
        respond messageService.get(id)
    }

    @Transactional
    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def update(Message message) {
        if (message == null) {
            notFound()
            return
        }

        try {
            messageService.save(message)
        } catch (ValidationException e) {
            respond message.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'message.label', default: 'Message'), message.id])
                redirect message
            }
            '*'{ respond message, [status: OK] }
        }
    }

    @Transactional
    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        messageService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'message.label', default: 'Message'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'message.label', default: 'Message'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
