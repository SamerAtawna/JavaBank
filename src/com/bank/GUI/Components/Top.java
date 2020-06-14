package com.bank.GUI.Components;

import javax.swing.*;
import java.awt.*;

public class Top extends JPanel {
    JPanel smallLogo = new LogoSmall();
    JPanel time = new JPanel();
    JPanel userPanel = new JPanel();
    JPanel userContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JLabel timeLabel = new JLabel("12:30");
    JLabel loggedUserLabel = new JLabel("משתמש נוכחי : ");
    JLabel loggedUsername = new JLabel("samer ");
    JLabel timeLoginLabel = new JLabel("שעת כניסה :");
    JLabel timeLogin = new JLabel("12:12 ");
    GridBagConstraints gbc = new GridBagConstraints();

    public Top(){
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
        userContainer.add(userPanel);
        time.add(timeLabel);
        this.setLayout(new GridLayout(1,3));
        this.add(smallLogo);
        this.add(time);
        this.add(userContainer);

        this.setPreferredSize(new Dimension(this.getWidth(),100));
    }
}
