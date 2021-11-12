package bjim.client;

import bjim.common.AbstractChatWindow;
import bjim.common.AbstractWindowForClientName;
public class ClientChatWindow extends AbstractChatWindow {
    AbstractWindowForClientName a=new AbstractWindowForClientName();
    String jj= a.getkey();



    public ClientChatWindow() {
        this("jjk");
    }

    public ClientChatWindow(String username) {
        super(username);
   }


}
