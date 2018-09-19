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

    /*
    * Pour les users, je dois créer une methode qui permet d'utiliser l'api
    * Avec un switch case, si je fasi un GET sans parametre par exemple, en retour j'ai la liste de tous les users
    * Si je donne un ID en parametre, en retour j'ai le user associé à cet ID
    *
    * Avec un POST, je fais soit un create, si le user n'existe pas, ou un update s'il existe
    * */
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
