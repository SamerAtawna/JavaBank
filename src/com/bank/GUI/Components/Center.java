package com.bank.GUI.Components;

import Classes.Client;
import Classes.Enums.Enums;
import Common.ClientThread;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Center extends JPanel {
    JTabbedPane tabs = new JTabbedPane();

    public Center() throws IOException, InterruptedException {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        tabs.add(new Center_NewClient(), "לקוח חדש");
        tabs.add(new Center_Transfer(), "העברה");
        tabs.add(new Center_Withdraw(), "משיכה");
        tabs.add(new Center_Dispose(), "הפקדה");
        tabs.add(new Center_Clients(), "לקוחות");
        if (com.bank.GUI.Components.State.getInstance().getLoggedUser().getPermission().equals(Enums.Permission.ADMIN)) {
            tabs.add(new Center_Admin(), "ממשק ניהול");

        }
        ;

        this.add(tabs);

        tabs.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                System.out.println("Tab: " + tabs.getTitleAt(tabs.getSelectedIndex()));
                try {
                    //update current view
                    State.getInstance().setCurrentView(tabs.getTitleAt(tabs.getSelectedIndex()));
                    System.out.println(State.getInstance().getClients().size());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });


    }
}
