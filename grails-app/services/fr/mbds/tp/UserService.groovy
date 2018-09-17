package fr.mbds.tp

import grails.gorm.services.Service

@SuppressWarnings(['LineLength', 'UnusedVariable', 'SpaceAfterOpeningBrace', 'SpaceBeforeClosingBrace'])
@Service(User)
interface UserService {

    User get(Serializable id)

    List<User> list(Map args)

    Long count()

    void delete(Serializable id)

    User save(User user)

    User updateFeaturedImageUrl(Serializable id, Long version, String featuredImageUrl)

    //User updateName(Serializable id, Long version, String name)
}