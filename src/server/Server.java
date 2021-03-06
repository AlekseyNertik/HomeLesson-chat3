package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.*;

public class Server{
    SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss");
    List<ClientHandler> clients;
    private AuthService authService;

    private static int PORT = 8189;
    ServerSocket server = null;
    Socket socket = null;
    private static final Logger logger = Logger.getLogger(""); // подключаю лог

    public Server() {

// Запускаю логирование в файл
        logger.setLevel(Level.ALL);
        Handler fileHandler = null;
        try {
            fileHandler = new FileHandler("server.log");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        fileHandler.setLevel(Level.ALL);
        fileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);
// конец блока запуска логирование


        clients = new Vector<>();
        authService = new SimpleAuthService();

        try {
            server = new ServerSocket(PORT);
            System.out.println("Сервер запущен");
            logger.log(Level.ALL, "УРА! Сервер запущен! ");

            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");
                logger.log(Level.ALL, "Ого! Клиент подключился! "+ socket);
//                clients.add(new ClientHandler(this, socket));
//                subscribe(new ClientHandler(this, socket));
                new ClientHandler(this, socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadcastMsg(ClientHandler sender, String msg) {
        String message = String.format("%s %s : %s",formater.format(new Date()), sender.getNickName(), msg);
        logger.log(Level.ALL, "Сообщение для всех " + message);
        for (ClientHandler client : clients) {
            client.sendMsg(message);

        }
    }

    public void privateMsg(ClientHandler sender, String receiver, String msg) {
        String message = String.format("%s [%s] private [%s] : %s",formater.format(new Date()), sender.getNickName(), receiver, msg);
        for (ClientHandler c : clients) {
            if (c.getNickName().equals(receiver)) {
                c.sendMsg(message);
                logger.log(Level.ALL, "Ух ты! личное сообщение! :" + message);
                if (!c.equals(sender)) {    // отправитель != получателю
                    sender.sendMsg(message);

                }
                return;
            }
        }

        sender.sendMsg("not found user: " + receiver);
        logger.log(Level.ALL, "Пичаль-беда! Такого User`а нету: "+ receiver);

    }

    public void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
        broadcastClientList();
    }

    public void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        broadcastClientList();
    }

    public AuthService getAuthService(){
        return authService;
    }

    public boolean isLoginAuthenticated(String login){
        for (ClientHandler c : clients) {
            if (c.getLogin().equals(login)){
                return true;
            }
        }
        return false;
    }

    private void broadcastClientList(){
        StringBuilder sb = new StringBuilder("/clientsList ");
        for (ClientHandler c : clients) {
            sb.append(c.getNickName()).append(" ");
        }
        String msg = sb.toString();
        for (ClientHandler c : clients) {
            c.sendMsg(msg);
        }
    }
}
