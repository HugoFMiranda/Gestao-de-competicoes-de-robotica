package handler;

import controller.GetController;
import controller.UpdateController;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import model.Robot;

/**
 * Its the handler for the update function
 */
public class UpdateHandler {

    // It's a constructor.
    public UpdateHandler() {

    }

    /**
     * It takes a JSON object from the request body, and updates the robot with the
     * given ID
     *
     * @param routingContext the routing context of the request
     */
    public void updateRobot(RoutingContext routingContext) {
        final String id = routingContext.request().getParam("id");
        JsonObject json = routingContext.getBodyAsJson();
        if (id == null || json == null) {
            routingContext.response().setStatusCode(400).end();
        } else {
            GetController upc = new GetController();
            Robot robot = null;
            try {
                robot = upc.getRobot(Integer.parseInt(id));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (robot == null) {
                routingContext.response().setStatusCode(404).end();
            } else {
                robot.setNome(json.getString("nomeRobot"));
                robot.setMac_adr(json.getString("macRobot"));
                routingContext.response()
                        .putHeader("content-type", "application/json; charset=utf-8")
                        .end(Json.encodePrettily(robot));
            }
        }

    }

    /**
     * It receives a request from the client, parses the parameters, calls the
     * function that updates
     * the database and returns the result to the client
     * 
     * @param rc RoutingContext
     */
    public void updateTempo(RoutingContext rc) {
        try {
            int id = Integer.parseInt(rc.request().getParam("idAssociacaoRobot"));
            int horas = Integer.parseInt(rc.request().getParam("horas"));
            int minutos = Integer.parseInt(rc.request().getParam("minutos"));
            int segundos = Integer.parseInt(rc.request().getParam("segundos"));
            int milissegundos = Integer.parseInt(rc.request().getParam("milissegundos"));


            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            UpdateController upd = new UpdateController();
            response.end(Json.encodePrettily(upd.novoTempo(id, horas, minutos, segundos, milissegundos)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * It receives a request from the client, gets the parameters from the request,
     * and then calls the
     * function "novaPontuacao" from the class "UpdateController" and returns the
     * result of that
     * function to the client
     * 
     * @param rc RoutingContext
     */
    public void updatePontos(RoutingContext rc) {
        try {
            int id = Integer.parseInt(rc.request().getParam("idAssociacaoRobot"));
            String classificacao = rc.request().getParam("classificar");
            int subtrairPontos = Integer.parseInt(rc.request().getParam("subtrairPontos"));

            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            UpdateController upd = new UpdateController();
            response.end(Json.encodePrettily(upd.novaPontuacao(id, classificacao, subtrairPontos)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   /**
    * It gets the parameters from the request, gets the user id from the session, and then calls the
    * update controller to update the user
    * 
    * @param rc RoutingContext
    */
    public void updateJuri(RoutingContext rc) {
        try{
            String nome = rc.request().getParam("nome");
            String email = rc.request().getParam("email");
            String pass = rc.request().getParam("pass");
            LoginHandler lh = new LoginHandler();
            int id_juri = lh.getUser().getId();

            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            UpdateController upd = new UpdateController();
            response.end(Json.encodePrettily(upd.editarJuri(id_juri, nome, email, pass)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * It receives a request from the client, parses the parameters, and calls the update controller to
     * update the database
     * 
     * @param rc RoutingContext
     */
    public void updateCompeticao(RoutingContext rc){
        try{
            int id_competicao = Integer.parseInt(rc.request().getParam("idCompeticao"));
            String nome = rc.request().getParam("nome");
            String descricao = rc.request().getParam("descricao");
            int nrondas = Integer.parseInt(rc.request().getParam("nrondas"));
            int nequipas = Integer.parseInt(rc.request().getParam("nequipas"));

            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            UpdateController upd = new UpdateController();
            response.end(Json.encodePrettily(upd.editarCompeticao(id_competicao, nome, descricao, nrondas, nequipas)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateEquipa(RoutingContext rc){
        try{
            LoginHandler lh = new LoginHandler();
            int id_equipa = lh.getUser().getId();
            String nome = rc.request().getParam("nome");

            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            UpdateController upd = new UpdateController();
            response.end(Json.encodePrettily(upd.editarEquipa(id_equipa, nome)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
