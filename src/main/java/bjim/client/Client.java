package bjim.client;

import static java.util.stream.Collectors.toSet;

import bjim.common.Connection;
import bjim.common.MessageParser;
import bjim.common.MessageParser.Message;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.Getter;
import lombok.Value;

public class Client {
    int a; //added a new variable
    private static final int SERVER_PORT = 6789;
    private static final int serverPort = SERVER_PORT; // todo: allow setting in constructor
    public static final String LOCAL_HOST = "127.0.0.1";
    private static final String CONNECTION_CLOSED = "Connection closed";

    private final String serverIP;
    private Connection connection;

    private connection_button;

    private String lastReceivedMessage = "";

    @Getter private Set<String> onlineUsers;

    private final MainWindow mainWindow;

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Value(staticConstructor = "username")
    public static class Username {
        String username;
    }

    @Value(staticConstructor = "serverIP")
    public static class ServerIP {
        String serverIP;
    }

    public Client() {
        this(ServerIP.serverIP(LOCAL_HOST));
    }

    public Client(Username userName) {
        this(ServerIP.serverIP(LOCAL_HOST), userName);
    }

    @SuppressWarnings("unused")
    public Client(ServerIP serverIP) {
        this(serverIP, Username.username("Client"));
    }

    @SuppressWarnings("unused")
    public Client(ServerIP serverIP, Username username) {
        this.serverIP = serverIP.serverIP;
        this.mainWindow = new MainWindow(username.username);
        this.mainWindow.onSend(event -> sendMessage(event.getActionCommand()));
        this.mainWindow.onUsernameSelected();
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

        String messageToSend = mainWindow.getUsername() + ":\n  " + message;

        try {
            sendMessage(messageToSend, connection);
            showSentMessage(MessageParser.parse(messageToSend));
        } catch (IOException ioException) {
            setStatus("Failed to send message");
        }
    }

    public void sendControlMessage(String message) {
        try {
            sendMessage(message, connection);
        } catch (IOException ioException) {
            setStatus("Failed to send control message");
        }
    }

    private void sendMessage(String messageToSend, Connection connection) throws IOException {
        connection.getOutput().writeObject(messageToSend);
        connection.getOutput().flush();
    }

    private void showSentMessage(Message message) throws IOException {
        mainWindow.showSentMessage(message);
    }

    private void showReceivedMessage(String message) throws IOException {
        mainWindow.showReceivedMessage(message);
    }

    public void setDefaultCloseOperation(int exitOnClose) {
        mainWindow.setDefaultCloseOperation(exitOnClose);
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
        return mainWindow.getUsername();
    }

    private class StartClient implements Runnable {

        @Override
        public void run() {
            try {
                connectToServer();
                sendUsername();
                whileChatting();
            } catch (IOException eofException) {
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
            do {
                try {
                    lastReceivedMessage = String.valueOf(connection.getInput().readObject());
                    if (lastReceivedMessage.startsWith("users:")) {
                        updateOnlineUsers();
                    } else {
                        showReceivedMessage(lastReceivedMessage);
                    }

                } catch (ClassNotFoundException e) {
                    setStatus("Dont know ObjectType!");
                }
            } while (!lastReceivedMessage.equals("\nADMIN - END"));
        }

        private void disconnect() {
            setStatus(CONNECTION_CLOSED);
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (IOException e) {
                setStatus(CONNECTION_CLOSED);
            }
        }
    }

    private void setStatus(String text) {
        mainWindow.setStatus(text);
    }

    private void updateOnlineUsers() {
        String[] onlineUsersArray = lastReceivedMessage.split(":")[1].split(",");
        onlineUsers = Arrays.stream(onlineUsersArray).collect(toSet());
        mainWindow.setOnlineUsersJList(onlineUsersArray);
    }
}
