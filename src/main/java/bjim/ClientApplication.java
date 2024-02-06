package bjim;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import bjim.client.Client;
import bjim.client.Client.Username;
import bjim.client.UsernameWindow;

public class ClientApplication {

    public static void main(String[] args) {

       int a;
       int b;

        UsernameWindow usernameWindow = new UsernameWindow();

        usernameWindow.setActionListener(
                event -> {
                    String username = usernameWindow.getUsername().strip();
                    if (username == null || username.isEmpty()) {
                        System.out.println("Username is empty");
                    } else {
                        Client client = new Client(Username.username(username));
                        client.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        client.startRunning();
                        usernameWindow.setVisible(false);
                    }
                });
    }
}
