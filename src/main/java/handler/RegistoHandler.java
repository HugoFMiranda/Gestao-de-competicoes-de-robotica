package handler;

import controller.RegistoController;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

/**
 * It's a handler for the registration page
 */
public class RegistoHandler {

    // It's a private variable that is used to store the user that is logged in.
    private final LoginHandler lh;

    // It's a constructor.
    public RegistoHandler() {
        this.lh = new LoginHandler();
    }

    /**
     * It receives a request from the client, gets the parameters from the request,
     * creates a new
     * instance of the RegistoController class, calls the registarEquipa function
     * from the
     * RegistoController class, and sends the response back to the client
     * 
     * @param rc RoutingContext
     */
    public void addEquipa(RoutingContext rc) {
        String name = rc.request().getParam("nomeEquipa");
        String password = rc.request().getParam("passwordEquipa");
        // TODO adicionar elementos na pagina de registo
        // NOTE array? insere 1 a 1? num sei :(
        try {
            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            RegistoController rec = new RegistoController();
            response.end(Json.encodePrettily(rec.registarEquipa(name, password)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * It receives a request from the client, gets the parameters from the request,
     * creates a new
     * instance of the RegistoController class, and calls the registarJuri function,
     * which returns a
     * boolean value
     * 
     * @param rc RoutingContext
     */
    public void addJuri(RoutingContext rc) {
        String name = rc.request().getParam("nomeJuri");
        String email = rc.request().getParam("emailJuri");
        String password = rc.request().getParam("passwordJuri");
        try {
            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            RegistoController rec = new RegistoController();
            response.end(Json.encodePrettily(rec.registarJuri(name, email, password)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * It receives a request from the client, gets the parameters from the request,
     * and then calls the
     * function registarOrganizador from the RegistoController class
     * 
     * @param rc RoutingContext
     */
    public void addOrganizador(RoutingContext rc) {
        String name = rc.request().getParam("nomeOrganizador");
        String email = rc.request().getParam("emailOrganizador");
        String password = rc.request().getParam("passwordOrganizador");
        try {
            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            RegistoController rec = new RegistoController();
            response.end(Json.encodePrettily(rec.registarOrganizador(name, email, password)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * It receives a request from the client, gets the parameters from the request,
     * and adds a Robot to the database
     *
     * @param rc RoutingContext
     */
    public void addRobot(RoutingContext rc) {
        int id_equipa = lh.getUser().getId();
        String name = rc.request().getParam("nomeRobot");
        String mac_adr = rc.request().getParam("macRobot");
        try {
            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            RegistoController rec = new RegistoController();
            response.end(Json.encodePrettily(rec.registarRobot(id_equipa, name, mac_adr)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * It receives a request from the client, gets the parameters from the request,
     * and adds a Elemento to the database
     *
     * @param rc RoutingContext
     */
    public void addElemento(RoutingContext rc) {
        int id_equipa = lh.getUser().getId();
        String name = rc.request().getParam("nomeElemento");
        String email = rc.request().getParam("emailElemento");
        try {
            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            RegistoController rec = new RegistoController();
            response.end(Json.encodePrettily(rec.registarElemento(id_equipa, name, email)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * It receives a request from the client, gets the parameters from the request,
     * and adds a competition to the database
     *
     * @param rc RoutingContext
     */
    public void addCompeticao(RoutingContext rc) {
        int id_organizador = lh.getUser().getId();
        String name = rc.request().getParam("nomeCompeticao");
        String desc = rc.request().getParam("descCompeticao");
        int nrondas = Integer.parseInt(rc.request().getParam("nrondasCompeticao"));
        int maxequipas = Integer.parseInt(rc.request().getParam("maxCompeticao"));
        try {
            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            RegistoController rec = new RegistoController();
            response.end(Json.encodePrettily(rec.registarCompeticao(id_organizador, name, desc, nrondas, maxequipas)));
            rec.criarRondas(nrondas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * It receives a request from the client, gets the id of the competition and the
     * id of the user,
     * and then calls the function inscreverJuri from the RegistoController class
     * 
     * @param rc RoutingContext
     */
    public void inscreverJuri(RoutingContext rc) {
        int id_competicao = Integer.parseInt(rc.request().getParam("idCompeticao"));
        int id_juri = lh.getUser().getId();
        try {
            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            RegistoController rec = new RegistoController();
            response.end(Json.encodePrettily(rec.inscreverJuri(id_competicao, id_juri)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inscreverEquipa(RoutingContext rc) {
        int id_competicao = Integer.parseInt(rc.request().getParam("idCompeticao"));
        int id_equipa = lh.getUser().getId();
        try {
            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            RegistoController rec = new RegistoController();
            response.end(Json.encodePrettily(rec.inscreverEquipa(id_competicao, id_equipa)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void adicionarMembro(RoutingContext rc){
        int id_equipa = lh.getUser().getId();
        String nome = rc.request().getParam("membro");
        String email = rc.request().getParam("email");
        try {
            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200);
            RegistoController rec = new RegistoController();
            response.end(Json.encodePrettily(rec.adicionarMembro(id_equipa, nome, email)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This function returns the LoginHandler object
     * 
     * @return The LoginHandler object.
     */
    public LoginHandler getLh() {
        return lh;
    }
}
