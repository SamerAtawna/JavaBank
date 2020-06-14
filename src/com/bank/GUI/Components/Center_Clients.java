package com.bank.GUI.Components;

import javax.swing.*;
import java.awt.*;

public class Center_Clients extends JPanel {
    JLabel numOfClientsLabel = new JLabel("מספר לקוחות");
    Integer numofClients = 0;
    JLabel clientsNum = new JLabel(numofClients.toString());
    JPanel numClientsPanel = new JPanel(new FlowLayout());
    JPanel searchPanel = new JPanel(new FlowLayout());
    JLabel searchLabel = new JLabel("חיפוש");
    JButton searchButton = new JButton("חפש");
    JPanel topPanel = new JPanel(new GridLayout(1,2));
    JTextField searchTextField = new JTextField(10);
    JPanel clientsTable = new ClientsTable();

    Center_Clients() {
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
    }
}
