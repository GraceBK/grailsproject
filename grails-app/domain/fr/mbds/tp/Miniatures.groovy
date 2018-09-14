package fr.mbds.tp

class Miniatures {

    String filename
    Date dateCreated

    static belongsTo = [user:User]

    static constraints = {
        filename blank: false
    }
}
