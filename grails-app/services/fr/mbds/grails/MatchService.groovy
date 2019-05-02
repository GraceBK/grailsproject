package fr.mbds.grails

import grails.gorm.services.Service

@Service(Match)
interface MatchService {

    Match get(Serializable id)

    List<Match> list(PropertyValue.Map args)

    Long count()

    void delete(Serializable id)

    Match save(Match match)

}