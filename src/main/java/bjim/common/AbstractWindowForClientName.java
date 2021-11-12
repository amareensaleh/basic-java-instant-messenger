package bjim.common;


import bjim.client.Client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AbstractWindowForClientName extends JFrame implements ActionListener {
    Container container = getContentPane();
    JLabel userLabel = new JLabel("Client Name");
   // JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    //JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("Connect");
    //JButton resetButton = new JButton("RESET");
   // JCheckBox showPassword = new JCheckBox("Show Password");

    public AbstractWindowForClientName()
    {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }


    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        userLabel.setBounds(50, 150, 100, 30);
        //passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        //passwordField.setBounds(150, 220, 150, 30);
       // showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
      //  resetButton.setBounds(200, 300, 100, 30);


    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        //container.add(passwordLabel);
        container.add(userTextField);
        //container.add(passwordField);
        //container.add(showPassword);
        container.add(loginButton);
        //container.add(resetButton);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
       // resetButton.addActionListener(this);
        //showPassword.addActionListener(this);
    }


   // @Override


    String userText= null;
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGIN button
        if (e.getSource() == loginButton) {

            String pwdText;
            userText = userTextField.getText();

            System.out.println(userText);


           // Client client = new Client(userText, 0);
            //pwdText = passwordField.getText();
            /*if (userText.equalsIgnoreCase("mehtab") && pwdText.equalsIgnoreCase("12345")) {
                JOptionPane.showMessageDialog(this, "Login Successful");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }*/

        }
    }



    public String getkey()
    {
        userText = userTextField.getText();
        return userText;
    }
        //Coding Part of RESET button
        /*
        if (e.getSource() == resetButton) {
            userTextField.setText("");
            //passwordField.setText("");
        }*/
        //Coding Part of showPassword JCheckBox
        /*
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }


        }*/




}
