package com.bank.GUI.Components;

import Classes.Enums.Enums;
import Common.ClientThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Center_NewClient extends JPanel {
    ClientThread clientThread;

    JPanel formPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    JLabel fullNameLabel = new JLabel("שם מלא");
    JLabel idLabel = new JLabel("ת.ז.");
    JLabel accountLabel = new JLabel("מספר חשבון");
    JLabel balanceLabel = new JLabel("יתרה");
    JLabel statusLabel = new JLabel("סטטוס");

    JTextField nameTextField = new JTextField(20);
    JTextField idTextField = new JTextField(20);
    JTextField accountTextField = new JTextField(20);
    JTextField balanceTextField = new JTextField(20);
    JTextField cardCodeTextField = new JTextField(10);
    JLabel cardCodeLabel = new JLabel("קוד כרטיס");
    JRadioButton statusActive=new JRadioButton("פעיל");
    JRadioButton statusDisabled=new JRadioButton("לא פעיל");
    ButtonGroup accountStatus =new ButtonGroup();

    JButton submit = new JButton("יצירת חשבון");

    Center_NewClient(){
        this.clientThread = clientThread;
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        accountStatus.add(statusActive);
        accountStatus.add(statusDisabled);
        gbc.ipady=10;
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(nameTextField, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(fullNameLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(idTextField, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(idLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(accountTextField, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(accountLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(balanceTextField, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(balanceLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(statusActive, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        formPanel.add(statusLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(statusDisabled, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(cardCodeTextField, gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        formPanel.add(cardCodeLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 8;
        formPanel.add(submit, gbc);
        this.add(formPanel);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("CreateAccount Button");
                try {
                    ClientThread.getInstance().createAccount(nameTextField.getText(),
                            Integer.parseInt(idTextField.getText()),
                            Integer.parseInt(accountTextField.getText()),
                            Float.parseFloat(balanceTextField.getText()),
                            Enums.Status.ACTIVE,
                            Integer.parseInt(cardCodeTextField.getText()));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });



    }
}
