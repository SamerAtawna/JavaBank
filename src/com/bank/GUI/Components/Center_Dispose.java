package com.bank.GUI.Components;

import Common.ClientThread;
import Common.ServerConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Center_Dispose  extends JPanel {
    JLabel withdrawLabel = new JLabel("סכום להפקדה");
    JTextField withdrawTextField = new JTextField(10);
    JPanel top = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton withdrawButton = new JButton("הפקדה");
    JLabel clientLabel = new JLabel("עבור");
    JTextField clientTextField = new JTextField(10);
    ClientThread clientThread;
    Center_Dispose(ClientThread clientThread) throws IOException {
        this.setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
        this.setSize(new Dimension(600,500));
        top.add(withdrawTextField);
        top.add(withdrawLabel);
        top.add(clientTextField);
        top.add(clientLabel);
        this.add(top);
        this.add(new ClientsTable_Helper());
        this.add(withdrawButton);
//        State.getInstance().setDisposeToRef(clientTextField);
        State.getInstance().disposeToRef = clientTextField;
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ClientThread.getInstance().dispose(clientTextField.getText(),Float.parseFloat(withdrawTextField.getText()));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

    }
}
