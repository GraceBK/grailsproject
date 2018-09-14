package fr.mbds.tp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class MiniaturesServiceSpec extends Specification {

    MiniaturesService miniaturesService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Miniatures(...).save(flush: true, failOnError: true)
        //new Miniatures(...).save(flush: true, failOnError: true)
        //Miniatures miniatures = new Miniatures(...).save(flush: true, failOnError: true)
        //new Miniatures(...).save(flush: true, failOnError: true)
        //new Miniatures(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //miniatures.id
    }

    void "test get"() {
        setupData()

        expect:
        miniaturesService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Miniatures> miniaturesList = miniaturesService.list(max: 2, offset: 2)

        then:
        miniaturesList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        miniaturesService.count() == 5
    }

    void "test delete"() {
        Long miniaturesId = setupData()

        expect:
        miniaturesService.count() == 5

        when:
        miniaturesService.delete(miniaturesId)
        sessionFactory.currentSession.flush()

        then:
        miniaturesService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Miniatures miniatures = new Miniatures()
        miniaturesService.save(miniatures)

        then:
        miniatures.id != null
    }
}
