package com.bank.GUI.Components.ATM;

import Classes.Client;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ATM extends JFrame {
    JLabel enterCode = new JLabel("<html><font size=30 >יש להכניס קוד</font></html>");
    JPanel atmPanel = new JPanel();
    JPanel moneyPanel = new JPanel();
    JTextField inputCode = new JTextField(4);
    JButton continueBtn = new JButton("המשך");
    GridBagConstraints gbc = new GridBagConstraints();
    GridBagConstraints gbc2 = new GridBagConstraints();
    JLabel serverMessage = new JLabel("");
    JButton oneHundred = new JButton("<html><font size=30 >100₪</font></html>");
    JButton twoHundred = new JButton("<html><font size=30 >200₪</font></html>");
    Client loggedClient;

    public void setServerMessage(String serverMessage) {
        this.serverMessage.setText(serverMessage);
        this.repaint();
    }

    ATM(){
        try {
            this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src\\com\\bank\\GUI\\Assets\\ATM.jpg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setLayout(new FlowLayout());
        atmPanel.setLayout(new GridBagLayout());
        atmPanel.setOpaque(false);
        moneyPanel.setOpaque(false);
        gbc.ipady = 10;
        gbc.insets = new Insets(10,10,10,10);
        gbc2.insets = new Insets(50,0,0,0);
        gbc.weighty = 4;
        gbc2.weighty = 4;
        gbc.gridx = 0;
        gbc.gridy = 0;
        atmPanel.add(enterCode, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        atmPanel.add(inputCode, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        atmPanel.add(continueBtn, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(serverMessage, BorderLayout.CENTER);
        moneyPanel.setLayout(new GridBagLayout());
        gbc2.weighty = 4;
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        moneyPanel.add(oneHundred, gbc2);
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        moneyPanel.add(twoHundred, gbc2);
        gbc2.gridx = 0;
        gbc2.gridy = 2;
        this.add(atmPanel);
        this.pack();
        this.setVisible(true);
        this.setPreferredSize(new Dimension(800, 600));
        this.setTitle("כספומט");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        continueBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ATM_Thread.getInstance().checkCard(Integer.parseInt(inputCode.getText()));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });

        oneHundred.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverMessage.setText("<html><font color=green size=30>נא להמתין</font></html>");
                try {
                    ATM_Thread.getInstance().withDraw(String.valueOf(loggedClient.getAccountNumber()),100);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        twoHundred.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverMessage.setText("<html><font color=green size=30>נא להמתין</font></html>");
                try {
                    ATM_Thread.getInstance().withDraw(String.valueOf(loggedClient.getAccountNumber()),200);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });


    }

    public void next(Client client){
        this.loggedClient = client;
        this.atmPanel.setVisible(false);
        this.serverMessage.setText("");
        this.add(moneyPanel);
        this.repaint();
    }

}
