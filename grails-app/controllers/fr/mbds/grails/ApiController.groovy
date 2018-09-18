package fr.mbds.grails

import grails.converters.JSON

class ApiController {

    def index() {
        //render "ok"
        switch (request.getMethod()){
            case "POST":
                render("POST\n")
                println(request.getHeader('Allow text/xml'))
                break
            case "GET":
                render("GET\n")
                break
        }

        render(User.get(2)) as JSON
    }

    def user(Long id){
        switch (request.getMethod()){
            case "GET":
                if (id != 0){
                    render(User.get(id)) as JSON
                }else{
                    render(User.list()) as JSON
                }
                break
            case "POST":
                User user = new User();
                user.username = request.getHeader(user.username)
                user.password = request.getHeader(user.password)
                break
        }
    }
}
