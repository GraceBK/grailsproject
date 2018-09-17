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

    User updateUser(params){
        User user = params.user;
        user.username = params.username;
        if (params.password && params.password.size()){
            user.password = params.password
        }

        if ((params.imageType == 'upload') && (params.miniatures && !params.miniatures.empty)){
            if (user.miniatures){
                imageService.deleteImage(user.miniatures)
                Miniatures new_miniature = user.miniatures
                user.miniatures = null
                new_miniature.delete()
            }
            Miniatures miniatures = imageService.createMiniature(picture: params.miniatures)
            user.miniatures = miniatures
        }

        if (params.imageType == 'empty'){
            if (user.miniatures){
                imageService.deleteImage(user.miniatures)
                Miniatures new_miniature = user.miniatures
                user.miniatures = null
                new_miniature.delete()
            }
        }
    }



    def serviceMethod() {

    }

    boolean myRoleIs(User user, String userRole) {
        if (user && userRole){
            Role role = Role.findByAuthority(userRole)
            if (role){
                Role admin = Role.findByAuthority('ROLE_ADMIN')
                Role roleUser = Role.findByAuthority('ROLE_USER')
                if (userRole == 'ROLE_ADMIN'){
                    def new_role = UserRole.findAllByUser(user)
                    if (new_role.role.contains(role)){
                        return true
                    }else {
                        return false
                    }
                }else if (userRole == 'ROLE_USER'){
                    def new_role = UserRole.findAllByUser(user)
                    if (new_role.role.contains(admin) || new_role.role.contains(roleUser) || new_role.role.contains(role)){
                        return true
                    }else {
                        return false
                    }
                }
            }
        }
    }
}
