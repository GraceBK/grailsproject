package fr.mbds.grails


import grails.gorm.transactions.Transactional
import org.springframework.web.multipart.MultipartFile

// http://guides.grails.org/grails-upload-file/guide/index.html
// https://fr.slideshare.net/cavneb/upload-files-with-grails

@Transactional
class AvatarService {

    String uploadFile(MultipartFile file, String name, String destinationDirectory) {

        // Create storage path directory if it does not exist
        def storagePathDirectory = new File(destinationDirectory)
        if (!storagePathDirectory.exists()) {
            print "CREATING DIRECTORY ${destinationDirectory}: "
            if (storagePathDirectory.mkdirs()) {
                println "SUCCESS"
            } else {
                println "FAILED"
            }
        }

        // Store file
        if (!file.isEmpty()) {
            file.transferTo(new File("${destinationDirectory}/${name}"))
            println "Saved file: ${destinationDirectory}/${name}"
            return "${destinationDirectory}/${name}"
        } else {
            println "File: ${file.inspect()} was empty"
            return null
        }
    }
}
