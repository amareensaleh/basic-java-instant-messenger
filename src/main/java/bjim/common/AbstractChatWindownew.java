package bjim.common;

import static java.awt.BorderLayout.NORTH;
import static java.awt.Font.BOLD;
import static javax.swing.SwingUtilities.invokeLater;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTMLEditorKit.Parser;
import javax.swing.text.html.HTMLEditorKit.ParserCallback;
import javax.swing.text.html.StyleSheet;
import javax.swing.text.html.parser.DTD;
import javax.swing.text.html.parser.DocumentParser;

import java.util.*;

import bjim.client.Client;
import bjim.server.Server;


import lombok.Getter;
import lombok.SneakyThrows;


public class AbstractChatWindownew {
    public Server server;
    public Client client;
    private static final int FONT_SIZE = 18;
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 300;
    private static final String FONT_NAME = "Segoe Script";
    JPanel jpFileRow = new JPanel();



    public JFrame chatWindow;
    public JTextField userInput;

    public JLabel status;
    public JButton btnNewButton_7;
    //JTextPane pan = new JTextPane();
    public Style style;
    JTextField usernameChooser;
    JFrame preFrame;

    //public StyledDocument doc;
    //public  String username;
    public HTMLDocument doc1;

    public String msg;
    private JPanel contentPane;
    public JTextPane pan;
    private JTextPane txtDisplayMessage;
    private JButton btnSendFile;
    private JLabel lblReceive;
    private AbstractChatWindownew  frame = this;
    private JProgressBar progressBar;
    JButton btnSend;
    public File[] fileToSend;

    public ImageIcon image4;
    public HTMLEditorKit kit;

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Getter public  String username;


    public AbstractChatWindownew (String username) throws BadLocationException, IOException {


        this.username=username;

        preFrame = new JFrame("Choose your username!(Colt chat v0.1");
        preFrame.setBounds(450, 190, 1014, 597);
        usernameChooser = new JTextField();

        JLabel chooseUsernameLabel = new JLabel("Pick a username:");
        JButton enterServer = new JButton("Enter Chat Server");


        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        preFrame.setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewUserRegister = new JLabel("Welcome!!!");
        lblNewUserRegister.setFont(new Font("Times New Roman", Font.BOLD, 42));
        lblNewUserRegister.setBounds(362, 52, 325, 50);
        contentPane.add(lblNewUserRegister);

        JLabel lblName = new JLabel("Enter your name");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblName.setBounds(362, 152, 150, 43);
        contentPane.add(lblName);


        usernameChooser= new JTextField();
        usernameChooser.setFont(new Font("Tahoma", Font.PLAIN, 32));
        usernameChooser.setBounds(362, 230, 228, 50);
        contentPane.add(usernameChooser);
        usernameChooser.setColumns(10);

        enterServer.setFont(new Font("Tahoma", Font.PLAIN, 22));
        enterServer.setBounds(362, 350, 259, 74);
        contentPane.add(enterServer);

        enterServer.addActionListener(new AbstractChatWindownew .enterServerButtonListener());


        status = new JLabel("");



        Font font = new Font(FONT_NAME, BOLD, FONT_SIZE);
        chatWindow = new JFrame(username);
        chatWindow.setResizable(false);
        chatWindow.setTitle("Chat Frame");
        chatWindow.setBounds(100, 100, 576, 595);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        chatWindow.setContentPane(contentPane);
        contentPane.setLayout(null);


        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setBounds(0, 0, 573, 67);
        contentPane.add(panel);
        panel.setLayout(null);

        ImageIcon image2 = new ImageIcon("image/profile.png");
        JLabel lblNewLabel = new JLabel(image2);
        lblNewLabel.setBounds(20, 0, 66, 67);
        panel.add(lblNewLabel);

        JPanel panel_6 = new JPanel();


        String bodyRule = "body { font-family: " + FONT_NAME + "; " +
                "font-size: " + FONT_SIZE + "pt; }";


        pan = new JTextPane();
        kit = new HTMLEditorKit();
        doc1 = new HTMLDocument();
        Font font1 = new Font("Serif", Font.ITALIC, 18);
        pan.setFont(font1);
        pan.setEditorKit(kit);
        pan.setDocument(doc1);
        ((HTMLDocument)pan.getDocument()).getStyleSheet().addRule(bodyRule);

        panel_6.setBounds(0, 66, 562, 323);
        panel_6.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(pan);
        scrollPane.setBounds(0, 0, 562, 323);
        panel_6.add(scrollPane);
        contentPane.add(panel_6);

        JPanel panel_2 = new JPanel();
        panel_2.setBounds(0, 372, 573, 73);
        contentPane.add(panel_2);
        panel_2.setLayout(null);


        ImageIcon image3 = new ImageIcon("image/crying.png");
        JButton btnNewButton_2 = new JButton(image3);
        btnNewButton_2.setBounds(31, 22, 44, 41);
        btnNewButton_2.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNewButton_2.setContentAreaFilled(false);
        panel_2.add(btnNewButton_2);

        btnNewButton_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                userInput.setText(":'(");


            }
        });


        image4 = new ImageIcon("image/heart_eye.png");
        JButton btnNewButton_4 = new JButton(image4);
        btnNewButton_4.setBounds(31*3, 22, 44, 41);
        btnNewButton_4.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNewButton_4.setContentAreaFilled(false);
        panel_2.add(btnNewButton_4);

        btnNewButton_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                userInput.setText("<3)");

            }
        });


        ImageIcon imagesmile = new ImageIcon("image/smile_big.png");
        JButton btnNewButton_8 = new JButton(imagesmile);

        btnNewButton_8.setBounds(31*5, 22, 44, 41);
        btnNewButton_8.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNewButton_8.setContentAreaFilled(false);
        panel_2.add(btnNewButton_8);

        btnNewButton_8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                userInput.setText(":D");


            }
        });




        ImageIcon image5 = new ImageIcon("image/sad.png");
        JButton btnNewButton_5 = new JButton(image5);

        btnNewButton_5.setBounds(31*7, 22, 44, 41);
        btnNewButton_5.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNewButton_5.setContentAreaFilled(false);
        panel_2.add(btnNewButton_5);


        btnNewButton_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                userInput.setText(":(");

            }
        });

        ImageIcon image6 = new ImageIcon("image/angry.png");
        JButton btnNewButton_6 = new JButton(image6);
        btnNewButton_6.setBounds(31 * 9, 22, 44, 41);
        btnNewButton_6.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNewButton_6.setContentAreaFilled(false);
        panel_2.add(btnNewButton_6);

        btnNewButton_6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                userInput.setText("o.O");


            }
        });






        ImageIcon image7 = new ImageIcon("image/smile_cry.png");
        JButton btnNewButton_7 = new JButton(image7);
        btnNewButton_7.setBounds(31*11, 22, 44, 41);
        btnNewButton_7.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNewButton_7.setContentAreaFilled(false);
        btnNewButton_7.setText("\uD83C\uDFBC");
        panel_2.add(btnNewButton_7);

        btnNewButton_7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                userInput.setText(":'D");


            }
        });

        progressBar = new JProgressBar();
        progressBar.setBounds(10, 22, 540, 41);
        progressBar.setVisible(false);
        panel_2.add(progressBar);

        JPanel panel_3 = new JPanel();
        panel_3.setBounds(0, 446, 562, 73);
        contentPane.add(panel_3);
        panel_3.setLayout(null);


        ImageIcon image8 = new ImageIcon("image/send.png");
        btnSend = new JButton(image8);
        btnSend.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnSend.setContentAreaFilled(false);
        btnSend.setBounds(498, 5, 64, 64);
        panel_3.add(btnSend);

        ImageIcon image9 = new ImageIcon("image/document.png");
        btnSendFile = new JButton(image9);
        btnSendFile.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnSendFile.setContentAreaFilled(false);

        btnSendFile.setBounds(440, 10, 64, 53);
        panel_3.add(btnSendFile);

        btnSendFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser jFileChooser = new JFileChooser();

                jFileChooser.setDialogTitle("Choose a file to send.");

                if (jFileChooser.showOpenDialog(null)  == JFileChooser.APPROVE_OPTION) {

                    fileToSend[0] = jFileChooser.getSelectedFile();

                    userInput.setText( fileToSend[0].getName());
                }
            }
        });





        userInput = new JTextField();
        userInput.setBounds(0, 5, 433, 58);
        panel_3.add(userInput);
        userInput.setColumns(10);



        if(username.equals("Client"))
        {preFrame.setVisible(true);
            chatWindow.setVisible(false);
        }
        else
        {
            preFrame.setVisible(false);
            chatWindow.setVisible(true);
        }


    }


    class enterServerButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {


            username = usernameChooser.getText();

            if (username.length() <= 1)
            {System.out.println("No!"); }

            else {


                preFrame.setVisible(false);
                chatWindow.setVisible(true);
            }


        }

    }






    public void setStatus(String statusText) {
        status.setText(statusText);

    }


    public void onSend(ActionListener actionListener) {
        userInput.addActionListener(
                e -> {
                    actionListener.actionPerformed(e);
                    userInput.setText("");
                });




    }
    public String emoji()
    {
        return msg;
    }


    public void append(String s) {
        pan.setText(s);
    }

    public boolean isVisible() {
        return chatWindow.isVisible();
    }





    public void showMessage(final String text) throws BadLocationException {
        String lasttwodig=text.substring(text.length()-2);
        String lastfourdig=text.substring(text.length()-4);



        if(lasttwodig.equals("sm")) {
            String message=text.substring(0,text.length()-2);
            invokeLater(() -> {
                try {

                    doc1.insertString(doc1.getLength(),message, style);


                    String Path = "C:/image/emo.gif";
                    kit.insertHTML(doc1, doc1.getLength(),"<img src=\"file:\\"+Path+"\" alt=\"some_text\" width=\"100\" height=\"100\">", 0, 0, HTML.Tag.IMG);

                } catch (BadLocationException | IOException e) {
                    e.printStackTrace();
                }

            });
        }


        else  if(lasttwodig.equals("cr")) {
            String message=text.substring(0,text.length()-2);
            invokeLater(() -> {
                try {

                    doc1.insertString(doc1.getLength(),message, style);


                    String Path = "C:/image/cry1.gif";
                    kit.insertHTML(doc1, doc1.getLength(),"<img src=\"file:\\"+Path+"\" alt=\"some_text\" width=\"100\" height=\"100\">", 0, 0, HTML.Tag.IMG);

                } catch (BadLocationException | IOException e) {
                    e.printStackTrace();
                }

            });
        }
        else  if(lasttwodig.equals("hr")) {
            String message=text.substring(0,text.length()-2);
            invokeLater(() -> {
                try {

                    doc1.insertString(doc1.getLength(),message, style);


                    String Path = "image/heart_eye.gif";
                    kit.insertHTML(doc1, doc1.getLength(),"<img src=\"file:\\"+Path+"\" alt=\"some_text\" width=\"100\" height=\"100\">", 0, 0, HTML.Tag.IMG);

                } catch (BadLocationException | IOException e) {
                    e.printStackTrace();
                }

            });
        }



        else  if(lasttwodig.equals("sd")) {
            String message=text.substring(0,text.length()-2);
            invokeLater(() -> {
                try {

                    doc1.insertString(doc1.getLength(),message, style);


                    String Path = "image/sad.gif";
                    kit.insertHTML(doc1, doc1.getLength(),"<img src=\"file:\\"+Path+"\" alt=\"some_text\" width=\"100\" height=\"100\">", 0, 0, HTML.Tag.IMG);

                } catch (BadLocationException | IOException e) {
                    e.printStackTrace();
                }

            });
        }
        else  if(lasttwodig.equals("ag")) {
            String message=text.substring(0,text.length()-2);
            invokeLater(() -> {
                try {

                    doc1.insertString(doc1.getLength(),message, style);


                    String Path = "image/angry.gif";
                    kit.insertHTML(doc1, doc1.getLength(),"<img src=\"file:\\"+Path+"\" alt=\"some_text\" width=\"100\" height=\"100\">", 0, 0, HTML.Tag.IMG);

                } catch (BadLocationException | IOException e) {
                    e.printStackTrace();
                }

            });
        }

        else  if(lasttwodig.equals("sc")) {
            String message=text.substring(0,text.length()-2);
            invokeLater(() -> {
                try {

                    doc1.insertString(doc1.getLength(),message, style);


                    String Path = "image/smile_cry.gif";
                    kit.insertHTML(doc1, doc1.getLength(),"<img src=\"file:\\"+Path+"\" alt=\"some_text\" width=\"100\" height=\"100\">", 0, 0, HTML.Tag.IMG);

                } catch (BadLocationException | IOException e) {
                    e.printStackTrace();
                }

            });
        }







        else
        {
            invokeLater(() -> {
                try {

                    doc1.insertString(doc1.getLength(), text, style);

                    String Path = "image/emo.gif";

                } catch (BadLocationException e) {
                    e.printStackTrace();
                }

            });

        }

    }





    public void ableToType(final boolean tof) {
        invokeLater(() -> userInput.setEditable(tof));
    }

    public void setDefaultCloseOperation(int exitOnClose) {
        chatWindow.setDefaultCloseOperation(exitOnClose);
    }
}
