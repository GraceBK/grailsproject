package grailsproject

class UrlMappings {

    static mappings = {
<<<<<<< HEAD
=======
        //"/auth"(controller: "Login", action: "auth")
>>>>>>> e4c3fd801d37a7b6bc52b9af2d553c802e3f678f

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "/index"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
