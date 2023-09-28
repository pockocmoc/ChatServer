package com.pockocmoc.server;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class ClientGUI extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    private final JTextArea log = new JTextArea();

    private final JTextField tfMessage = new JTextField();


    ClientGUI() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat Client");

        JPanel panelTop = new JPanel(new GridLayout(2, 3));
        JTextField tfIPAddress = new JTextField("127.0.0.1");
        panelTop.add(tfIPAddress);
        JTextField tfPort = new JTextField("8189");
        panelTop.add(tfPort);
        JTextField tfLogin = new JTextField("ivan_igorevich");
        panelTop.add(tfLogin);
        JPasswordField tfPassword = new JPasswordField("123456");
        panelTop.add(tfPassword);
        JButton btnLogin = new JButton("Login");
        panelTop.add(btnLogin);
        add(panelTop, BorderLayout.NORTH);

        JPanel panelBottom = new JPanel(new BorderLayout());
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        JButton btnSend = new JButton("Send");
        panelBottom.add(btnSend, BorderLayout.EAST);
        add(panelBottom, BorderLayout.SOUTH);

        log.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(log);
        add(scrollLog);

        readLogFromFile();

        btnSend.addActionListener(e -> {
            String message = tfMessage.getText().trim();
            if (!message.isEmpty()) {
                log.append(message + "\n");
                writeLogToFile(message);
                tfMessage.setText("");
            }
        });

        setVisible(true);
    }

    private void readLogFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("chat_log.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                log.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeLogToFile(String message) {
        try (FileWriter writer = new FileWriter("chat_log.txt", true)) {
            writer.write(message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}