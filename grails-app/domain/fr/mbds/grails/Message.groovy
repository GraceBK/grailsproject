package fr.mbds.grails

class Message {

    User author
    User target
    String content
    // TODO : remettre lu a FALSE
    Boolean lu = Boolean.FALSE


    static constraints = {
        author nullable: false
        target nullable: false
        content nullable: false
    }

}
