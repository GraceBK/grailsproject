package fr.mbds.tp

import groovy.io.FileType

import java.security.acl.Owner

class Canvas {

    FileType type
    String filename
    Date dateCreated

    static belongsTo = [user:User]

    static constraints = {
        type nullable: false
        filename blank: false
    }
}
