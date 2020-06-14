package com.bank.GUI.Components;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Center extends JPanel {
    JTabbedPane tabs = new JTabbedPane();
    JPanel clients = new Center_Clients();
    JPanel dispose = new Center_Dispose();
    JPanel withdraw = new Center_Withdraw();
    JPanel transfer = new Center_Transfer();
    JPanel newClient = new Center_NewClient();
    public Center(){

        this.setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
        tabs.add(newClient, "לקוח חדש");
        tabs.add(transfer, "העברה");
        tabs.add(withdraw, "משיכה");
        tabs.add(dispose, "הפקדה");
        tabs.add(clients, "לקוחות");

        this.add(tabs);



    }
}
