package com.bank.GUI.Components;

import Classes.User;

import javax.swing.*;
import java.awt.*;

public class Top extends JPanel {
    User currUser ;
    JPanel smallLogo = new LogoSmall();
    JPanel time = new JPanel();
    JPanel userPanel = new JPanel();
    JPanel userContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JLabel timeLabel = new JLabel("12:30");
    JLabel loggedUserLabel = new JLabel("משתמש נוכחי : ", SwingConstants.RIGHT);
    JLabel loggedUsername = new JLabel("samer ");
    JLabel timeLoginLabel = new JLabel("שעת כניסה :", SwingConstants.RIGHT);
    JLabel timeLogin = new JLabel("12:12 ");
    JLabel permissionLabel = new JLabel("הרשאה : ", SwingConstants.RIGHT);
    JLabel permission = new JLabel("משתמש מערכת ");
    GridBagConstraints gbc = new GridBagConstraints();

    public Top(User currUser){
        this.currUser = currUser;
        this.loggedUsername.setText(currUser.getUserName());
        this.permission.setText(this.currUser.getPermission().toString());

        userPanel.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        userPanel.add(loggedUsername, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        userPanel.add(loggedUserLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        userPanel.add(timeLogin, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        userPanel.add(timeLoginLabel,gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        userPanel.add(permission, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        userPanel.add(permissionLabel, gbc);
        userContainer.add(userPanel);
        time.add(timeLabel);
        this.setLayout(new GridLayout(1,3));
        this.add(smallLogo);
        this.add(time);
        this.add(userContainer);

        this.setPreferredSize(new Dimension(this.getWidth(),100));
    }
}
