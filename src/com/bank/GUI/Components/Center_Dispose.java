package com.bank.GUI.Components;

import Common.ClientThread;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Center_Dispose  extends JPanel {
    JLabel withdrawLabel = new JLabel("סכום להפקדה");
    JTextField withdrawTextField = new JTextField(10);
    JPanel top = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton withdrawButton = new JButton("הפקדה");
    ClientThread clientThread;
    Center_Dispose(ClientThread clientThread) throws IOException {
        this.setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
        this.setSize(new Dimension(600,500));
        top.add(withdrawTextField);
        top.add(withdrawLabel);
        this.add(top);
        this.add(ClientsTable.getInstance());
        this.add(withdrawButton);
    }
}
