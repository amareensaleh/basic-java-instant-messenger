package bjim;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import bjim.client.Client;

import javax.swing.text.BadLocationException;
import java.io.IOException;

public class ClientApplication {

    public static void main(String[] args) throws IOException, BadLocationException {
        Client client = new Client();
        client.setDefaultCloseOperation(EXIT_ON_CLOSE);
        client.startRunning();
    }
}
