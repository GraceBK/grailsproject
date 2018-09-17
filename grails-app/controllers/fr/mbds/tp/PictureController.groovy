package fr.mbds.tp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class PictureController {

    PictureService pictureService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond pictureService.list(params), model:[pictureCount: pictureService.count()]
    }

    def upload() {

    }

    def show(Long id) {
        respond pictureService.get(id)
    }

    def create() {
        respond new Picture(params)
    }

    def save = {
        def userInstance = new User(params)
        if (userInstance.save(flush: true)) {
            // Save avatar if uploaded
            def avatarImage = request.getFile('avatar')
            if (!avatarImage.isEmpty()) {
                userInstance.avatar = PictureService.uploadFile(avatarImage, "${userInstance.id}.png", "avatarImages")
            }
            redirect(action: "show", id: userInstance.id)
        } else {
            render(view: "create", model: [userInstance: userInstance])
        }
    }

    def edit(Long id) {
        respond pictureService.get(id)
    }

    def update(Picture picture) {
        if (picture == null) {
            notFound()
            return
        }

        try {
            pictureService.save(picture)
        } catch (ValidationException e) {
            respond picture.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'picture.label', default: 'Picture'), picture.id])
                redirect picture
            }
            '*'{ respond picture, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        pictureService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'picture.label', default: 'Picture'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'picture.label', default: 'Picture'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
