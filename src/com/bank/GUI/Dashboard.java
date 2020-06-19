package com.bank.GUI;

import Classes.User;
import Common.ClientThread;
import com.bank.GUI.Components.Bottom;
import com.bank.GUI.Components.Center;
import com.bank.GUI.Components.State;
import com.bank.GUI.Components.Top;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Dashboard extends JFrame {
    private static Dashboard instance = null;
     static User currUser;


    JPanel mainPanel = new JPanel(new BorderLayout());
    public Dashboard() throws IOException {
        System.out.println("## DASHBOARD CONSTRUCT");
        this.currUser = State.getInstance().getLoggedUser();

        ClientThread.getInstance().getClients();

        this.add(new Top(currUser), BorderLayout.NORTH);
        this.add(new Center(), BorderLayout.CENTER);
        this.add(new Bottom(), BorderLayout.SOUTH);
        this.setPreferredSize(new Dimension(800, 600));
        this.setTitle("מסך ראשי");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
    public static Dashboard getInstance() throws IOException {
        if(instance==null){
            instance = new Dashboard();
        }
        return instance;
    }

}
