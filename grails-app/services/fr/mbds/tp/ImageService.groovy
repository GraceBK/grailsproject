package fr.mbds.tp

import grails.gorm.transactions.Transactional
import groovy.io.FileType
import org.springframework.web.multipart.MultipartFile

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

@Transactional
class ImageService {

    def plateformeGrails

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


    def serviceMethod() {

    }
}
