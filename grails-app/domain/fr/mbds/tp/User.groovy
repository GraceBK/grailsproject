package fr.mbds.tp

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

    private static final long serialVersionUID = 1
    transient springSecurityService

    String username
    String password
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
    Date dateCreated
    String featuredImageUrl
    Miniatures miniatures
    String avatar

    static transients = ['springSecurityService']

    static hasMany = [                      // Pour créer une liste des matchs gagnés et des matchs perdus
                                            matchWon:Match,
                                            matchLost: Match,
                                            messageSent: Message,
                                            messageReceived: Message];

    static mappedBy = [                     // On va ensuite faire un binding pour garder la trace
                                            matchWon: "winner",
                                            matchLost: "looser",
                                            messageSent: "author",
                                            messageReceived: "target"];


    Set<Role> getAuthorities() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }

    static constraints = {
        password nullable: false, blank: false, password: true
        username nullable: false, blank: false, unique: true
        miniatures nullable: true
<<<<<<< HEAD
        featuredImageUrl nullable: true
=======
        avatar nullable: true
>>>>>>> 9d02c570024d6938360842d53f2c2f3a50a8df27
    }

    static mapping = {
	    password column: '`password`'
        miniatures cascade: 'all-delete-orphan'
    }

    /*protected void crypterMDP() {
        password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
    }*/
}
