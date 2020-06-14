package com.bank.GUI.Components;

import javax.swing.*;
import java.awt.*;

public class Center_Dispose  extends JPanel {
    JLabel withdrawLabel = new JLabel("סכום להפקדה");
    JTextField withdrawTextField = new JTextField(10);
    JPanel top = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JPanel clients = new ClientsTable();
    JButton withdrawButton = new JButton("הפקדה");

    Center_Dispose(){
        this.setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
        this.setSize(new Dimension(600,500));
        top.add(withdrawTextField);
        top.add(withdrawLabel);
        this.add(top);
        this.add(clients);
        this.add(withdrawButton);
    }
}
