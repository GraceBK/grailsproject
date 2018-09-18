package fr.mbds.tp

import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import groovy.transform.CompileStatic
import org.springframework.web.multipart.MultipartFile

@SuppressWarnings('GrailsStatelessService')
@CompileStatic
class ImageService implements GrailsConfigurationAware{

    UserService userService

    String cdnFolder
    String cdnRootUrl

    def plateformeGrails
    ImageService imageService

    @Override
    void setConfiguration(Config co) {
        cdnFolder = co.getProperty('guides.cdnFolder')
        cdnRootUrl = co.getProperty('guides.cdnRootUrl')
    }

    @SuppressWarnings('JavaToPackageAccess')
    String uploadFeaturedImage(MultipartFile featuredImageFile) {

        def extention = 'jpg'//FilenameUtils.getExtension(featuredImageFile.originalFilename)
        String filename = UUID.randomUUID().toString() + '.' + extention
        File folder = new File(cdnFolder + '/' + filename)
        folder.createNewFile()



        featuredImageFile.transferTo(new File(cdnFolder + '/' + filename))

        filename
    }


//    @SuppressWarnings('JavaToPackageAccess')
//    String uploadFeaturedImage(MultipartFile featuredImageFile){
//        //def ext = FilenameUtils.getExtension(cmd.originalFilename)
//        String ext = 'jpg'
//        def file = File.createTempFile('img', '.' + ext, new File(cdnFolder))
//        file.createNewFile()
//
//        featuredImageFile.transferTo(new File(cdnFolder + '/' + file))
//        featuredImageFile
//        /*
//        String filename = cmd.featuredImageFile.originalFilename
//        String folderPath = "${cdnFolder}/user/${cmd.id}" ////// ATTENTIONNN
//        File folder = new File(folderPath)
//        if (!folder.exists()){
//            folder.mkdirs()
//        }
//        String path = "${folderPath}/${filename}"
//        cmd.featuredImageFile.transferTo(new File(path))
//
//        String featuredImageUrl = "${cdnRootUrl}//user/${cmd.id}/${filename}"
//        User user = userService.updateFeaturedImageUrl(cmd.id, cmd.version, featuredImageUrl)
//
//        if (!user || user.hasErrors()){
//            File f = new File(path)
//            f.delete()
//        }
//        user*/
//    }
    /*@SuppressWarnings('JavaToPackageAccess')
    User uploadFeaturedImage(FeaturedImageCommand cmd){
        String filename = cmd.featuredImageFile.originalFilename
        String folderPath = "${cdnFolder}/user/${cmd.id}" ////// ATTENTIONNN
        File folder = new File(folderPath)
        if (!folder.exists()){
            folder.mkdirs()
        }
        String path = "${folderPath}/${filename}"
        cmd.featuredImageFile.transferTo(new File(path))

        String featuredImageUrl = "${cdnRootUrl}//user/${cmd.id}/${filename}"
        User user = userService.updateFeaturedImageUrl(cmd.id, cmd.version, featuredImageUrl)

        if (!user || user.hasErrors()){
            File f = new File(path)
            f.delete()
        }
        user
    }*/

    /*
    boolean extensionValide(MultipartFile picture){
        String ext = picture.getContentType()
        if ((ext == 'image/png') || (ext == 'image/gif') || (ext == 'image/jpeg')){
            return true
        } else {
            return false
        }
    }

    def getImageDimension(MultipartFile picture){
        if(extensionValide(picture)){
            try{
                BufferedImage bufferedImage = ImageIO.read(picture.getInputStream())
                    def dimension = [width:buffuredImage.getWidth(), height:buffuredImage.getHeight()]
                    return dimension
            }catch(exception){

            }
        }else {
            return null
        }
    }

    Canvas createImage(params){
        def dimensions = getImageDimension(params.picture)
        if (dimensions != null){
            String ext = 'jpg'
            def file = File.createTempFile('img', '.' + ext, new File(plateformeGrails.config.grails.assetspath.images))
            params.picture.transferTo(file)
            def img = new Canvas(filename: file.getName(), type: params.fileType?params.fileType: FileType.IMG)
            if (params.save){
                img.save()
            }
            return img
        }else {
            return null
        }
    }

    Miniatures createMiniature(params){
        if (dimensions != null){
            String ext = 'jpg'
            def file = File.createTempFile('img', '.' + ext, new File(plateformeGrails.config.grails.assetspath.images))
            params.picture.transferTo(file)
            def img = new Miniatures(filename: file.getName(), user: params.user)
            if (params.save){
                img.save()
            }
            return img
        }else {
            return null
        }
    }

    def deleteImage(Miniatures miniatures){
        def file = new File(plateformeGrails.config.grails.assetspath.images + miniatures.filename)
        if (file && miniatures.filename){
            file.delete()
        }
    }

    def deleteImage(Canvas canvas){
        def file = new File(plateformeGrails.config.grails.assetspath.images + canvas.filename)
        if (file && miniatures.filename){
            file.delete()
        }
    }
*/

    def serviceMethod() {

    }
}
