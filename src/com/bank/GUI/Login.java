package com.bank.GUI;

import Common.ClientThread;
import com.bank.GUI.Components.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Login extends JFrame implements ActionListener {
    JPanel inputPanel = new JPanel(new GridLayout(4, 2));
    JPanel loginMainPanel = new JPanel(new GridLayout(2, 1));
    GridBagConstraints gbc = new GridBagConstraints();
    static ClientThread clientThread;

    JPanel imagePanel = new Logo();
    JButton loginButton = new JButton("כניסה");
    JButton exitButton = new JButton("יציאה");
    JTextField userName = new JTextField(10);
    JTextField password = new JTextField(10);

    JLabel userLabel = new JLabel("שם משתמש");
    JLabel passLabel = new JLabel("סיסמא");
    JLabel space = new JLabel("");




    public Login(ClientThread clientThread) throws IOException {
        State appState = State.getInstance();
        this.clientThread = clientThread;
        inputPanel.setLayout(new GridBagLayout());
        // set nimbus look and feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
            System.out.println("Failed theme");
        }

        loginButton.addActionListener(this);
        exitButton.addActionListener(this);

        space.setPreferredSize(new Dimension(50, 50));
        loginMainPanel.add(imagePanel);
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(userName, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        inputPanel.add(userLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(password, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(passLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(space, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(loginButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        inputPanel.add(exitButton, gbc);


        loginMainPanel.add(inputPanel);
        this.add(loginMainPanel);
        this.setPreferredSize(new Dimension(800, 600));
        this.setTitle("מסך כניסה");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {
        }
        new Login(clientThread);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = ((JButton) e.getSource()).getActionCommand();
        System.out.println(command);
        if(command.equals("כניסה")){
            System.out.println("login");
            try {
                clientThread.auth(this.userName.getText(), this.password.getText());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        if(command.equals("יציאה")){
            System.out.println("exit");
        }

    }
}
