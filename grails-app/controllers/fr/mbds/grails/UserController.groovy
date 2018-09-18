package fr.mbds.grails

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class UserController {

    UserService userService

    AvatarService avatarService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond userService.list(params), model:[userCount: userService.count()]
    }

    def show(Long id) {
        respond userService.get(id)
    }

    def create() {
        respond new User(params)
    }

    // controller action
    def displayImg = {
        /*def img // byte array
        //...
        response.setHeader('Content-length', img.length)
        response.contentType = 'image/png' // or the appropriate image content type
        response.outputStream << img
        response.outputStream.flush()*/

        def validKeyInstance = File.get(params.id)
        response.setContentType("image/jpg")
        response.outputStream << validKeyInstance.staffImg
        response.outputStream.flush()
    }

    def save(User user) {

        /*if (user.avatar == null) {
            notFound()
            return
        }*/

        String baseImage = UUID.randomUUID().toString()
        def downloadedFile = request.getFile("avatarFile")

        if (user == null) {
            notFound()
            return
        }

        String isUpload = avatarService.uploadFile(downloadedFile, "${baseImage}.jpg", grailsApplication.config.imagepathfile.filePath)

        try {
            if (isUpload) {
                user.avatar = grailsApplication.config.imagepathfile.fileUrl + "${baseImage}.jpg"
            }
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
        respond userService.get(id)
    }

    def update(User user) {
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
        if (id == null) {
            notFound()
            return
        }

        userService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
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
