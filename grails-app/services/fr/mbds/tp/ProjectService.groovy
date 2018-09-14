package fr.mbds.tp

import grails.gorm.transactions.Transactional

@Transactional
class ProjectService {

    def springSecurityService
    ImageService imageService

    def hasAUthorityOn(User userA, User userB){
        Role roleUserA = userA.getAuthorities()[0]
        Role roleUserB = userB.getAuthorities()[0]

        if (roleUserB.authority == 'ROLE_ADMIN'){
            if (roleUserA == roleUserB){
                return true
            }else {
                return false
            }
        }else if (roleUserB.authority == 'ROLE_USER'){
            if (roleUserA == roleUserB){
                return false
            }else {
                return true
            }
        }

    }

    User createUser(params){
        User user = new User(username: params.username, password: params.password)
        if ((params.imageType == 'upload') && (params.miniatures && !params.miniatures.empty)){
            Miniatures miniatures = imageService.createMiniature(picture: params.miniatures)
            user.miniatures = miniatures
        }
        user.save(failOnError: true)
        UserRole.create(user, params.role)
        return user
    }



    def serviceMethod() {

    }
}
