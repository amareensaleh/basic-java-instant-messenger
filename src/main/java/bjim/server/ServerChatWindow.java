package bjim.server;

import bjim.common.AbstractChatWindownew;

import javax.swing.text.BadLocationException;
import java.io.IOException;

public class ServerChatWindow extends AbstractChatWindownew {

    public ServerChatWindow() throws IOException, BadLocationException {
        this("Server");
    }

    public ServerChatWindow(String username) throws IOException, BadLocationException {
        super(username);
    }

    public boolean isUserMessageVisible() {
        return userInput.isVisible();
    }

    public boolean isabletowrite() {
        return userInput.isVisible();
    }

    public boolean abletowrite() {
        userInput.setEditable(false);
        if (userInput.isEditable() == true) return true;
        else return false;
    }
}
