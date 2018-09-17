package fr.mbds.tp

class Picture {

    String picturename
    byte[] picturedata
    Date lastUpdated
    Date dateCreated
    Date uploadDate = new Date()

    static constraints = {
        picturename blank: false, nullable: true// TODO : nullable: false
        picturedata blank: true, nullable: true, maxSize: 1073741824
    }
}
