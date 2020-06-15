package com.bank.GUI.Components;

import Classes.Client;
import Common.ClientThread;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Center extends JPanel {
    JTabbedPane tabs = new JTabbedPane();
    public Center(ClientThread clientThread) throws IOException {

        this.setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
        tabs.add(new Center_NewClient(clientThread), "לקוח חדש");
        tabs.add(new Center_Transfer(clientThread), "העברה");
        tabs.add(new Center_Withdraw(clientThread), "משיכה");
        tabs.add(new Center_Dispose(clientThread), "הפקדה");
        tabs.add(new Center_Clients(clientThread), "לקוחות");

        this.add(tabs);



    }
}
