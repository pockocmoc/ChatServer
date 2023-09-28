package com.pockocmoc.server;

import javax.swing.*;
import java.awt.*;

public class ServerWindow extends JFrame {
    public static final int POS_X = 500;
    public static final int POS_Y = 550;
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    private ClientGUI clientGUI;

    private final JTextArea log = new JTextArea();

    public void addMessageToLog(String message) {
        log.append(message + "\n");
    }

    public static void main(String[] args) {
        new ServerWindow();
    }

    ServerWindow() {
        JButton btnStop = new JButton("Stop");
        btnStop.addActionListener(e -> {
            addMessageToLog("Server stopped " + false);
            if (clientGUI != null) {
                clientGUI.dispose();
            }
        });

        JButton btnStart = new JButton("Start");
        btnStart.addActionListener(e -> {
            addMessageToLog("Server started " + true);
            if (clientGUI == null) {
                clientGUI = new ClientGUI();
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setAlwaysOnTop(true);

        setLayout(new BorderLayout());
        add(log, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(btnStart);
        buttonPanel.add(btnStop);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

}
