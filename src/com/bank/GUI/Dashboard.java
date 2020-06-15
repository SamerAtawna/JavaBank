package com.bank.GUI;

import Classes.User;
import Common.ClientThread;
import com.bank.GUI.Components.Center;
import com.bank.GUI.Components.Top;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Dashboard extends JFrame {
    private static Dashboard instance = null;
     static User currUser;
     static ClientThread clientThread;

    JPanel mainPanel = new JPanel(new BorderLayout());
    public Dashboard(User currUser, ClientThread clientThread) throws IOException {

        this.currUser = currUser;
        this.clientThread = clientThread;
        this.clientThread.getClients();

        this.add(new Top(currUser), BorderLayout.NORTH);
        this.add(new Center(clientThread), BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(800, 600));
        this.setTitle("מסך ראשי");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
    public static Dashboard getInstance() throws IOException {
        if(instance==null){
            instance = new Dashboard(currUser, clientThread);
        }
        return instance;
    }

}
