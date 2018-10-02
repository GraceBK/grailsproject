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
                String username = request.getParameter("username")
                if (User.findAllByUsername(username)){
                    render("Cet utilisateur existe deja dans la base, vous ne pouvez pas le rajouter")
                }else {
                    //User user = new User()
                    user.username = request.getParameter("username")
                    user.password = request.getParameter("password")
                    response.status = 200
                    render user as JSON
                }
                break
            case "DELETE":
                User user1 = User.get(request.JSON.id)
                if (user1){
                    userService.delete(user1)
                    response.status = 200
                    render("L'utilisateur" + user1 + "a été supprimé de la base")
                }else {
                    response.status = 405
                    render("Cet utilisateur n'existe pas dans la base, impossible de le supprimer")
                }
                break
            case "PUT":
                println request.JSON
                User user = User.get(request.JSON.id)
<<<<<<< HEAD
                if(user){
                    user.properties = request.JSON
                    userService.save(user)
=======
                user.properties = request.JSON
                userService.save(user)

/*
               // String userJSON = JSON.parse(request.JSON.text);
                User user = new User(userJSON.user)
                if (user.save(flush: true)){
                    response.status = 200
                    render user as JSON
                }
*/







                //////////////////////////////////////////////
                Long userId = Long.parseLong(request.getParameter("id"))
                String username = request.getParameter("username")
                String password = request.getParameter("password")
                User user1 = User.get(userId)
                if (user1){
                    user1.setUsername(username)
                    user1.setPassword(password)
                    userService.save(user1)
>>>>>>> 7f8c211f812464915a68a83aa7f8c66bf1c46b3e
                    response.status = 200
                }else{
                    response.status = 405
                    render("Cet utilisateur n'existe pas dans la base, impossible de le mettre a jour")
                }
            default:
                response.status = 400
        }
    }


    /**
     * Méthode qui permet de gerer les Requestes sur un message
     * params message (author, target, content, lu)
     * @param id
     * @return
     */
    def message() {
        switch (request.getMethod()) {
            case "GET":
                def sms = Message.get(params.id)
                if (sms) {
                    render(sms as JSON)
                    return null
                } else {
                    render("Not Found")
                    return null
                }
                break
            case "POST":
                /*def jsonObject = request.JSON.target
                println requesr.JSON.target
                System.out.println("coucou "+jsonObject)*/
                def sms = new Message(request.JSON as Map)
                if (sms.save(flush : true)) {
                    System.out.println(sms + " COUCOU")
                    render(sms as JSON)
                    return null
                } else {
                    System.out.println(sms)
                    render("Cann't save")
                    return null
                }
                break
            case "DELETE":
                def smsInstance = Message.get(params.id)
                if (!smsInstance) {
                    render(status: 400, text: "400 Bad Request")
                    return null
                } else {
                    smsInstance.delete(flush: true)
                    render(status: 200, text: "200 OK")
                    return null
                }
                break
            case "PUT":
                // TODO Update message
                def jsonObject = request.JSON.id
                def smsInstance = Message.get(request.JSON.id)
                println(" coucou "+smsInstance + " - " + params.author)
                if (!smsInstance) {
                    render(status: 400, text: "400 Bad Request")
                    return null
                } else {
                    println(" ++++++ "+smsInstance)
                    //smsInstance.author = params.
                    if (smsInstance.save(flush : true)) {
                        render(smsInstance as JSON)
                        return null
                    } else {
                        render("Cann't update")
                        return null
                    }
                }
                break
        }
    }


    def messages() {
        switch (request.getMethod()) {
            case "GET":
                render(Message.getAll() as JSON)
                break
            /*case "DELETE":
                render(Message.deleteAll() as JSON)
                break*/
        }
    }



    def match() {
        switch (request.getMethod()) {
            case "GET":
                def matchInstance = Match.get(params.id)
                if (matchInstance) {
                    render(matchInstance as JSON)
                    return null
                } else {
                    render("Not Found")
                    return null
                }
                break
            case "POST":
                /*def jsonObject = request.JSON.target
                println requesr.JSON.target
                System.out.println("coucou "+jsonObject)*/
                def matchInstance = new Match(request.JSON as Map)
                if (matchInstance.save(flush : true)) {
                    System.out.println(matchInstance + " COUCOU")
                    render(matchInstance as JSON)
                    return null
                } else {
                    System.out.println(matchInstance)
                    render("Cann't save")
                    return null
                }
                break
            case "DELETE":
                def matchInstance = Match.get(params.id)
                if (!matchInstance) {
                    render(status: 400, text: "400 Bad Request")
                    return null
                } else {
                    matchInstance.delete(flush: true)
                    render(status: 200, text: "200 OK")
                    return null
                }
                break
            case "PUT":
                // TODO Update message
                def jsonObject = request.JSON.id
                def matchInstance = Match.get(request.JSON.id)
                println(" coucou "+matchInstance + " - " + params.author)
                if (!matchInstance) {
                    render(status: 400, text: "400 Bad Request")
                    return null
                } else {
                    println(" ++++++ "+matchInstance)
                    //matchInstance.author = params.
                    if (matchInstance.save(flush : true)) {
                        render(matchInstance as JSON)
                        return null
                    } else {
                        render("Cann't update")
                        return null
                    }
                }
                break
        }
    }

    def matchs() {
        switch (request.getMethod()) {
            case "GET":
                render(Match.getAll() as JSON)
                break
            case "DELETE":
                def matchInstance = Match.get(params.id)
                if (!matchInstance) {
                    render(status: 400, text: "400 Bad Request")
                    return null
                } else {
                    matchInstance.delete(flush: true)
                    render(status: 200, text: "200 OK")
                    return null
                }
                break
        }
    }
}
