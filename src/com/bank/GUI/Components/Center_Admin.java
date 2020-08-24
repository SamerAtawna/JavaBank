package com.bank.GUI.Components;

import Common.ClientThread;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInput;
import java.io.IOException;

public class Center_Admin extends JPanel {
    TitledBorder blackline = BorderFactory.createTitledBorder("הקמת משתמש");
    TitledBorder deleteUserBorder = BorderFactory.createTitledBorder("מחיקת משתמש");

    ClientsTable_Helper clientTable = new ClientsTable_Helper();
    JPanel deleteUserPanel = new JPanel();
    String userPermission[] = {"Admin", "User"};
    JComboBox userPerm = new JComboBox(userPermission);
    JLabel permLabel = new JLabel("רמת הרשאה");

    JLabel createUserLabel = new JLabel("הקמת משתמש", SwingConstants.RIGHT);
    JLabel userNameLabel = new JLabel("שם משתמש", SwingConstants.RIGHT);
    JTextField userName = new JTextField();
    JTextField password = new JTextField();
    JLabel passLabel = new JLabel("סיסמא", SwingConstants.RIGHT);
    JButton createButton = new JButton("צור משתמש");
    JPanel createUserPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    JLabel space = new JLabel("");
    JTextField userToDelete = new JTextField();
    JButton delUserBtn = new JButton("מחק משתמש");
    Center_Admin() throws IOException {

        this.setLayout(new GridLayout(2, 2));
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        blackline.setTitleJustification(TitledBorder.RIGHT);
        deleteUserBorder.setTitleJustification(TitledBorder.RIGHT);
        createUserPanel.setPreferredSize(new Dimension(300, 200));
        createUserPanel.setBorder(blackline);
        userName.setColumns(10);
        password.setColumns(10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        createUserPanel.add(userName, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        createUserPanel.add(userNameLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        createUserPanel.add(password, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        createUserPanel.add(passLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        createUserPanel.add(userPerm, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        createUserPanel.add(permLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        createUserPanel.add(createButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        createUserPanel.add(space, gbc);
        userToDelete.setPreferredSize(new Dimension(100,30));
        deleteUserPanel.setBorder(deleteUserBorder);
        deleteUserPanel.setPreferredSize(new Dimension(300, 200));
        deleteUserPanel.add(userToDelete);
        deleteUserPanel.add(delUserBtn);
        this.add(createUserPanel);
        this.add(deleteUserPanel);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    ClientThread.getInstance().createUser(userName.getText(), password.getText(), userPerm.getSelectedItem().toString());


                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

    }
}
