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
                Long userId = request.getParameter("id")
                // On vérifie si l'id est nul, si oui
                if (userId != null){
                    User user = User.get(id);
                    // On vérifie si l'utilisateur existe, si oui
                    if (user) {
                        // L'afficher au format JSON
                        render(user as JSON);
                    }else { //  sinon renvoyer la bonne erreur
                        response.status = 400;
                    }
                }else{  // Sinon, on renvoie la liste de tous les utilisateurs
                    render(User.list() as JSON)
                }
                break
            case "POST":
                User user = new User();
                user.id = request.getParameter("id");
                user.username = request.getParameter("username");
                user.password = request.getParameter("password");

                break
            case "DELETE":
                break
            case "PUT":
                break
        }
    }
}
