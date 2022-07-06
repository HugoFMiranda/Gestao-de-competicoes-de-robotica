package mqtt;

import controller.MqttController;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.mqtt.MqttClient;
import io.vertx.mqtt.MqttClientOptions;
import io.vertx.mqtt.messages.MqttConnAckMessage;
import io.vertx.mqtt.messages.MqttPublishMessage;

// Suppressing all warnings.
@SuppressWarnings("ALL")
/**
 * It's a wrapper around the MQTT client library that provides a simple
 * interface for publishing and
 * subscribing to MQTT topics
 */
public class MQTTCli {

    private final MqttClient client;

    //private final MqttClient clientePub;
    private final Vertx vertx;
    private final String BROKER_HOST = "mq.ttmby.org";
    private final int BROKER_PORT = 1883;
    private final String USER_NAME = "grupo6";
    private final String PASSWORD = "grupo6";
    private final String topico1 = "IGP/LSIS1/Dados";
    private String subtopico = "LSIS1";
    private String subsubtopico = "teste";

    //private String topico2 = "IGP/" + subtopico + "/" + subsubtopico;

    // It's a wrapper around the MQTT client library that provides a simple
    // interface for publishing
    // and
    // * subscribing to MQTT topics
    public MQTTCli(Vertx vertx) {
        this.vertx = vertx;

        MqttClientOptions options = new MqttClientOptions();
        options.setUsername(USER_NAME);
        options.setPassword(PASSWORD);
        client = MqttClient.create(vertx, options);
        //clientePub = MqttClient.create(vertx, options);
        Future<MqttConnAckMessage> maqiatto = client.connect(BROKER_PORT, BROKER_HOST);
        //Future<MqttConnAckMessage> maqiatto2 = clientePub.connect(BROKER_PORT, BROKER_HOST);
        maqiatto.onComplete(connectHandler(client));
        //maqiatto2.onComplete(connectHandler(clientePub));

    }

    /**
     * If the connection is successful, the client subscribes to the topic
     * 
     * @param client The MQTT client instance.
     * @return A Handler<AsyncResult<MqttConnAckMessage>>
     */
    private Handler<AsyncResult<MqttConnAckMessage>> connectHandler(MqttClient client) {
        return s -> {
            if (s.succeeded()) {
                System.out.println("(MQTT) ligado e a subscrever topicos \\id: " + client.clientId());
                subscrever(topico1);
            } else {
                System.out.println("(MQTT) Falhou ligacao ");
                s.cause();
            }
        };
    }

    /**
     * The function subscribes to a topic and then publishes a message to that topic
     * 
     * @param topico The topic to subscribe to.
     */
    public void subscrever(String topico) {
        client.publishHandler(topicHandler())
                .subscribe(topico, 0);
    }

    public void desinscrever(String topico) {
        client.unsubscribe(topico);
        System.out.println("(MQTT) Desinscrito do topico: " + topico);
    }

    /**
     * It receives a message from the MQTT broker, prints it to the console, and
     * then inserts it into a
     * database
     * 
     * @return A Handler<MqttPublishMessage>
     */
    private Handler<MqttPublishMessage> topicHandler() {
        return s -> {
            System.out.println("(MQTT) --- Mensagem Nova ---");
            System.out.println("(MQTT) Topico: " + s.topicName());
            System.out.println("(MQTT) Mensagem: " + s.payload().toString());
            System.out.println("(MQTT) QoS: " + s.qosLevel());
            MqttController mqttController = new MqttController();
            try {
                mqttController.inserirBD(s.payload().toString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

//    private void puplishTopico() {
//        client.publish(topico1, Buffer.buffer(topico2), MqttQoS.AT_MOST_ONCE,false,false);
//    }

    public String getTopico1() {
        return topico1;
    }

    public String getSubtopico() {
        return subtopico;
    }

    public void setSubtopico(String subtopico) {
        this.subtopico = subtopico;
//        updateTopico2();
    }

    public String getSubsubtopico() {
        return subsubtopico;
    }

    public void setSubsubtopico(String subsubtopico) {
        this.subsubtopico = subsubtopico;
//        updateTopico2();
    }

//    public String getTopico2() {
//        return topico2;
//    }
//
//    public void setTopico2(String topico2) {
//        this.topico2 = topico2;
//    }
//
//    public void updateTopico2(){
//        desinscrever(topico2);
//        this.topico2 = "IGP/" + subtopico + "/" + subsubtopico;
//        subscreverTopico2();
//    }
//
//    public void subscreverTopico2(){
//        System.out.println("(MQTT) A subscrever topicos " + topico2);
//        subscrever(topico2);
//    }

}
