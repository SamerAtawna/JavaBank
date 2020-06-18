package com.bank.GUI.Components;

import Common.ClientThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Center_Clients extends JPanel {

    ClientThread clientThread;
    JLabel numOfClientsLabel = new JLabel("מספר לקוחות");
    Integer numofClients = 0;
    JLabel clientsNum = new JLabel(numofClients.toString());
    JPanel numClientsPanel = new JPanel(new FlowLayout());
    JPanel searchPanel = new JPanel(new FlowLayout());
    JLabel searchLabel = new JLabel("חיפוש");
    JButton searchButton = new JButton("חפש");
    JPanel topPanel = new JPanel(new GridLayout(1,2));
    JTextField searchTextField = new JTextField(10);
    JPanel clientsTable = ClientsTable.getInstance();

    Center_Clients(ClientThread clientThread) throws IOException {
        this.clientThread = clientThread;
        this.setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
        numClientsPanel.add(numOfClientsLabel);
        numClientsPanel.add(clientsNum);
        searchPanel.add(searchButton);
        searchPanel.add(searchTextField);
        searchPanel.add(searchLabel);
        topPanel.add(numClientsPanel);
        topPanel.add(searchPanel);
        this.add(topPanel);

        this.add(clientsTable);
        this.clientsNum.setText(State.getInstance().getClientsNumber());
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                   System.out.println(State.getInstance().getClients().size());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }
}
