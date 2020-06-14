package com.bank.GUI.Components;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;

public class Center_Withdraw extends JPanel {
    JLabel withdrawLabel = new JLabel("סכום למשיכה");
    JTextField withdrawTextField = new JTextField(10);
    JPanel top = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JPanel clients = new ClientsTable();
    JButton withdrawButton = new JButton("משיכה");

    Center_Withdraw(){
        this.setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
        this.setSize(new Dimension(600,500));
        top.add(withdrawTextField);
        top.add(withdrawLabel);
        this.add(top);
        this.add(clients);
        this.add(withdrawButton);
    }
}
