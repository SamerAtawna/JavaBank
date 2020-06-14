package com.bank.GUI.Components;

import javax.swing.*;
import java.awt.*;

public class Center_Transfer extends JPanel {
    JPanel top = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JLabel fromLabel =  new JLabel("העברה מ");
    JLabel toLabel =  new JLabel(" אל");
    JLabel amountLabel = new JLabel("סכום");
    JPanel clientsList = new ClientsTable();
    JTextField fromField = new JTextField(10);
    JTextField toField = new JTextField(10);
    JTextField amountField = new JTextField(10);
    JButton transferButton = new JButton("העברה");
    Center_Transfer(){
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        top.add(amountField);
        top.add(amountLabel);
        top.add(toField);
        top.add(toLabel);
        top.add(fromField);
        top.add(fromLabel);
        this.add(top);
        this.add(clientsList);
        this.add(transferButton);
    }
}
