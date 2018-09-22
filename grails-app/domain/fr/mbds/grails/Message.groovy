package fr.mbds.grails

class Message {

    User author
    User target
    String content
    Boolean read = Boolean.FALSE
    Date dateCreated


    static constraints = {
        content nullable: false
        author nullable: false
        target nullable: false
    }
}
