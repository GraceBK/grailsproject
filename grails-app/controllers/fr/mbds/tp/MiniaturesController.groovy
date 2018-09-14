package fr.mbds.tp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class MiniaturesController {

    MiniaturesService miniaturesService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond miniaturesService.list(params), model:[miniaturesCount: miniaturesService.count()]
    }

    def show(Long id) {
        respond miniaturesService.get(id)
    }

    def create() {
        respond new Miniatures(params)
    }

    def save(Miniatures miniatures) {
        if (miniatures == null) {
            notFound()
            return
        }

        try {
            miniaturesService.save(miniatures)
        } catch (ValidationException e) {
            respond miniatures.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'miniatures.label', default: 'Miniatures'), miniatures.id])
                redirect miniatures
            }
            '*' { respond miniatures, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond miniaturesService.get(id)
    }

    def update(Miniatures miniatures) {
        if (miniatures == null) {
            notFound()
            return
        }

        try {
            miniaturesService.save(miniatures)
        } catch (ValidationException e) {
            respond miniatures.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'miniatures.label', default: 'Miniatures'), miniatures.id])
                redirect miniatures
            }
            '*'{ respond miniatures, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        miniaturesService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'miniatures.label', default: 'Miniatures'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'miniatures.label', default: 'Miniatures'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
