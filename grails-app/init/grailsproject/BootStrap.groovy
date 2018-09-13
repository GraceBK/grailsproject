package grailsproject

import fr.mbds.tp.Match
import fr.mbds.tp.Message
import fr.mbds.tp.Role
import fr.mbds.tp.User
import fr.mbds.tp.UserRole

class BootStrap {

    def init = { servletContext ->

        if (Role.count == 0) {
            def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush:true, failOnError:true)
            def gamingRole = new Role(authority: 'ROLE_USER').save(flush:true, failOnError:true)

            def adminUser = new User(username: "admin", password: "azerty").save(flush:true, failOnError:true)
            def playerUser = new User(username: "player", password: "coucou").save(flush:true, failOnError:true)
            def playerTwoUser = new User(username: "player2", password: "coucou").save(flush:true, failOnError:true)

            UserRole.create(adminUser, adminRole, true)
            UserRole.create(playerUser, gamingRole, true)
            UserRole.create(playerTwoUser, gamingRole, true)

            new Match(winner: playerUser, looser: playerTwoUser, winnerScore: 10, looserScore: 1).save(flush:true, failOnError:true)

            new Message(author: playerUser, target: playerTwoUser, content: "nananère").save(flush:true, failOnError:true)
            new Message(author: playerTwoUser, target: playerUser, content: ":(").save(flush:true, failOnError:true)
        }
    }
    def destroy = {
    }
}
