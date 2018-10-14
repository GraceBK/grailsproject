package grailsproject

import fr.mbds.grails.Match
import fr.mbds.grails.Message
import fr.mbds.grails.Role
import fr.mbds.grails.User
import fr.mbds.grails.UserRole
import grails.converters.JSON

class BootStrap {

    def init = { servletContext ->

        JSON.registerObjectMarshaller(User) {
            def output = [:]
            output['id'] = it.id
            output['dateCreated'] = it.dateCreated
            output['passwordExpired'] = it.passwordExpired
            output['username'] = it.username
            output['accountLocked'] = it.accountLocked
            output['accountExpired'] = it.accountExpired
            output['enabled'] = it.enabled
            output['avatar'] = it.avatar

            output
        }

        if (Role.count == 0) {
            def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true, failOnError: true)
            def gamingRole = new Role(authority: 'ROLE_USER').save(flush: true, failOnError: true)

            print("-------------------"+adminRole + "\n")
            print("-------------------"+gamingRole + "\n")


            def adminUser = new User(username: "admin", password: "aaaa").save(flush: true, failOnError: true)
            def playerUser = new User(username: "player", password: "coucou").save(flush: true, failOnError: true)
            def playerTwoUser = new User(username: "player2", password: "coucou").save(flush: true, failOnError: true)

            UserRole.create(adminUser, adminRole, true)
            UserRole.create(playerUser, gamingRole, true)
            UserRole.create(playerTwoUser, gamingRole, true)

            new Match(winner: playerUser, looser: playerTwoUser, winnerScore: 10, looserScore: 1).save(flush: true, failOnError: true)
            new Match(winner: playerTwoUser, looser: playerUser, winnerScore: 20, looserScore: 15).save(flush: true, failOnError: true)
            new Match(winner: playerUser, looser: playerTwoUser, winnerScore: 10, looserScore: 9).save(flush: true, failOnError: true)

            new Message(author: playerUser, target: playerTwoUser, content: "nananère").save(flush: true, failOnError: true)
            new Message(author: playerTwoUser, target: playerUser, content: ":(").save(flush: true, failOnError: true)
        }

        /*JSON.registerObjectMarshaller(User) {
            return [
                    username: user.id
            ]
        }*/

    }
    def destroy = {
    }
}
