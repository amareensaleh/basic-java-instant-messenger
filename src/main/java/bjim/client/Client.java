package bjim.client;

import bjim.common.Connection;

import javax.swing.text.BadLocationException;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

    public static final String LOCAL_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 6789;
    private static final String CONNECTION_CLOSED = "Connection closed";

    private final ClientChatWindow chatWindow;
    private final String serverIP;
    private final int serverPort = SERVER_PORT; // todo: allow setting in constructor

    private Connection connection;
    String messageToSend;
    private String lastReceivedMessage = "";
    private ObjectOutputStream output;
    private ObjectInputStream input;
    boolean type;



    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static Client withUsername(String username) throws IOException, BadLocationException {
        return new Client(LOCAL_HOST, username);
    }

    public Client() throws IOException, BadLocationException {
        this(new ClientChatWindow());
    }

    public Client(String serverIP) throws IOException, BadLocationException {
        this(serverIP, new ClientChatWindow());
    }

    public Client(String serverIP, String username) throws IOException, BadLocationException {
        this(serverIP, new ClientChatWindow(username));
    }

    public Client(ClientChatWindow chatWindow) throws BadLocationException {
        this(LOCAL_HOST, chatWindow);
    }

    public Client(String serverIP, ClientChatWindow chatWindow) throws BadLocationException {
        this.serverIP = serverIP;
        this.chatWindow = chatWindow;
        this.chatWindow.onSend(event -> sendMessage(event.getActionCommand()));

    }

    public boolean isWindowVisibleClientSide() {
        return chatWindow.isVisible();
    }

    public boolean checktypingstatus() throws IOException {
        return type;
    }

    public void startRunning1() {

        executorService.submit(new StartClient1());
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
        String lastfourdig=message.substring(message.length()-4);


        if(lastfourdig.equals(".txt"))
        {
            System.out.println("it is a text");
        }


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

    private class StartClient1 implements Runnable {

        @Override
        public void run() {
            try {
                connectToServer();
                setupStreams();

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


        private void setupStreams() throws IOException, BadLocationException {
            output = new ObjectOutputStream(connection.getSocket().getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getSocket().getInputStream());
            showMessage("\nStreams are now good to go!");
        }

        private void whileChatting() throws IOException, BadLocationException {
            ableToType(true);
            do {
                try {
                    lastReceivedMessage = String.valueOf(connection.getInput().readObject());
                    showMessage("\n" + lastReceivedMessage);

                } catch (ClassNotFoundException | BadLocationException classNotFoundException) {
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
            type = tof;
        }

        private void setStatus(String text) {
            chatWindow.setStatus(text);
        }
    }

    private class StartClient implements Runnable {

        @Override
        public void run() {
            try {
                connectToServer();

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

        private void setupStreams() throws IOException, BadLocationException {
            output = new ObjectOutputStream(connection.getOutput());
            output.flush();
            input = new ObjectInputStream(connection.getInput());
            showMessage("\nStreams are now good to go!");
        }

        private void whileChatting() throws IOException, BadLocationException {
            ableToType(true);
            do {
                try {
                    lastReceivedMessage = String.valueOf(connection.getInput().readObject());
                    showMessage("\n" + lastReceivedMessage);

                } catch (ClassNotFoundException | BadLocationException classNotFoundException) {
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
}
