package bjim.common;

import static java.awt.Font.BOLD;
import static javax.swing.SwingUtilities.invokeLater;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import lombok.Getter;

public class AbstractChatWindownew {

    private static final int FONT_SIZE = 18;
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 300;
    private static final String FONT_NAME = "Segoe Script";

    public JFrame chatWindow;
    public JTextField userInput;
    public JTextArea chatText;
    public JLabel status;

    JTextField usernameChooser;
    JFrame preFrame;
    private Label l1 = new Label("Welcome to ");
    private Label l2 = new Label("Blondie Bytes");
    // public  String username;

    private JPanel contentPane;

    private JTextPane txtDisplayMessage;
    private JButton btnSendFile;
    private JLabel lblReceive;
    private AbstractChatWindownew frame = this;
    private JProgressBar progressBar;
    JButton btnSend;

    // distinct users
    HashMap<String, Integer> map = new HashMap<>();
    int count_user = 1;

    @Getter public String username;

    public AbstractChatWindownew(String username) {

        this.username = username;

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

        usernameChooser = new JTextField();
        usernameChooser.setFont(new Font("Tahoma", Font.PLAIN, 32));
        usernameChooser.setBounds(362, 230, 228, 50);
        contentPane.add(usernameChooser);
        usernameChooser.setColumns(10);

        enterServer.setFont(new Font("Tahoma", Font.PLAIN, 22));
        enterServer.setBounds(362, 350, 259, 74);
        contentPane.add(enterServer);

        enterServer.addActionListener(new AbstractChatWindownew.enterServerButtonListener());

        status = new JLabel("");


        chatWindow = new JFrame(username);
        chatWindow.setResizable(false);

        chatWindow.setTitle("Chat Fr");

        //chatWindow.setTitle(usernameChooser.getText());

        //chatWindow.setTitle(username);
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

        ImageIcon image2 = new ImageIcon("/Users/amarees/IdeaProjects/basic-java-instant-messenger/src/main/resources/image/profile.png");
        JLabel lblNewLabel = new JLabel(image2);
        lblNewLabel.setBounds(20, 0, 66, 67);
        panel.add(lblNewLabel);

        JPanel panel_6 = new JPanel();

        Font font = new Font(FONT_NAME, BOLD, FONT_SIZE);
        chatText = new JTextArea();
        chatText.setEditable(false);

        chatText.setFont(font);

        panel_6.setBounds(0, 66, 562, 323);
        panel_6.setLayout(null);
        JScrollPane scrollPane = new JScrollPane(chatText);
        scrollPane.setBounds(0, 0, 562, 323);
        panel_6.add(scrollPane);
        contentPane.add(panel_6);

        JPanel panel_2 = new JPanel();
        panel_2.setBounds(0, 372, 573, 73);
        contentPane.add(panel_2);
        panel_2.setLayout(null);

        ImageIcon image3 = new ImageIcon("/Users/amarees/IdeaProjects/basic-java-instant-messenger/src/main/resources/image/crying.png");
        JButton btnNewButton_2 = new JButton(image3);
        btnNewButton_2.setBounds(31, 22, 44, 41);
        btnNewButton_2.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNewButton_2.setContentAreaFilled(false);
        panel_2.add(btnNewButton_2);

        ImageIcon image4 = new ImageIcon("/Users/amarees/IdeaProjects/basic-java-instant-messenger/src/main/resources/image/heart_eye.png");
        JButton btnNewButton_4 = new JButton(image4);
        btnNewButton_4.setBounds(120, 22, 44, 41);
        btnNewButton_4.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNewButton_4.setContentAreaFilled(false);
        panel_2.add(btnNewButton_4);

        ImageIcon image5 = new ImageIcon("/Users/amarees/IdeaProjects/basic-java-instant-messenger/src/main/resources/image/sad.png");
        JButton btnNewButton_5 = new JButton(image5);

        btnNewButton_5.setBounds(265, 22, 44, 41);
        btnNewButton_5.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNewButton_5.setContentAreaFilled(false);
        panel_2.add(btnNewButton_5);

        ImageIcon image6 = new ImageIcon("/Users/amarees/IdeaProjects/basic-java-instant-messenger/src/main/resources/image/crying.png");
        JButton btnNewButton_6 = new JButton(image6);
        btnNewButton_6.setBounds(31 * 4, 22, 44, 41);
        btnNewButton_6.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNewButton_6.setContentAreaFilled(false);
        panel_2.add(btnNewButton_2);

        ImageIcon image7 = new ImageIcon("/Users/amarees/IdeaProjects/basic-java-instant-messenger/src/main/resources/image/scared.png");
        JButton btnNewButton_7 = new JButton(image7);
        btnNewButton_7.setBounds(378, 22, 44, 41);
        btnNewButton_7.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNewButton_7.setContentAreaFilled(false);
        panel_2.add(btnNewButton_7);

        progressBar = new JProgressBar();
        progressBar.setBounds(10, 22, 540, 41);
        progressBar.setVisible(false);
        panel_2.add(progressBar);

        JPanel panel_3 = new JPanel();
        panel_3.setBounds(0, 446, 562, 73);
        contentPane.add(panel_3);
        panel_3.setLayout(null);

        ImageIcon image8 = new ImageIcon("/Users/amarees/IdeaProjects/basic-java-instant-messenger/src/main/resources/image/send.png");
        btnSend = new JButton(image8);
        btnSend.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnSend.setContentAreaFilled(false);
        btnSend.setBounds(498, 5, 64, 64);
        panel_3.add(btnSend);

        ImageIcon image9 = new ImageIcon("/Users/amarees/IdeaProjects/basic-java-instant-messenger/src/main/resources/image/document.png");
        btnSendFile = new JButton(image9);
        btnSendFile.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnSendFile.setContentAreaFilled(false);

        btnSendFile.setBounds(440, 10, 64, 53);
        panel_3.add(btnSendFile);

        userInput = new JTextField();
        userInput.setBounds(0, 5, 433, 58);
        panel_3.add(userInput);
        userInput.setColumns(10);

        // chatWindow.setVisible(false);
        if (username == "Client") {
            preFrame.setVisible(true);
            chatWindow.setVisible(false);
        } else {
            preFrame.setVisible(false);
            chatWindow.setVisible(true);
        }
    }

    class enterServerButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            username = usernameChooser.getText();

            if (map.containsKey(username)) {
                System.out.println("This key contains");
            } else {
                map.put(username, count_user + 1);
            }

            if (username.length() <= 1) {
                System.out.println("No!");
            } else {

                for (String i : map.keySet()) {
                    System.out.println("item" + i);
                }
                preFrame.setVisible(false);
                chatWindow.setTitle(username);
                chatWindow.setVisible(true);
            }
        }
    }

    public void chatbox() {
        chatWindow = new JFrame(username);
        chatWindow.setResizable(false);
        chatWindow.setTitle("Chat mmmm");
       // chatWindow.setTitle(usernameChooser.getText());
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

        ImageIcon image2 = new ImageIcon("/Users/amarees/IdeaProjects/basic-java-instant-messenger/src/main/resources/image/profile.png");
        JLabel lblNewLabel = new JLabel(image2);
        lblNewLabel.setBounds(20, 0, 66, 67);
        panel.add(lblNewLabel);

        JPanel panel_6 = new JPanel();

        Font font = new Font(FONT_NAME, BOLD, FONT_SIZE);
        chatText = new JTextArea();
        chatText.setEditable(false);

        chatText.setBackground(Color.WHITE);
        chatText.setForeground(Color.WHITE);
        chatText.setFont(font);

        panel_6.setBounds(0, 66, 562, 323);
        panel_6.setLayout(null);
        JScrollPane scrollPane = new JScrollPane(chatText);
        scrollPane.setBounds(0, 0, 562, 323);
        panel_6.add(scrollPane);
        contentPane.add(panel_6);

        JPanel panel_2 = new JPanel();
        panel_2.setBounds(0, 372, 573, 73);
        contentPane.add(panel_2);
        panel_2.setLayout(null);

        ImageIcon image3 = new ImageIcon("/Users/amarees/IdeaProjects/basic-java-instant-messenger/src/main/resources/image/crying.png");
        JButton btnNewButton_2 = new JButton(image3);
        btnNewButton_2.setBounds(31, 22, 44, 41);
        btnNewButton_2.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNewButton_2.setContentAreaFilled(false);
        panel_2.add(btnNewButton_2);

        ImageIcon image4 = new ImageIcon("/Users/amarees/IdeaProjects/basic-java-instant-messenger/src/main/resources/image/heart_eye.png");
        JButton btnNewButton_4 = new JButton(image4);
        btnNewButton_4.setBounds(120, 22, 44, 41);
        btnNewButton_4.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNewButton_4.setContentAreaFilled(false);
        panel_2.add(btnNewButton_4);

        ImageIcon image5 = new ImageIcon("/Users/amarees/IdeaProjects/basic-java-instant-messenger/src/main/resources/image/sad.png");
        JButton btnNewButton_5 = new JButton(image5);

        btnNewButton_5.setBounds(265, 22, 44, 41);
        btnNewButton_5.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNewButton_5.setContentAreaFilled(false);
        panel_2.add(btnNewButton_5);

        ImageIcon image6 = new ImageIcon("/Users/amarees/IdeaProjects/basic-java-instant-messenger/src/main/resources/image/crying.png");
        JButton btnNewButton_6 = new JButton(image6);
        btnNewButton_6.setBounds(31 * 4, 22, 44, 41);
        btnNewButton_6.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNewButton_6.setContentAreaFilled(false);
        panel_2.add(btnNewButton_2);

        ImageIcon image7 = new ImageIcon("/Users/amarees/IdeaProjects/basic-java-instant-messenger/src/main/resources/image/scared.png");
        JButton btnNewButton_7 = new JButton(image7);
        btnNewButton_7.setBounds(378, 22, 44, 41);
        btnNewButton_7.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNewButton_7.setContentAreaFilled(false);
        panel_2.add(btnNewButton_7);

        progressBar = new JProgressBar();
        progressBar.setBounds(10, 22, 540, 41);
        progressBar.setVisible(false);
        panel_2.add(progressBar);

        JPanel panel_3 = new JPanel();
        panel_3.setBounds(0, 446, 562, 73);
        contentPane.add(panel_3);
        panel_3.setLayout(null);

        ImageIcon image8 = new ImageIcon("/Users/amarees/IdeaProjects/basic-java-instant-messenger/src/main/resources/image/send.png");
        btnSend = new JButton(image8);
        btnSend.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnSend.setContentAreaFilled(false);
        btnSend.setBounds(498, 5, 64, 64);
        panel_3.add(btnSend);

        ImageIcon image9 = new ImageIcon("/Users/amarees/IdeaProjects/basic-java-instant-messenger/src/main/resources/image/document.png");
        btnSendFile = new JButton(image9);
        btnSendFile.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnSendFile.setContentAreaFilled(false);

        btnSendFile.setBounds(440, 10, 64, 53);
        panel_3.add(btnSendFile);

        userInput = new JTextField();
        userInput.setBounds(0, 5, 433, 58);
        panel_3.add(userInput);
        userInput.setColumns(10);
        chatWindow.setVisible(true);
    }

    private JTextArea createChatText() {
        JTextArea textArea = new JTextArea();
        Font font = new Font(FONT_NAME, BOLD, FONT_SIZE);
        textArea.setFont(font);
        textArea.setEditable(false);
        return textArea;
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

    public void append(String s) {
        chatText.append(s);
    }

    public boolean isVisible() {
        return chatWindow.isVisible();
    }

    public void showMessage(final String text) {
        invokeLater(() -> chatText.append(text));
        invokeLater(() -> chatText.append("\n___________________________________"));
    }

    public void ableToType(final boolean tof) {
        invokeLater(() -> userInput.setEditable(tof));
    }

    public void setDefaultCloseOperation(int exitOnClose) {
        chatWindow.setDefaultCloseOperation(exitOnClose);
    }
}