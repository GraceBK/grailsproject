package fr.mbds.tp

import grails.gorm.transactions.Transactional

@Transactional
class ProjectService {

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

    def serviceMethod() {

    }
}
