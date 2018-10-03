package fr.mbds.grails

import grails.converters.JSON
import org.springframework.validation.BindingResult

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
                if(user){
                    user.properties = request.JSON
                    userService.save(user)
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
     * Méthode qui permet de gerer les Requestes sur un Message
     * params message (Author, Target, Content, Lu)
     */
    def message() {
        switch (request.getMethod()) {
        /**
         * HTTP Method    | GET
         * URI            | http://localhost:8081/tp/api/message/1
         * Operation      | Get Message of Id 1
         * Operation Type | Read Only
         */
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
        /**
         * HTTP Method    | POST
         * URI            | http://localhost:8081/tp/api/message/(Optional 4)
         * Operation      | Insert Message with Id (Optional 4)
         * Operation Type | Non-Idempotent
         * ------------------------------------------------------------------
         *
         * Headers = JSON (application/json)
         *
         * Body =
         *
         {
            "target": {
                "id": 1
            },
            "lu": false,
            "content": "Coucou",
            "author": {
                "id": 2
            }
         }
         */
            case "POST":
                /*def jsonObject = request.JSON.target
                println requesr.JSON.target
                System.out.println("coucou "+jsonObject)*/
                def sms = new Message(request.JSON as Map)
                if (sms.save(flush : true)) {
//                    System.out.println(sms + " COUCOU")
                    render(sms as JSON)
                    return null
                } else {
                    System.out.println(sms)
                    render("Can't save")
                    return null
                }
                break
        /**
         * HTTP Method    | DELETE
         * URI            | http://localhost:8081/tp/api/message/1
         * Operation      | Delete Message with Id 1
         * Operation Type | Idempotent
         */
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
        /**
         * HTTP Method    | PUT
         * URI            | http://localhost:8081/tp/api/message/2
         * Operation      | Delete Message with Id 2
         * Operation Type | N/A
         * ------------------------------------------------------------------
         *
         * Headers = JSON (application/json)
         *
         * Body =
         *
         {
             "id": 2,
             "target": {
                "id": 1
             },
             "lu": true,
             "content": "Hello World",
             "author": {
                "id": 2
             }
         }
         */
            case "PUT":
                //def jsonObject = request.JSON.id
                println request.JSON
                def smsInstance = Message.get(request.JSON.id)
                println(" coucou "+smsInstance.properties)
                if (!smsInstance) {
                    render(status: 400, text: "400 Bad Request")
                    return null
                } else {
                    smsInstance.properties = request.JSON
                    if (smsInstance.save(flush : true)) {
                        render(smsInstance as JSON)
                        return null
                    } else {
                        render("Can't update")
                        return null
                    }
                }
                break
        }
    }


    /**
     * Méthode qui permet de gerer les Requestes sur l'ensemble des Messages
     * params message (Author, Target, Content, Lu)
     */
    def messages() {
        switch (request.getMethod()) {
        /**
         * HTTP Method    | GET
         * URI            | http://localhost:8081/tp/api/messages
         * Operation      | Get list of Message
         * Operation Type | Read Only
         */
            case "GET":
                render(Message.getAll() as JSON)
                break
            // TODO : delete all Message with lu = true
            /*case "DELETE":
                render(Message.deleteAll() as JSON)
                break*/
        }
    }


    /**
     * Méthode qui permet de gerer les Requestes sur un Match
     * params match (WinnerScore, LooserScore, Winner, Looser)
     * @param id
     * @return
     */
    def match() {
        switch (request.getMethod()) {
        /**
         * HTTP Method    | GET
         * URI            | http://localhost:8081/tp/api/match/1
         * Operation      | Get Match of Id 1
         * Operation Type | Read Only
         */
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
        /**
         * HTTP Method    | POST
         * URI            | http://localhost:8081/tp/api/match/(Optional 2)
         * Operation      | Insert Match with Id (Optional 2)
         * Operation Type | Non-Idempotent
         * ------------------------------------------------------------------
         *
         * Headers = JSON (application/json)
         *
         * Body =
         *
         {
             "winnerScore": 2,
             "looserScore": 1,
             "winner": {
                "id": 1
             },
             "looser": {
                "id": 2
             }
         }
         */
            case "POST":
                def matchInstance = new Match(request.JSON as Map)
                if (matchInstance.save(flush : true)) {
//                    System.out.println(matchInstance + " COUCOU")
                    render(matchInstance as JSON)
                    return null
                } else {
                    System.out.println(matchInstance)
                    render("Can't save")
                    return null
                }
                break
        /**
         * HTTP Method    | DELETE
         * URI            | http://localhost:8081/tp/api/match/1
         * Operation      | Delete Match with Id 1
         * Operation Type | Idempotent
         */
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
        /**
         * HTTP Method    | PUT
         * URI            | http://localhost:8081/tp/api/match/2
         * Operation      | Delete Match with Id 2
         * Operation Type | N/A
         * ------------------------------------------------------------------
         *
         * Headers = JSON (application/json)
         *
         * Body =
         *
         {
             "id": 2,
             "winnerScore": 2,
             "looserScore": 1,
             "winner": {
                "id": 1
             },
             "looser": {
                "id": 2
             }
         }
         */
            case "PUT":
                def matchInstance = Match.get(request.JSON.id)
                if (!matchInstance) {
                    render(status: 400, text: "400 Bad Request")
                    return null
                } else {
                    matchInstance.properties = request.JSON
                    if (matchInstance.save(flush : true)) {
                        render(matchInstance as JSON)
                        return null
                    } else {
                        render("Can't update")
                        return null
                    }
                }
                break
        }
    }

    /**
     * Méthode qui permet de gerer les Requestes sur l'ensemble des Matchs
     * params match (WinnerScore, LooserScore, Winner, Looser)
     */
    def matchs() {
        switch (request.getMethod()) {
        /**
         * HTTP Method    | GET
         * URI            | http://localhost:8081/tp/api/matchs
         * Operation      | Get list of Match
         * Operation Type | Read Only
         */
            case "GET":
                render(Match.getAll() as JSON)
                break
            /*case "DELETE":
                break*/
        }
    }
}
