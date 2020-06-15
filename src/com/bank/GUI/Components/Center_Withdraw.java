package com.bank.GUI.Components;

import Common.ClientThread;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.Flow;

public class Center_Withdraw extends JPanel {
    JLabel withdrawLabel = new JLabel("סכום למשיכה");
    JTextField withdrawTextField = new JTextField(10);
    JPanel top = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    JButton withdrawButton = new JButton("משיכה");
    ClientThread clientThread;
    Center_Withdraw(ClientThread clientThread) throws IOException {
        this.clientThread = clientThread;
        this.setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
        this.setSize(new Dimension(600,500));
        top.add(withdrawTextField);
        top.add(withdrawLabel);
        this.add(top);
        this.add(ClientsTable.getInstance());
        this.add(withdrawButton);
    }
}
