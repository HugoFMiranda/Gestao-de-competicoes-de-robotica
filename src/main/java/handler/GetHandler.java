package handler;

import controller.GetController;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

import java.sql.SQLException;

/**
 * It's a class that handles the GET requests
 */
public class GetHandler {

    public GetHandler() {

    }

    /**
     * It gets the competitions from the database and returns them in a JSON format
     *
     * @param rc RoutingContext
     */
    public void getCompeticoesJuri(RoutingContext rc) {
        try {
            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            GetController get = new GetController();
            response.end(Json.encodePrettily(get.getCompeticoesJuri()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getCompeticoesEquipa(RoutingContext rc) {
        try {
            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            GetController get = new GetController();
            response.end(Json.encodePrettily(get.getCompeticoesEquipa()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getCompeticoesOrganizador(RoutingContext rc) {
        try {
            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            GetController get = new GetController();
            response.end(Json.encodePrettily(get.getCompeticoesJuri()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * It gets the rounds of a competition
     *
     * @param rc RoutingContext
     */
    public void getRondasCompeticao(RoutingContext rc) {
        try {
            String id = rc.request().getParam("idCompeticao");
            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            GetController get = new GetController();
            response.end(Json.encodePrettily(get.getRondasCompeticoes(Integer.parseInt(id))));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * It gets the id of the competition from the request, creates a response, sets
     * the response status
     * code to 200, creates a new GetController object, and then sets the response
     * body to the result of
     * the getCompeticao function
     *
     * @param rc RoutingContext
     */
    public void getCompeticao(RoutingContext rc) {
        try {
            String id = rc.request().getParam("idCompeticao");
            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            GetController get = new GetController();
            response.end(Json.encodePrettily(get.getCompeticao(Integer.parseInt(id))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * It gets the id of the round from the request, creates a response, sets the
     * response header, sets
     * the status code, creates a new GetController object, and then returns the
     * response
     *
     * @param rc RoutingContext
     */
    public void getDadosRonda(RoutingContext rc) {
        try {
            String id = rc.request().getParam("idRonda");
            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            GetController get = new GetController();
            response.end(Json.encodePrettily(get.getDadosRonda(Integer.parseInt(id))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * It gets the name of the robot from the database
     *
     * @param rc RoutingContext
     */
    public void getNomeRobot(RoutingContext rc) {
        try {
            String id = rc.request().getParam("idRobot");
            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            GetController get = new GetController();
            response.end(Json.encodePrettily(get.getNomeRobot(Integer.parseInt(id))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * It gets the rankings of a competition by its id
     *
     * @param rc RoutingContext
     */
    public void getRankingsCompeticao(RoutingContext rc) {
        try {
            String id = rc.request().getParam("idCompeticao");
            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            GetController get = new GetController();
            response.end(Json.encodePrettily(get.getRankingsCompeticao(Integer.parseInt(id))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * It gets the rankings of a round of a competition
     *
     * @param rc RoutingContext
     */
    public void getRankingsRonda(RoutingContext rc) {
        try {
            String idCompeticao = rc.request().getParam("idCompeticao");
            String idRonda = rc.request().getParam("idRonda");
            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            GetController get = new GetController();
            response.end(Json.encodePrettily(get.getRankingsRonda(Integer.parseInt(idRonda))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * It gets the user id from the login handler and then uses it to get the juri
     * from the get
     * controller
     * 
     * @param rc RoutingContext
     */
    public void getJuri(RoutingContext rc) {

        try {
            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            LoginHandler lh = new LoginHandler();
            GetController get = new GetController();
            response.end(Json.encodePrettily(get.getJuri(lh.getUser().getId())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * It receives a request from the client, gets the id of the competition from
     * the request, calls
     * the getNomeCompeticao function from the GetController class, and returns the
     * name of the
     * competition to the client
     * 
     * @param rc RoutingContext
     */
    public void getNomeCompeticao(RoutingContext rc) {
        int id_competicao = Integer.parseInt(rc.request().getParam("idCompeticao"));

        try {
            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            GetController get = new GetController();
            response.end(Json.encodePrettily(get.getNomeCompeticao(id_competicao)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * It gets the OrganizadorEquipas from the database and returns it in a JSON
     * format
     * 
     * @param rc RoutingContext
     */
    public void getOrganizadorEquipas(RoutingContext rc) {
        try {
            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            GetController get = new GetController();
            response.end(Json.encodePrettily(get.getOrganizadorEquipas()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * It gets the id of the team from the url, then it creates a response, sets the
     * content type,
     * status code and then it calls the function getOrganizadorEquipa from the
     * GetController class,
     * which returns a JSON object, and then it ends the response with the JSON
     * object
     * 
     * @param rc RoutingContext
     */
    public void getOrganizadorEquipa(RoutingContext rc) {
        try {
            int id_equipa = Integer.parseInt(rc.request().getParam("idEquipa"));

            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            GetController get = new GetController();
            response.end(Json.encodePrettily(get.getOrganizadorEquipa(id_equipa)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * It receives a request from the client, calls a function from another class,
     * and returns the
     * result to the client
     * 
     * @param rc RoutingContext
     */
    public void getOrganizadorJuris(RoutingContext rc) {
        try {
            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            GetController get = new GetController();
            response.end(Json.encodePrettily(get.getOrganizadorJuris()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * It receives a request from the client, gets the id of the juri from the
     * request, creates a
     * response, sets the response's content type to JSON, sets the status code to
     * 200, creates a new
     * GetController object, and then sends the response to the client
     * 
     * @param rc RoutingContext
     */
    public void getOrganizadorJuri(RoutingContext rc) {
        try {
            int id_juri = Integer.parseInt(rc.request().getParam("idJuri"));

            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            GetController get = new GetController();
            response.end(Json.encodePrettily(get.getOrganizadorJuri(id_juri)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getRobos(RoutingContext rc) {
        try {
            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            GetController get = new GetController();
            response.end(Json.encodePrettily(get.getRobos()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getEquipa(RoutingContext rc) {
        try {
            LoginHandler lh = new LoginHandler();

            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            GetController get = new GetController();
            response.end(Json.encodePrettily(get.getEquipa(lh.getUser().getId())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
