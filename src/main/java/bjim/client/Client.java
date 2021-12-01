package bjim.client;

import static java.util.stream.Collectors.toSet;

import bjim.common.Connection;

import javax.swing.text.BadLocationException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.Getter;

public class Client {

    public static final String LOCAL_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 6789;
    private static final String CONNECTION_CLOSED = "Connection closed";

    private final ClientChatWindow chatWindow;
    private final String serverIP;
    private static final int serverPort = SERVER_PORT; // todo: allow setting in constructor
    private Connection connection;

    private String lastReceivedMessage = "";
    boolean type;
     String messageToSend;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private ObjectOutputStream output;

    @Getter private Set<String> onlineUsers;


    public Client() throws IOException, BadLocationException {
        this(new ClientChatWindow());
    }

    @SuppressWarnings("unused")
    public Client(String serverIP) throws IOException, BadLocationException {
        this(serverIP, new ClientChatWindow());
    }

    @SuppressWarnings("unused")
    public Client(String serverIP, String username) throws IOException, BadLocationException {
        this(serverIP, new ClientChatWindow(username));
    }

    public Client(ClientChatWindow chatWindow) {
        this(LOCAL_HOST, chatWindow);
    }

    public Client(String serverIP, ClientChatWindow chatWindow) {
        this.serverIP = serverIP;
        this.chatWindow = chatWindow;
        this.chatWindow.onSend(event -> sendMessage(event.getActionCommand()));
    }

    public boolean isWindowVisibleClientSide() {
        return chatWindow.isVisible();
    }

    public void startRunning() {
        executorService.submit(new StartClient());
    }

    public String getLastReceivedMessage() {
        return lastReceivedMessage;
    }

    public void stopRunning() {
        System.out.println("Stopping client...");
        while (connection != null && !connection.isClosed()) {
            try {
                connection.close();
                return;
            } catch (IOException e) {
                System.out.println("Failed to stop client...");
            }
        }
    }

    public void sendMessage(String message) {

        if(message.equals(":D"))
        {  messageToSend = chatWindow.getUsername() + ":\n  "+"sm" ;}

        else   if(message.equals(":'("))
        {  messageToSend = chatWindow.getUsername() + ":\n  "+"cr" ;}
        else   if(message.equals("<3)"))
        {  messageToSend = chatWindow.getUsername() + ":\n  "+"hr" ;}
        else   if(message.equals(":("))
        {  messageToSend = chatWindow.getUsername() + ":\n  "+"sd" ;}

        else   if(message.equals("o.O"))
        {  messageToSend = chatWindow.getUsername() + ":\n  "+"ag" ;}
        else   if(message.equals(":'D"))
        {  messageToSend = chatWindow.getUsername() + ":\n  "+"sc" ;}





        else {messageToSend = chatWindow.getUsername() + ":\n  " + message;}
        try {
            sendMessage(messageToSend, connection);
            showMessage("\n" + messageToSend);
        } catch (IOException | BadLocationException ioException) {
            chatWindow.append("\nSomething is messed up!");
        }
    }

    public void sendControlMessage(String message) {
        try {
            sendMessage(message, connection);
        } catch (IOException ioException) {
            chatWindow.append("\nSomething is messed up!");
        }
    }

    private void sendMessage(String messageToSend, Connection connection) throws IOException {
        connection.getOutput().writeObject(messageToSend);
        connection.getOutput().flush();
    }

    private void showMessage(final String m) throws BadLocationException {
        chatWindow.showMessage(m);
    }

    public void setDefaultCloseOperation(int exitOnClose) {
        chatWindow.setDefaultCloseOperation(exitOnClose);
    }

    public String getServerIP() {
        return serverIP;
    }

    public boolean isConnected() {
        return connection != null && connection.isConnected();
    }

    private void sendUsername() {
        sendControlMessage("username:".concat(getUsername()));
    }

    private String getUsername() {
        return chatWindow.getUsername();
    }

    private class StartClient implements Runnable {

        @Override
        public void run() {
            try {
                connectToServer();
                sendUsername();
                whileChatting();
            } catch (IOException | BadLocationException eofException) {
                setStatus(CONNECTION_CLOSED);
            } finally {
                disconnect();
            }
        }

        private void connectToServer() throws IOException {
            setStatus("Attempting to connect to server @" + serverIP + ":" + serverPort);
            connection = new Connection(new Socket(InetAddress.getByName(serverIP), serverPort));
            setStatus("Connected to server @" + serverIP + ":" + serverPort);
        }

        private void whileChatting() throws IOException {
            ableToType(true);
            do {
                try {
                    lastReceivedMessage = String.valueOf(connection.getInput().readObject());
                    if (lastReceivedMessage.startsWith("users:")) {
                        updateOnlineUsers();
                    } else {
                        showMessage("\n" + lastReceivedMessage);
                    }

                } catch (ClassNotFoundException classNotFoundException) {
                    showMessage("\nDont know ObjectType!");
                }
            } while (!lastReceivedMessage.equals("\nADMIN - END"));
        }

        private void disconnect() {
            setStatus(CONNECTION_CLOSED);
            ableToType(false);
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (IOException e) {
                setStatus(CONNECTION_CLOSED);
            }
        }

        private void ableToType(final boolean tof) {
            chatWindow.ableToType(tof);
        }

        private void setStatus(String text) {
            chatWindow.setStatus(text);
        }
    }

    private void updateOnlineUsers() {
        onlineUsers = Arrays.stream(lastReceivedMessage.split(":")[1].split(",")).collect(toSet());
    }
}
