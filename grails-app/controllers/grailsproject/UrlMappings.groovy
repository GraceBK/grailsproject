package grailsproject

class UrlMappings {

    static mappings = {
        "/auth"(controller: "login", action: "auth")

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: "user", action: "list")

        "/"(view:"/index")
        "/index"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
