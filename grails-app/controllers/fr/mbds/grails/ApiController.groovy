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
    def user(){
        // On vérifie le VERBE
        switch (request.getMethod()){
            // Si c'est une requete GET
            case "GET":
                Long userId = Long.parseLong(params.id)
                // On vérifie si l'id est nul, si oui
                if (userId != null){
                    User user = User.get(userId)
                    // On vérifie si l'utilisateur existe, si oui
                    if (user) {
                        // L'afficher au format JSON
                        response.status = 200
                        render(user as JSON)
                    }else { //  sinon renvoyer la bonne erreur
                        response.status = 404
                    }
                }else{  // Sinon, on renvoie la liste de tous les utilisateurs
                    response.status = 405
                }
                break
            case "POST":
                String userJSON = JSON.parse(request.reader.text);
                User user = new User(userJSON.user);
                if (user.save(flush: true)){
                    response.status = 200
                    render user as JSON
                }else {
                    render("Cet utilisateur existe deja dans la base, vous ne pouvez pas le rajouter")

                }
//                String username = request.getParameter("username")
//                if (User.findAllByUsername(username)){
//                    render("Cet utilisateur existe deja dans la base, vous ne pouvez pas le rajouter")
//                }else {
//                    User user = new User()
//                    user.username = request.getParameter("username")
//                    user.password = request.getParameter("password")
//                    response.status = 200
//                    render user as JSON
//                }
                break
            case "DELETE":

                break
            case "PUT":
                println request.JSON
                String userJSON = JSON.parse(request.JSON.text);
                User user = new User(userJSON.user)
                if (user.save(flush: true)){
                    response.status = 200
                    render user as JSON
                }








                //////////////////////////////////////////////
                Long userId = Long.parseLong(request.getParameter("id"))
                String username = request.getParameter("username")
                String password = request.getParameter("password")
                User user1 = User.get(userId)
                if (user1){
                    user1.setUsername(username)
                    user1.setPassword(password)
                    response.status = 200
                    render user as JSON
                }else {
                    render("Cet utilisateur n'existe pas dans la base, mise à jour impossible")
                    response.status = 405

                }
                break
            default:
                response.status = 400
        }
    }
}
