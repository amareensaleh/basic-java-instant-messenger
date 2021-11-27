package bjim;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import bjim.server.Server;

import javax.swing.text.BadLocationException;
import java.io.IOException;

public class ServerApplication {

    public static void main(String[] args) throws InterruptedException, IOException, BadLocationException {
        Server server = new Server();
        server.setDefaultCloseOperation(EXIT_ON_CLOSE);
        server.startRunning();
    }
}
