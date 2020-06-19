package com.bank.GUI.Components;

import Common.ClientThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.Flow;
import java.util.stream.StreamSupport;

public class Center_Withdraw extends JPanel {
    JLabel withdrawLabel = new JLabel("סכום למשיכה");
    JTextField withdrawTextField = new JTextField(10);
    JPanel top = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton withdrawButton = new JButton("משיכה");
    JLabel clientLabel = new JLabel("עבור");
    JTextField clientTextField = new JTextField(10);
    ClientThread clientThread;
    Center_Withdraw() throws IOException {
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
        State.getInstance().withDrawRef = clientTextField;
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(clientTextField.getText().equals("") || withdrawTextField.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"יש לוודא את הנתונים","שגיאה", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    ClientThread.getInstance().withDraw(clientTextField.getText(),Float.parseFloat(withdrawTextField.getText()));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

    }
}
