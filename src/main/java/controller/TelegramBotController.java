package controller;

import io.vertx.core.Vertx;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import telegram.TelegramBot;

/**
 * It creates a new TelegramBot object, and then registers it with the
 * TelegramBotsApi.
 */
public class TelegramBotController {
    // A static variable that is used to store the TelegramBot object.
    private static TelegramBot bot;

    // A constructor.
    public TelegramBotController() {
    }

    /**
     * This function creates a new TelegramBot object and returns it.
     * 
     * @param vertx The Vert.x instance
     * @return A new instance of TelegramBot
     */
    public TelegramBot newTelegramBot(Vertx vertx) {
        bot = new TelegramBot(vertx);
        return bot;
    }

    /**
     * It creates a new TelegramBotsApi object, which is used to register the bot
     * 
     * @param bot The bot object that you created.
     */
    public void telegramBot(TelegramBot bot) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace(System.out);
        }
    }

    /**
     * This function returns the bot object
     * 
     * @return The bot object.
     */
    public TelegramBot getBot() {
        return bot;
    }

    /**
     * This function sets the bot variable to the bot variable passed in the
     * function
     * 
     * @param bot The bot object that you created in the previous step.
     */
    public void setBot(TelegramBot bot) {
        TelegramBotController.bot = bot;
    }

    /**
     * The function takes a string as an argument and sends it to the bot
     * 
     * @param msg The message to send
     */
    public void sendMessage(String msg) {
        bot.sendMessage(msg);
    }

    public void sendMessageHtml(String msg) {
        bot.sendMessageHtml(msg);
    }
}
