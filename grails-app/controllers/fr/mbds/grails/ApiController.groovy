package fr.mbds.grails

import grails.converters.JSON
import org.hibernate.mapping.Map
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
                Long userId;
                try {
                    userId = Long.parseLong(params.id)

                }catch(Exception){
                    response.status = 400
                    render("Vérifiez les paramètres de votre requête, ils doivent être incorrects")
                }
                // On vérifie si l'id est nul, si oui
                //println(params)
                if (userId != null){
                    User user = User.get(userId)
                    // On vérifie si l'utilisateur existe, si oui
                    if (user) {
                        // L'afficher au format JSON
                        response.status = 200
                        render(user as JSON)
                    }else { //  sinon renvoyer la bonne erreur
                        response.status = 404
                        render("Cet utilisateur n'existe pas dans la base des Utilisateurs")
                    }
                }else{  // Sinon, on renvoie une erreur
                    response.status = 405
                }
                break
            case "POST":
                def user = new User(request.JSON as Map)
                if (user.save(flush: true)){
                    response.status = 201
                    render user as JSON
                }else {
                    render("Cet utilisateur existe deja dans la base, vous ne pouvez pas le rajouter")
                    response.status = 405
                }
                break
            case "DELETE":
                User user1 = User.get(params.id)
                if (user1.delete(flush: true)){
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
                    if (user.save(flush: true)){
                        response.status = 200
                        render("L'utilisateur " + user.id + "a été modifié avec succès")
                    }else{
                        response.status = 405
                        render("La modification de l'utilisateur est impossible")
                    }

                }else{
                    response.status = 404
                    render("Cet utilisateur n'existe pas dans la base, impossible de le mettre a jour")
                }
            default:
                response.status = 400
        }
    }


    def users(){
        switch (request.getMethod()){
            case "GET":
                if (params.containsKey("id")){
                    response.status = 400
                    render("Cette méthode n'est pas sensée utiliser des paramètres")
                }else{
                response.status = 200
                    render(User.list() as JSON)
                }
                break

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
                    response.status = 200
                    println("OK")
                    render(sms as JSON)
                    return null
                } else {
                    println("Not Found")
                    render(status: 404, text: "Not Found")
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
                    response.status = 201
                    println("Created")
                    render(sms as JSON)
                    return null
                } else {
                    response.status = 409
                    println("Conflict")
                    render("Conflict Can't save")
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
                    println("Bad Request")
                    render(status: 400, text: "Bad Request")
                    return null
                } else {
                    println("OK")
                    smsInstance.delete(flush: true)
                    render(status: 200, text: "OK")
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
//                println(" coucou "+smsInstance.properties)
                if (!smsInstance) {
                    println("Bad Request")
                    render(status: 400, text: "Bad Request")
                    return null
                } else {
                    smsInstance.properties = request.JSON
                    if (smsInstance.save(flush : true)) {
                        response.status = 200
                        println("OK")
                        render(smsInstance as JSON)
                        return null
                    } else {
                        println("Can't update")
                        render(status: 404, text: "Can't update")
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
        /**
         * HTTP Method    | POST
         * URI            | http://localhost:8081/tp/api/matchs
         * Operation      | Insert list of Match
         * Operation Type | Non-Idempotent
         */
            case "POST":
                request.JSON.each {
                    def messageInstance = new Message(
                            author: it.author,
                            target: it.target,
                            content: it.content,
                            lu: it.lu
                    )
                    if (messageInstance.save(flush : true)) {
                        println("${it} Created")
                    } else {
                        response.status = 405
                        render(status: 405, text: "Bad Request")
                        return null
                    }
                }
                render(status: 201, text: "Message Add")
                break
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
                    response.status = 200
                    println("OK")
                    render(matchInstance as JSON)
                    return null
                } else {
                    println("Not Found")
                    render(status: 404, text: "Not Found")
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
                    response.status = 201
                    println("Created")
                    render(matchInstance as JSON)
                    return null
                } else {
                    println("Conflict")
                    render(status: 409, text: "Can't save")
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
                    println("Bad Request")
                    render(status: 400, text: "Bad Request")
                    return null
                } else {
                    println("OK")
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
                    println("Bad Request")
                    render(status: 400, text: "Bad Request")
                    return null
                } else {
                    matchInstance.properties = request.JSON
                    if (matchInstance.save(flush : true)) {
                        response.status = 200
                        println("OK")
                        render(matchInstance as JSON)
                        return null
                    } else {
                        println("Can't update")
                        render(status: 404, text: "Can't update")
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
        /**
         * HTTP Method    | POST
         * URI            | http://localhost:8081/tp/api/matchs
         * Operation      | Insert list of Match
         * Operation Type | Non-Idempotent
         */
            case "POST":
                println("-----> "+ request.JSON)
                request.JSON.each {
                    def matchInstance = new Match(
                            winner: it.winner,
                            winnerScore: it.winnerScore,
                            looserScore: it.looserScore,
                            looser: it.looser

                    )
                    if (matchInstance.save(flush : true)) {
                        println("${it} Created")
                    } else {
                        response.status = 405
                        render(status: 405, text: "Bad Request")
                        return null
                    }
                }
                render(status: 201, text: "Matches Add")
                break
        }
    }
}
