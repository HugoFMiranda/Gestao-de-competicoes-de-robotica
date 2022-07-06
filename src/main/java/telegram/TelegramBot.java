package telegram;

import controller.GetController;
import controller.MqttController;
import io.vertx.core.Vertx;
import keys.keys;
import model.Ranking;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendVoice;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;
import java.util.List;

/**
 * > This class extends the TelegramLongPollingBot class, which is a class that
 * allows us to connect to
 * the Telegram API and receive messages
 */
public class TelegramBot extends TelegramLongPollingBot {

    private final Vertx vertx;
    private final String chat_id = keys.chat_id;
    private int op = 0;

    private String subtopico = "LSIS1";

    private String subsubtopico = "teste";

    private final MqttController mqttController = new MqttController();

    // A constructor.
    public TelegramBot(Vertx vertx) {
        this.vertx = vertx;
        System.out.println("(Telegram) Bot Iniciado");
        sendMessageHtml("\n🏎🏎🏎🏎🏎🏎🏎🏎🏎 <b>IGP</b> 🏎🏎🏎🏎🏎🏎🏎🏎🏎" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "Utilize os comandos <b>'/competicao' e '/ronda'</b> para configurar o subtópico e sub-subtópico do seu robô 🤖" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "<b>'/comandos'</b> para ver todos os comandos disponíveis" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "🏎🏎🏎🏎🏎🏎🏎🏎🏎🏎🏎🏎🏎🏎🏎🏎🏎🏎🏎");
    }

    /**
     * It sends a message to the user
     *
     * @param msg The message you want to send.
     */
    public void sendMessage(String msg) {
        SendMessage message = new SendMessage();
        message.setText(msg);
        message.setChatId(chat_id);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace(System.out);
        }
    }

    public void sendMessageHtml(String msg) {
        SendMessage message = new SendMessage();
        message.setParseMode("html");
        message.setText(msg);
        message.setChatId(chat_id);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace(System.out);
        }
    }

    /**
     * If the message has text, print the message
     *
     * @param update The update object that contains the information about the
     *               message.
     */
    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            System.out.print("(Telegram) Mensagem de: ");
            System.out.println(update.getMessage().getFrom().getFirstName());
            if (update.getMessage().hasText()) {
                String msgRec = update.getMessage().getText();
                System.out.println("(Telegram) " + msgRec);
                if (msgRec.contains("Boas")) {
                    sendMessage("BOASSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS PESSOAAAAAALLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL" +
                            "\n" +
                            "\n" +
                            "DAQUI QUEM FALA É O FEROMONAS E SEJAM BEM VINDOS A MAISSSSSSSSSSSSSSS UM VIDEO" +
                            "\n" +
                            "\n" +
                            "😍😍😍😍😍😍😍😍😍😍😍😍😍😍😍😍");
                    try {
                        sendVoice("https://cdn.discordapp.com/attachments/895411302263050274/987854457675075644/Nova-Intro-do-Feromonas.opus");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (msgRec.contains("/competicao")) {
                    sendMessage("Insira o nome da competição");
                    op = 1;
                } else if (msgRec.contains("/ronda")) {
                    sendMessage("Insira o numero da ronda");
                    op = 2;
                } else if (msgRec.contains("/verinfo")) {
                    verinfo();
                    op = 0;
                } else if (msgRec.contains("/rankings")) {
                    verrankings();
                    op = 3;
                } else if (msgRec.contains("/inforankings")) {
                    verrankingsinfo();
                    op = 4;
                } else if (msgRec.contains("/comandos")) {
                    vercomandos();
                    op = 0;
                }else if (msgRec.contains("/1")) {
                    maisum();
                    op = 0;
                }else if (msgRec.contains("/0")) {
                    zerarin();
                    op = 0;
                } else
                    ops(msgRec);
                }
            }
        }

    private void maisum() {
        int in = mqttController.getIn();
        if (in == 2) {
            mqttController.setIn(0);
            return;
        }

        mqttController.setIn(in+1);
        sendMessage("IN = " + mqttController.getIn());
    }

    private void zerarin() {
        mqttController.setIn(0);
        sendMessage("IN = " + mqttController.getIn());
    }



    private void sendVoice(String s) throws TelegramApiException {
        SendVoice sendVoice = new SendVoice();
        InputFile file = new InputFile(s);
        sendVoice.setVoice(file);
        sendVoice.setChatId(chat_id);
        sendVoice.setDuration(13);
        execute(sendVoice);
    }

    private void vercomandos() {
        sendMessage("\nLista de comandos disponiveis\n\n" +
                "'/competicao' - Insira o nome da competição a que pertence \n" +
                "'/ronda' - Insira o número da ronda que esta a competir\n" +
                "'/verinfo'- Mostra o tópico em que esta inscrito no mqtt\n" +
                "'/comandos' - Mostra todos os comandos disponíveis\n" +
                "'/rankings' - Mostra os rankings de uma competição\n"+
                "'/inforankings' - Mostra os rankings de uma competição com o melhor tempo e melhor velocidade");
    }

    private void verinfo() {
        sendMessage("Está a decorrer a competição: " + subtopico + " ronda: " + subsubtopico +
                "\n" +
                "\n" +
                "Para alterar porfavor utilize '/competicao' e '/ronda'");
    }

    /**
     * This function returns the username of the bot
     *
     * @return The bot's username.
     */
    @Override
    public String getBotUsername() {
        return "@lsis1equipa6bot";
    }

    /**
     * This function returns the token of the bot
     *
     * @return The bot token.
     */
    @Override
    public String getBotToken() {
        return keys.Apikey;
        // bot teste
        //return "5557473827:AAHpHAegAu4Zh0lZSwo6CysZ5XcwR3cMxxk";
    }

    public void ops(String msgRec) {
        switch (op) {
            case 1:
                String subtopicotemp = subtopico;
                subtopico = msgRec;
                mqttController.setSubtopico(subtopico);
                sendMessageHtml("Competicao alterada de <s>" + subtopicotemp + "</s> para: " + subtopico);
                System.out.println("(telegram) Competicao alterada para: " + subtopico);
                verinfo();
                op = 0;
                break;
            case 2:
                String subsubtopicotemp = subsubtopico;
                subsubtopico = msgRec;
                mqttController.setSubsubtopico(subsubtopico);
                sendMessageHtml("Ronda alterada de <s>" + subsubtopicotemp + "</s> para: " + subsubtopico);
                System.out.println("(telegram) Ronda alterada para: " + subsubtopico);
                verinfo();
                op = 0;
                break;
            case 3:

                break;
            default:
                break;
        }
    }

    private void verrankings() {
        GetController getController = new GetController();
        int id_comp = 0;
        try {
            id_comp = getController.getidCompeticao(subtopico);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int i = 0;
        List<Ranking> listRankings = getController.getRankingsCompeticao(id_comp);
        String str = "\uD83C\uDFC6 <b>Rankings</b>  \uD83C\uDFC6\n" + "\n";
        for (Ranking ranking : listRankings) {
            i++;
            if (i == 1) {
                str += "🥇 <b>" + i + " - " + ranking.getNome_equipa() + "</b> - " + ranking.getNome_robo() + " - " + ranking.getPontos() + "\n";
            } else if (i == 2) {
                str += "🥈 <b>" + i + " - " + ranking.getNome_equipa() + "</b> - " + ranking.getNome_robo() + " - " + ranking.getPontos() + "\n";
            } else if (i == 3) {
                str += "🥉 <b>" + i + " - " + ranking.getNome_equipa() + "</b> - " + ranking.getNome_robo() + " - " + ranking.getPontos() + "\n";
            } else {
                str += "🍬 <b>" + i + " - " + ranking.getNome_equipa() + "</b> - " + ranking.getNome_robo() + " - " + ranking.getPontos() + "\n";
            }
        }
        sendMessageHtml(str);
    }

    private void verrankingsinfo() {
        GetController getController = new GetController();
        int id_comp = 0;
        try {
            id_comp = getController.getidCompeticao(subtopico);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int i = 0;
        List<Ranking> listRankings = getController.getRankingsCompeticao(id_comp);
        String str = "\uD83C\uDFC6 <b>Rankings</b>  \uD83C\uDFC6\n" + "\n";
        for (Ranking ranking : listRankings) {
            i++;
            double tempo = ranking.getTempoMilissegundos()/1000;
            if (i == 1) {
                str += "🥇 <b>" + i + " - " + ranking.getNome_equipa() + "</b> - " + ranking.getNome_robo() + " - " + ranking.getPontos() + "\n" +
                        "       Melhor tempo: " + tempo + " segundos ⏱\n" +
                        "       Melhor velocidade: " + ranking.getVelmax() + " 🏎\n\n";
            } else if (i == 2) {
                str += "🥈 <b>" + i + " - " + ranking.getNome_equipa() + "</b> - " + ranking.getNome_robo() + " - " + ranking.getPontos() + "\n" +
                        "       Melhor tempo: " + tempo + " segundos ⏱\n" +
                        "       Melhor velocidade: " + ranking.getVelmax() + " 🏎\n\n";
            } else if (i == 3) {
                str += "🥉 <b>" + i + " - " + ranking.getNome_equipa() + "</b> - " + ranking.getNome_robo() + " - " + ranking.getPontos() + "\n" +
                        "       Melhor tempo: " + tempo + " segundos ⏱\n" +
                        "       Melhor velocidade: " + ranking.getVelmax() + " 🏎\n\n";
            } else {
                str += "🍬 <b>" + i + " - " + ranking.getNome_equipa() + "</b> - " + ranking.getNome_robo() + " - " + ranking.getPontos() + "\n" +
                        "       Melhor tempo: " + tempo + " segundos ⏱\n" +
                        "       Melhor velocidade: " + ranking.getVelmax() + " 🏎\n\n";
            }
        }
        sendMessageHtml(str);
    }

    public String getChat_id() {
        return chat_id;
    }

    public int getOp() {
        return op;
    }

    public void setOp(int op) {
        this.op = op;
    }

    public String getSubtopico() {
        return subtopico;
    }

    public void setSubtopico(String subtopico) {
        this.subtopico = subtopico;
    }

    public String getSubsubtopico() {
        return subsubtopico;
    }

    public void setSubsubtopico(String subsubtopico) {
        this.subsubtopico = subsubtopico;
    }
}
