package fr.mbds.grails

class Message {

    User author
    User target
    String content
    Boolean lu = Boolean.TRUE


    static constraints = {
        author nullable: false
        target nullable: false
        content nullable: false
    }

}
