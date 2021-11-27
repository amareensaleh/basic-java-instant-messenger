package bjim.client;

import bjim.common.AbstractChatWindownew;

import javax.swing.text.BadLocationException;
import java.io.IOException;

public class ClientChatWindow extends AbstractChatWindownew {

    public ClientChatWindow() throws IOException, BadLocationException {
        this("Client");
    }

    public ClientChatWindow(String username) throws IOException, BadLocationException {
        super(username);
    }
}
