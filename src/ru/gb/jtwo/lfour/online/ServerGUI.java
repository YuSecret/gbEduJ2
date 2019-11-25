package ru.gb.jtwo.lfour.online;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Event Dispatching Thread
//EventQueue-0
public class ServerGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler {

    private static final int POS_X = 1000;
    private static final int POS_Y = 550;
    private static final int WIDTH = 200;
    private static final int HEIGHT = 100;
    private final ChatServer server = new ChatServer();
    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ServerGUI();
            }
        });
    }

    private ServerGUI() {
        Thread.setDefaultUncaughtExceptionHandler(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setAlwaysOnTop(true);
        setLayout(new GridLayout(1, 2));
        btnStart.addActionListener(this);
        btnStop.addActionListener(this);
        add(btnStart);
        add(btnStop);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == btnStart) {
            server.start(8189);
        } else if (src == btnStop) {
            //server.stop();
            throw new RuntimeException("Hello From EDT");
        } else {
            throw new RuntimeException("Unknown source: " + src);
        }
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        String msg;
        StackTraceElement[] ste = e.getStackTrace();
        msg = e.getClass().getCanonicalName() + ": " +
                e.getMessage() + "\n\t" + ste[0];

        JOptionPane.showMessageDialog(this, msg, "Exception", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }
}
