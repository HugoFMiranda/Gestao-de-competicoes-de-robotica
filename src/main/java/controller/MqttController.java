package controller;

import database.DBConnection;
import io.vertx.core.Vertx;
import model.Associar_robo;
import mqtt.MQTTCli;

import java.sql.*;

/**
 * It receives a string, splits it into two integers, inserts them into a
 * database and returns the
 * number of rows affected
 */
public class MqttController {

    private static MQTTCli mqttCli;

    private static int in = 0;
    private static final Associar_robo r1 = new Associar_robo();
    private static final Associar_robo r2 = new Associar_robo();

    private Associar_robo associar_robo;

    // A constructor.
    public MqttController() {

    }

    /**
     * It creates a new MQTT client instance
     *
     * @param vertx The Vert.x instance
     * @return A new instance of the MQTTCli class.
     */
    public MQTTCli newMqttCli(Vertx vertx) {
        mqttCli = new MQTTCli(vertx);
        return mqttCli;
    }

    /**
     * It receives a string, splits it into three parts, and then inserts it into a
     * database
     *
     * @param data String
     * @return The number of rows affected by the query.
     */
    public int inserirBD(String data) throws SQLException {

        // Creating the controller for the Telegram bot.
        TelegramBotController tbc = new TelegramBotController();
        if (!data.contains("�")) {
            if (!data.isBlank() || !data.equals("") || !data.isEmpty()) {
                String velt = data.replace(",", ".");
                String[] split = velt.split("#");
                // Split
                if (split.length == 3) {
                    String mac_addrs = split[0];
                    double tempo = Double.parseDouble(split[1]);
                    double vel = Double.parseDouble(split[2]);
                    // Sql querys
                    String GETRobot_SQL = "SELECT * FROM Robot WHERE mac_address = ?;";
                    String GETEquipa_SQL = "SELECT * FROM Equipa WHERE id = ?;";
                    String INSERT_SQL = "INSERT INTO associar_robot(id_robot, id_ronda, velocidade, tempo, pontos, validado, _data_criacao_ ) VALUES (?,?,?,?,?,?,?);";
                    // Connect to sql
                    Connection conn = DBConnection.getConnection();
                    PreparedStatement ps;
                    // var
                    int res = 0;
                    ResultSet rset;
                    int step = 0;
                    int id_equipa = 0;
                    int id_robo = 0;
                    boolean robotreg = false;
                    String nome_equipa = "Sem equipa associada";
                    int id_ronda = getIdRonda();
                    associar_robo = new Associar_robo();
                    // Checking if the topic is configured correctly.
                    if (id_ronda == 0) {
                        System.out.println("Tópico mal configurado ou competição não existe!");
                        step = -1;
                    }
                    // Checking if the robot is registered in the database, if it is not, it inserts
                    // the
                    // data into the database without any associations.
                    try {
                        ps = conn.prepareStatement(GETRobot_SQL);
                        ps.setString(1, mac_addrs);
                        rset = ps.executeQuery();
                        if (rset.next()) {
                            associar_robo.setRobot(rset.getString("nome"));
                            id_equipa = rset.getInt("id_equipa");
                            id_robo = rset.getInt("id");
                            if (step != -1) {
                                step = 1;
                            }
                            robotreg = true;
                        } else {
                            System.out.println("Robô não se encontra registado!");
                            step = -1;
                        }
                        // se nao tiver inscrito por alguma razao, inserir sem associações
                        if (step == -1) {
                            if (robotreg) {
                                INSERT_SQL = "INSERT INTO associar_robot(id_robot,velocidade, tempo, pontos, validado, _data_criacao_ ) VALUES (?,?,?,?,?,?);";
                                ps = conn.prepareStatement(INSERT_SQL);
                                ps.setDouble(1, id_robo);
                                ps.setDouble(2, vel);
                                ps.setDouble(3, tempo);
                                ps.setInt(4, 0);
                                ps.setString(5, "Invalido não tem equipa registada!");
                                Timestamp timestamp = Timestamp.valueOf(associar_robo.getData_criacao());
                                ps.setTimestamp(6, timestamp);
                                System.out.println(ps);
                                res = ps.executeUpdate();
                                if (res > 0) {
                                    in++;
                                }

                            } else {
                                INSERT_SQL = "INSERT INTO associar_robot(velocidade, tempo, pontos, validado, _data_criacao_) VALUES (?,?,?,?,?);";
                                ps = conn.prepareStatement(INSERT_SQL);
                                ps.setDouble(1, vel);
                                ps.setDouble(2, tempo);
                                ps.setInt(3, 0);
                                ps.setString(4, "Invalido mac:" + mac_addrs + " não se encontra registado!");
                                Timestamp timestamp = Timestamp.valueOf(associar_robo.getData_criacao());
                                ps.setTimestamp(5, timestamp);
                                System.out.println(ps);
                                res = ps.executeUpdate();
                                if (res > 0) {
                                    in++;
                                }
                            }
                        } else {
                            if (step == 1) {
                                ps = conn.prepareStatement(GETEquipa_SQL);
                                ps.setInt(1, id_equipa);
                                rset = ps.executeQuery();
                                if (rset.next()) {
                                    nome_equipa = rset.getString("nome");
                                    step++;
                                }
                            }
                            if (step == 2) {
                                ps = conn.prepareStatement(INSERT_SQL);
                                ps.setInt(1, id_robo);
                                ps.setInt(2, getIdRonda());
                                ps.setDouble(3, vel);
                                ps.setDouble(4, tempo);
                                ps.setInt(5, 0);
                                ps.setString(6, "por validar....");
                                Timestamp timestamp = Timestamp.valueOf(associar_robo.getData_criacao());
                                ps.setTimestamp(7, timestamp);
                                System.out.println(ps);
                                res = ps.executeUpdate();
                                if (res > 0) {
                                    in++;
                                }
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    String msg = "🤖 - <b>" + nome_equipa + "</b>\n" +
                            "\nRobô: " + associar_robo.getRobot() +
                            "\n" +
                            "" + mqttCli.getSubtopico() + "- " + mqttCli.getSubsubtopico() +
                            "\n" +
                            "Tempo: " + tempo / 1000 + " segundos ⏱\n" +
                            "velocidade: " + vel + " metros/segundos 🏎\n";
                    tbc.sendMessageHtml(msg);
                    conn.close();
                    if (in == 1){
                        r1.setRobot(associar_robo.getRobot());
                    }
                    if (in == 2) {
                        r2.setRobot(associar_robo.getRobot());
                        pontos();
                        System.out.println("(SERVER) A Calcular pontos...");
                    }
                    return res;
                }
                System.out.println("(MQTT) ERRO - Wrong input: " + data);
                tbc.sendMessage("Recebi isto -> " + data + "\n" +
                        "NAO QUERO RECEBER ISTO 🤬🤬🤬🤬🤬!!!!!" +
                        "\n" +
                        "EU QUERO RECEBER 'mac#tempo#bolina'");
                return 0;
            }
            System.out.println("(MQTT) ERRO - Data empty");
            tbc.sendMessage("Recebi isto -> " + data + "\n" +
                    "NAO QUERO RECEBER ISTO 🤬🤬🤬🤬🤬!!!!!" +
                    "\n" +
                    "EU QUERO RECEBER 'mac#tempo#bolina'");
            return 0;
        }
        System.out.println("(MQTT) ERRO - Data cointains �");
        tbc.sendMessage("Recebi isto -> " + data + "\n" +
                "NAO QUERO RECEBER ISTO 🤬🤬🤬🤬🤬!!!!!" +
                "\n" +
                "EU QUERO RECEBER 'mac#tempo#bolina'");
        return 0;
    }

    /**
     * Setting the subtopic of the MQTT client.
     *
     * @param subtopico The topic you want to subscribe to.
     */
    public void setSubtopico(String subtopico) {
        mqttCli.setSubtopico(subtopico);
    }

    /**
     * Setting the sub-subtopic of the MQTT client.
     *
     * @param subsubtopico The topic you want to subscribe to.
     */
    public void setSubsubtopico(String subsubtopico) {
        mqttCli.setSubsubtopico(subsubtopico);
    }

    /**
     * It gets the id of a competition from the database, given the name of the
     * competition
     *
     * @return The id of the competition.
     */
    public int getIdCompeticao() {
        String GETcompeticao_SQL = "SELECT * FROM Competicao WHERE nome = ?;";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rset;
        try {
            ps = conn.prepareStatement(GETcompeticao_SQL);
            ps.setString(1, mqttCli.getSubtopico());
            rset = ps.executeQuery();
            if (rset.next()) {
                return rset.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * It gets the id of a round from the database
     *
     * @return The id of the round.
     */
    public int getIdRonda() {
        String GETrondas_SQL = "SELECT * FROM Ronda WHERE id_competicao = ? AND numero = ?;";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rset;
        int id_comp = getIdCompeticao();
        if (id_comp != 0) {
            try {
                ps = conn.prepareStatement(GETrondas_SQL);
                ps.setInt(1, id_comp);
                ps.setString(2, mqttCli.getSubsubtopico());
                rset = ps.executeQuery();
                if (rset.next()) {
                    return rset.getInt("id");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static MQTTCli getMqttCli() {
        return mqttCli;
    }

    public static void setMqttCli(MQTTCli mqttCli) {
        MqttController.mqttCli = mqttCli;
    }

    public int getIn() {
        return in;
    }

    public void setIn(int in) {
        MqttController.in = in;
    }

    public Associar_robo getAssociar_robo() {
        return associar_robo;
    }

    public void setAssociar_robo(Associar_robo associar_robo) {
        this.associar_robo = associar_robo;
    }

    public int pontos() {
        String GETassociarSQL2 = "SELECT * FROM associar_robot WHERE ID = (SELECT MAX(ID) FROM associar_robot);";
        String GETassociarSQL = "SELECT * FROM associar_robot WHERE ID = (SELECT MAX(ID-1) FROM associar_robot);";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rset;
        int res = 0;
        try {
            ps = conn.prepareStatement(GETassociarSQL);
            rset = ps.executeQuery();
            if (rset.next()) {
                r1.setId(rset.getInt("id"));
                r1.setId_robot(rset.getInt("id_robot"));
                r1.setVelocidade(String.valueOf(rset.getInt("velocidade")));
                r1.setTempo(String.valueOf(rset.getInt("tempo")));
            }
            ps = conn.prepareStatement(GETassociarSQL2);
            rset = ps.executeQuery();
            if (rset.next()) {
                r2.setId(rset.getInt("id"));
                r2.setId_robot(rset.getInt("id_robot"));
                r2.setVelocidade(String.valueOf(rset.getInt("velocidade")));
                r2.setTempo(String.valueOf(rset.getInt("tempo")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int tempo_r1 = Integer.parseInt(r1.getTempo());
        int tempo_r2 = Integer.parseInt(r2.getTempo());
        TelegramBotController tbc = new TelegramBotController();

        if (tempo_r1 < tempo_r2) {
            r1.setPontos(5);
            r2.setPontos(3);
            tbc.sendMessageHtml("🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆\n\n" +
                    "<b>" + r1.getRobot() + "</b> venceu <b>" + r2.getRobot() + "</b> por " + (tempo_r2-tempo_r1)/1000 + " segundos!" +
                    "\nPONTOS:" +
                    "\n" + r1.getRobot() + ": " + r1.getPontos() +
                    "\n" + r2.getRobot() + ": " + r2.getPontos());

        } else {
            r2.setPontos(5);
            r1.setPontos(3);
            tbc.sendMessageHtml("🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆\n\n" +
                    "<b>" + r2.getRobot() + "</b> venceu <b>" + r1.getRobot() + "</b> por " + (tempo_r1-tempo_r2)/1000 + " segundos!" +
                    "\nPONTOS:" +
                    "\n" + r2.getRobot() + ": " + r2.getPontos() +
                    "\n" + r1.getRobot() + ": " + r1.getPontos());

        }
        String UPDATE_SQL = "UPDATE associar_robot SET pontos = ? WHERE ID = ?;";
        try {
            ps = conn.prepareStatement(UPDATE_SQL);
            ps.setInt(1, r1.getPontos());
            ps.setInt(2, r1.getId());
            res = ps.executeUpdate();
            UPDATE_SQL = "UPDATE associar_robot SET pontos = ? WHERE ID = ?;";
            ps = conn.prepareStatement(UPDATE_SQL);
            ps.setInt(1, r2.getPontos());
            ps.setInt(2, r2.getId());
            res = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        in = 0;
        return res;
    }

}
