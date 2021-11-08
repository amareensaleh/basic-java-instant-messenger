package bjim.server;

import bjim.common.AbstractChatWindow;

public class ServerChatWindow extends AbstractChatWindow {

    public ServerChatWindow() {
        this("Server");
    }

    public ServerChatWindow(String username) {
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
        if(userInput.isEditable()==true)
            return true;
        else return false;
    }
    public String welcomemesaage()
    {chatText.setText("Waiting for someone to connect!");
        return chatText.getText();
    }


}
