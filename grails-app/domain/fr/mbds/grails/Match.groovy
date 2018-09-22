package fr.mbds.grails

class Match {

    User winner
    User looser
    int winnerScore
    int looserScore

    Date dateCreated

    static constraints = {
    }
}
