package fr.mbds.tp

import grails.gorm.services.Service

@Service(Miniatures)
interface MiniaturesService {

    Miniatures get(Serializable id)

    List<Miniatures> list(Map args)

    Long count()

    void delete(Serializable id)

    Miniatures save(Miniatures miniatures)

}