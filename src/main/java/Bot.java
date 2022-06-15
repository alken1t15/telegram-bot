
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Bot extends TelegramLongPollingBot {
    Model model = new Model();
    public String c;

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi =  new TelegramBotsApi(DefaultBotSession.class);

        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


    }

    public void sendMsg(Message message, String text){ // Метод отвечает за отправку сообщения
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            setButtons(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public  void onUpdateReceived(Update update){ // Метод который слуашет сообщения в чате
        Message message = update.getMessage();
        if(message != null && message.hasText()){
            switch (message.getText()){
                case "/help":
                    sendMsg(message, "Справка: Ваш заказ имеет уникальный номер, который можно получить на сайте и ввести сюда по команде \n/status <номер>. Введя номер заказа вы получите подробную информацию о нем и его характеристиках.\n" +
                            "Вы можете отменить свой заказ введя команду \n/cancel <номер>. Также доступна информация о нашей компании по команде /contact.");
                            break;
                case "/contact" :
                    sendMsg(message, "Наши контакты: \n" +
                            "Интернет-магазин: https://kramzos.ru/MARCHO/\n" +
                            "Телефон: +7 747 325 44 21\n" +
                            "Почта: marcho@gmail.com \n" +
                            "Marcho Logistic Group, LLC");
                    break;
                case "/cancel":
                    sendMsg(message , "Введите команду /cancel <номер> заказа");
                    break;
                case "/status":
                    sendMsg(message , "Введите команду /status <номер> заказа");
                    break;
             /*   case "/weather":
                    try {
                        sendMsg(message, Weather.gerWeather("Astana", model));
                    } catch (IOException e) {
                        sendMsg(message, "Такой город не найден!");
                    }

              */
                default:
                    sendMsg(message ,status(message));
                    if(message == null){
                        sendMsg(message ,"2");
                    }

            }
        }

    }
    public  void setButtons (SendMessage sendMessage){ // Метод отвечает за кнопки
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("/help"));
        keyboardFirstRow.add(new KeyboardButton("/contact"));

        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);

    }
    public String status(Message message){ // Функционал комманд
        String b =message.getText().toString();
        String arr[] = b.split(" ",2);
        String firstWord = arr[0];
        if(firstWord.equals("/status")){
            String theRest = arr[1];
           int a = Integer.parseInt(theRest);//12345
            MySql.Sql(a);
            return Query.query();
        }
        else if(firstWord.equals("/cancel")){
            String theRest = arr[1];
            int a = Integer.parseInt(theRest);
            Cancel.Sql(a);
            return "Ваш заказ отменен!";
        }
        else if(firstWord.equals("/weather")){
            String theRest = arr[1];
            try {
                sendMsg(message, Weather.gerWeather(theRest, model));
            } catch (IOException e) {
                sendMsg(message, "Такой город не найден!");
            }
        }
       return c;


    }

    @Override
    public String getBotUsername() {
        return "Marcho";
    }

    @Override
    public String getBotToken() {
        return "1762842300:AAErkUPPrTYvw6M2bsOEZYq-lLY8Xg1pd3s";
    }

}