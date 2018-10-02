package fr.mbds.grails

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class MatchController {

    MatchService matchService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond matchService.list(params), model:[matchCount: matchService.count()]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def show(Long id) {
        respond matchService.get(id)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def create() {
        respond new Match(params)
    }

    @Transactional
    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def save(Match match) {
        if (match == null) {
            notFound()
            return
        }

        try {
            matchService.save(match)
        } catch (ValidationException e) {
            respond match.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'match.label', default: 'Match'), match.id])
                redirect match
            }
            '*' { respond match, [status: CREATED] }
        }
    }

    @Transactional
    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def edit(Long id) {
        respond matchService.get(id)
    }

    @Transactional
    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def update(Match match) {
        if (match == null) {
            notFound()
            return
        }

        try {
            matchService.save(match)
        } catch (ValidationException e) {
            respond match.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'match.label', default: 'Match'), match.id])
                redirect match
            }
            '*'{ respond match, [status: OK] }
        }
    }

    @Transactional
    @Secured(['ROLE_ADMIN'])
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        matchService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'match.label', default: 'Match'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'match.label', default: 'Match'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
