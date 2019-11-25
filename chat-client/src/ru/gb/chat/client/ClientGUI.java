package ru.gb.chat.client;

import ru.gb.chat.common.Library;
import ru.gb.network.SocketThread;
import ru.gb.network.SocketThreadListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;

/*
	 - Полностью разобраться с кодом урока
	 - Готовим вопросы по коду
	 Для тех, кто очень хочет попрограммировать и не придумал вопросов по коду:
	 - * Реализовать массовую рассылку сообщений с помощью Vector (он как ArrayList)
	 - * Автоматическое убирание верхней панели и появление нижней (и наоборот)
	 - * Реализовать кнопку Disconnect
	 И, чтобы не терять на уроке время, скачиваем SQLite и JDBC
	 https://sqlitestudio.pl/index.rvt
	 https://bitbucket.org/xerial/sqlite-jdbc/downloads/
	 На уроке будем устанавливать и настраивать этот инструментарий. Качаем самые свежие джарники-бинарники.

* */
public class ClientGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler, SocketThreadListener {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 300;
    private static final String STR_WIN_TITLE = "Chat Client";

    private final JTextArea log = new JTextArea();
    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField tfIPAddress = new JTextField("95.84.209.91");
    private final JTextField tfPort = new JTextField("8189");
    private final JCheckBox cbAlwaysOnTop = new JCheckBox("Always on top", true);
    private final JTextField tfLogin = new JTextField("ivan");
    private final JPasswordField tfPassword = new JPasswordField("123");
    private final JButton btnLogin = new JButton("Login");

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JButton btnDisconnect = new JButton("<html><b>Disconnect</b></html>");
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");

    private final JList<String> userList = new JList<>();
    private boolean shownIoErrors = false;
    private SocketThread socketThread;
    private final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss: ");
    private static final String[] EMPTY_USER_LIST = new String[0];

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientGUI();
            }
        });
    }

    private ClientGUI() {
        Thread.setDefaultUncaughtExceptionHandler(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle(STR_WIN_TITLE);
        setAlwaysOnTop(true);
        JScrollPane scrollLog = new JScrollPane(log);
        JScrollPane scrollUsers = new JScrollPane(userList);
        scrollUsers.setPreferredSize(new Dimension(100, 0));
        cbAlwaysOnTop.addActionListener(this);
        btnSend.addActionListener(this);
        tfMessage.addActionListener(this);
        btnLogin.addActionListener(this);
        btnDisconnect.addActionListener(this);

        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(cbAlwaysOnTop);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        panelBottom.setVisible(false);
        panelBottom.add(btnDisconnect, BorderLayout.WEST);
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);

        add(scrollLog, BorderLayout.CENTER);
        add(scrollUsers, BorderLayout.EAST);
        add(panelBottom, BorderLayout.SOUTH);
        add(panelTop, BorderLayout.NORTH);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == cbAlwaysOnTop) {
            setAlwaysOnTop(cbAlwaysOnTop.isSelected());
        } else if (src == btnSend || src == tfMessage) {
            sendMessage();
        } else if (src == btnLogin) {
            connect();
        } else if (src == btnDisconnect) {
            socketThread.close();
        } else {
            throw new RuntimeException("Unknown source: " + src);
        }
    }

    private void connect() {
        Socket socket = null;
        try {
            socket = new Socket(tfIPAddress.getText(), Integer.parseInt(tfPort.getText()));
        } catch (IOException e) {
            showException(e);
            return;
        }
        socketThread = new SocketThread(this, "Client", socket);
    }

    private void sendMessage() {
        String msg = tfMessage.getText();
        if ("".equals(msg)) return;
        tfMessage.setText(null);
        tfMessage.requestFocusInWindow();
        socketThread.sendMessage(Library.getClientBcast(msg));
    }

    private void wrtMsgToLogFile(String msg, String username) {
        try (FileWriter out = new FileWriter("log.txt", true)) {
            out.write(username + ": " + msg + "\n");
            out.flush();
        } catch (IOException e) {
            if (!shownIoErrors) {
                shownIoErrors = true;
                showException(e);
            }
        }
    }

    private void putLog(String msg) {
        if ("".equals(msg)) return;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                log.append(msg + "\n");
                log.setCaretPosition(log.getDocument().getLength());
            }
        });
    }


    @Override
    public void uncaughtException(Thread t, Throwable e) {
        showException(e);
        System.exit(1);
    }

    private void showException(Throwable e) {
        e.printStackTrace();
        String msg;
        StackTraceElement[] ste = e.getStackTrace();
        msg = e.getClass().getCanonicalName() + ": " +
                e.getMessage() + "\n\t" + ste[0];

//        JOptionPane.showMessageDialog(this, msg, "Exception", JOptionPane.ERROR_MESSAGE);
        putLog("Exception: " + msg);
    }

    @Override
    public void onSocketThreadStart(SocketThread thread, Socket socket) {
        putLog("Socket Thread Start");
    }

    @Override
    public void onSocketThreadStop(SocketThread thread) {
        putLog("Socket Thread Stop");
        setTitle(STR_WIN_TITLE);
        userList.setListData(EMPTY_USER_LIST);
        panelBottom.setVisible(false);
        panelTop.setVisible(true);
    }

    @Override
    public void onReceiveString(SocketThread thread, Socket socket, String msg) {
        handleReceivedMessage(msg);
    }

    @Override
    public void onSocketReady(SocketThread thread, Socket socket) {
        putLog("Client connected");
        panelTop.setVisible(false);
        panelBottom.setVisible(true);
        String login = tfLogin.getText();
        String password = new String(tfPassword.getPassword());
        thread.sendMessage(Library.getAuthRequest(login, password));
    }

    @Override
    public void onSocketThreadException(SocketThread thread, Exception e) {
        showException(e);
    }

    private void handleReceivedMessage(String msg) {
        String[] arr = msg.split(Library.DELIMITER);
        String msgType = arr[0];
        switch (msgType) {
            case Library.AUTH_ACCEPT:
                setTitle(STR_WIN_TITLE + " authorized as " + arr[1]);
                break;
            case Library.AUTH_DENIED:
                putLog("Invalid credentials");
                socketThread.close();
                break;
            case Library.MSG_FORMAT_ERROR:
                putLog("Invalid message format: " + msg);
                break;
            case Library.TYPE_BROADCAST:
                log.append(dateFormat.format(Long.parseLong(arr[1])) + ": " + arr[2] + ": " + arr[3] + "\n");
                log.setCaretPosition(log.getDocument().getLength());
                break;
            case Library.USER_LIST:
                // /user_list§user1§user2§user3§
                String users = msg.substring(Library.USER_LIST.length() +
                        Library.DELIMITER.length());
                String[] usersArr = users.split(Library.DELIMITER);
                Arrays.sort(usersArr);
                userList.setListData(usersArr);
                break;
            default:
                throw new RuntimeException("Unknown message type: " + msg);
        }

    }

}
