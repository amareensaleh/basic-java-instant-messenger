package bjim.server;

import bjim.common.AbstractChatWindownew;

public class ServerChatWindow extends AbstractChatWindownew {

    public ServerChatWindow() {
        this("Server");
    }

    public ServerChatWindow(String username) {
        super(username);
    }

    public boolean isUserMessageVisible() {
        return userInput.isVisible();
    }
}
