package handler;

import controller.DeleteController;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

/**
 * It's a handler for the delete requests
 */
public class DeleteHandler {

    private final LoginHandler lh;

    // It's a constructor.
    public DeleteHandler() {
        this.lh = new LoginHandler();
    }

    /**
     * It deletes a juri from a competition
     * 
     * @param rc RoutingContext
     */
    public void deleteJuri(RoutingContext rc) {
        try {
            int id_competicao = Integer.parseInt(rc.request().getParam("idCompeticao"));
            int id_juri = lh.getUser().getId();

            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            DeleteController dec = new DeleteController();
            response.end(Json.encodePrettily(dec.deleteJuri(id_competicao, id_juri)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * It gets the id of the competition and the id of the team from the request,
     * and then it calls the
     * deleteEquipa function from the DeleteController class.
     * 
     * @param rc RoutingContext
     */
    public void deleteEquipa(RoutingContext rc) {
        try {
            int id_competicao = Integer.parseInt(rc.request().getParam("idCompeticao"));
            int id_equipa = lh.getUser().getId();

            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            DeleteController dec = new DeleteController();
            response.end(Json.encodePrettily(dec.deleteEquipa(id_competicao, id_equipa)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void deleteRobot(RoutingContext rc) {
        try {
            int id_robot = Integer.parseInt(rc.request().getParam("idRobot"));
            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            DeleteController dec = new DeleteController();
            response.end(Json.encodePrettily(dec.deleteRobot(id_robot)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCompeticao(RoutingContext rc) {
        try {
            int id_competicao = Integer.parseInt(rc.request().getParam("idCompeticao"));
            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            DeleteController dec = new DeleteController();
            response.end(Json.encodePrettily(dec.deleteCompeticao(id_competicao)));
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public void deleteMembro(RoutingContext rc) {
        try {
            int id_membro = Integer.parseInt(rc.request().getParam("idMembro"));
            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            DeleteController dec = new DeleteController();
            response.end(Json.encodePrettily(dec.deleteMembro(id_membro)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
