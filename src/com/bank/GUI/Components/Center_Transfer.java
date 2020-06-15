package com.bank.GUI.Components;

import Common.ClientThread;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Center_Transfer extends JPanel {
    JPanel top = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JLabel fromLabel = new JLabel("העברה מ");
    JLabel toLabel = new JLabel(" אל");
    JLabel amountLabel = new JLabel("סכום");
    JTextField fromField = new JTextField(10);
    JTextField toField = new JTextField(10);
    JTextField amountField = new JTextField(10);
    JButton transferButton = new JButton("העברה");
    ClientThread clientThread;

    Center_Transfer(ClientThread clientThread) throws IOException {
        this.clientThread = clientThread;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        top.add(amountField);
        top.add(amountLabel);
        top.add(toField);
        top.add(toLabel);
        top.add(fromField);
        top.add(fromLabel);
        this.add(top);
        this.add(ClientsTable.getInstance());
        this.add(transferButton);
    }
}
