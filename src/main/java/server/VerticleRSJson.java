package server;

import controller.MqttController;
import controller.TelegramBotController;
import handler.*;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;
import mqtt.MQTTCli;
import telegram.TelegramBot;

import static io.vertx.ext.web.handler.StaticHandler.DEFAULT_WEB_ROOT;

@SuppressWarnings("ALL")
public class VerticleRSJson extends AbstractVerticle {
    private final int porta = 8332;
    private RegistoHandler registoHandler;
    private LoginHandler loginHandler;
    private GetHandler getHandler;
    private UpdateHandler updateHandler;
    private DeleteHandler deleteHandler;
    private MQTTCli mqttCli;
    private TelegramBot bot;

    // A method that is called when the verticle is deployed.
    public void start(Promise<Void> promise) {
        initHandlers();
        initMqttClient();
        initTelegramBot();

        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());

        router.route("/*").handler(StaticHandler.create(DEFAULT_WEB_ROOT));

        // POST
        // Registo
        router.post("/submitEquipa").handler(registoHandler::addEquipa);
        router.post("/submitJuri").handler(registoHandler::addJuri);
        router.post("/submitOrganizador").handler(registoHandler::addOrganizador);

        router.post("/submitlogin").handler(loginHandler::verflogin);

        router.post("/Equipa/addRobot").handler(registoHandler::addRobot);
        router.post("/Equipa/addElemento").handler(registoHandler::addElemento);

        router.post("/Organizador/addCompeticao").handler(registoHandler::addCompeticao);
        router.post("/Juri/:idCompeticao/inscreverJuri").handler(registoHandler::inscreverJuri);
        router.post("/Equipa/:idCompeticao/inscreverEquipa").handler(registoHandler::inscreverEquipa);
        router.post("/Equipa/adicionarMembro").handler(registoHandler::adicionarMembro);

        // GET
        router.get("/sairUser").handler(loginHandler::sairUsr);

        router.get("/Juri/getCompeticoes").handler(getHandler::getCompeticoesJuri);
        router.get("/Juri/getCompeticao/:idCompeticao").handler(getHandler::getCompeticao);
        router.get("/Juri/:idCompeticao/getRondas").handler(getHandler::getRondasCompeticao);
        router.get("/Juri/:idRonda/getDados").handler(getHandler::getDadosRonda);
        router.get("/Juri/:idRobot/getNomeRobot").handler(getHandler::getNomeRobot);
        router.get("/Juri/:idCompeticao/getRankings").handler(getHandler::getRankingsCompeticao);
        router.get("/Juri/:idronda/getRankings").handler(getHandler::getRankingsRonda);
        router.get("/Juri/getJuri").handler(getHandler::getJuri);

        router.get("/Organizador/getCompeticoes").handler(getHandler::getCompeticoesOrganizador);
        router.get("/Organizador/getCompeticao/:idCompeticao").handler(getHandler::getCompeticao);
        router.get("/Organizador/:idCompeticao/getRondas").handler(getHandler::getRondasCompeticao);
        router.get("/Organizador/:idRonda/getDados").handler(getHandler::getDadosRonda);
        router.get("/Organizador/:idEquipa/getEquipa").handler(getHandler::getOrganizadorEquipa);
        router.get("/Organizador/:idJuri/getJuri").handler(getHandler::getOrganizadorJuri);
        router.get("/Organizador/:idCompeticao/getNomeCompeticao").handler(getHandler::getNomeCompeticao);
        router.get("/Organizador/:idCompeticao/getRankings").handler(getHandler::getRankingsCompeticao);
        router.get("/Organizador/getEquipas").handler(getHandler::getOrganizadorEquipas);
        router.get("/Organizador/getJuris").handler(getHandler::getOrganizadorJuris);

        router.get("/Equipa/getCompeticoes").handler(getHandler::getCompeticoesEquipa);
        router.get("/Equipa/getCompeticao/:idCompeticao").handler(getHandler::getCompeticao);
        router.get("/Equipa/:idCompeticao/getRondas").handler(getHandler::getRondasCompeticao);
        router.get("/Equipa/:idRonda/getDados").handler(getHandler::getDadosRonda);
        router.get("/Equipa/:idRobot/getNomeRobot").handler(getHandler::getNomeRobot);
        router.get("/Equipa/getRobos").handler(getHandler::getRobos);
        router.get("/Equipa/getEquipa").handler(getHandler::getEquipa);
        router.get("/Equipa/:idCompeticao/getRankings").handler(getHandler::getRankingsCompeticao);

        // Update
        router.put("/Equipa/updateRobot/:idrobot").handler(updateHandler::updateRobot);
        router.put("/Juri/updateTempo/:idAssociacaoRobot").handler(updateHandler::updateTempo);
        router.put("/Juri/updatePontos/:idAssociacaoRobot").handler(updateHandler::updatePontos);
        router.put("/Juri/updateJuri").handler(updateHandler::updateJuri);
        router.put("/Organizador/updateCompeticao/:idCompeticao").handler(updateHandler::updateCompeticao);
        router.put("/Equipa/updateEquipa").handler(updateHandler::updateEquipa);

        // Delete
        router.delete("/Juri/:idCompeticao/removerJuri").handler(deleteHandler::deleteJuri);
        router.delete("/Equipa/:idCompeticao/removerEquipa").handler(deleteHandler::deleteEquipa);
        router.delete("/Organizador/:idCompeticao/removerCompeticao").handler(deleteHandler::deleteCompeticao);
        router.delete("/Equipa/:idrobot/removerRobot").handler(deleteHandler::deleteRobot);
        router.delete("/Equipa/:idMembro/removerMembro").handler(deleteHandler::deleteMembro);

        // Checking if the user is authenticated. If not, it is sending the
        // error404
        router.get("/Equipa/*").handler(rs -> {
            if (!loginHandler.isAuthenticated()) {
                rs.response().setStatusCode(404);
                rs.response().end();
            } else {
                rs.next();
            }
        });

        // Checking if the user is authenticated. If not, it is sending the
        // error404
        router.get("/Juri/*").handler(rs -> {
            if (!loginHandler.isAuthenticated()) {
                rs.response().setStatusCode(404);
                rs.response().end();
            } else {
                rs.next();
            }
        });

        // Checking if the user is authenticated. If not, it is sending the
        // error404
        router.get("/Organizador/*").handler(rs -> {
            if (!loginHandler.isAuthenticated()) {
                rs.response().setStatusCode(404);
                rs.response().end();
            } else {
                rs.next();
            }
        });

        // ----------------------------------------------------------------
        // *cria servidor HTTP
        HttpServerOptions options = new HttpServerOptions();
        options.setPort(porta);
        vertx.createHttpServer(options)
                .requestHandler(router)
                .listen(res -> {
                    if (res.succeeded()) {
                        System.out.println("(Servidor) Porta: " + porta);
                        promise.complete();
                    } else {
                        promise.fail(res.cause());
                    }
                });
    }

    /**
     * The function `stop()` is a method of the class `Applet`. It is called when
     * the applet is stopped
     */
    public void stop() {
        System.out.println("--->Stopped! ");
    }

    /**
     * It initializes the handlers
     */
    public void initHandlers() {
        this.registoHandler = new RegistoHandler();
        this.loginHandler = new LoginHandler(false);
        this.getHandler = new GetHandler();
        this.updateHandler = new UpdateHandler();
        this.deleteHandler = new DeleteHandler();
    }

    public void initMqttClient() {
        MqttController mqttController = new MqttController();
        mqttCli = mqttController.newMqttCli(vertx);
    }

    public void initTelegramBot() {
        TelegramBotController tbc = new TelegramBotController();
        bot = tbc.newTelegramBot(vertx);
        tbc.telegramBot(bot);
    }

}
