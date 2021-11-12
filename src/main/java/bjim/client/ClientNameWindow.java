package bjim.client;
import bjim.common.AbstractChatWindow;
import bjim.common.AbstractWindowForClientName;

import javax.swing.*;
import java.awt.*;
public class ClientNameWindow  extends AbstractWindowForClientName{


    AbstractWindowForClientName a= new AbstractWindowForClientName();
    String usernameget=null;
    public ClientNameWindow()
    {
        setTitle("Instant Messenger");
        setVisible(true);
        setBounds(10, 10, 370, 600);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // usernameget=getkey();

    }


   public ClientNameWindow(String username) {
       super();
       // super();
   }
}
