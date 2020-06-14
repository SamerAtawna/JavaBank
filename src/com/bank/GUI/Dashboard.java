package com.bank.GUI;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import com.bank.GUI.Components.Center;
import com.bank.GUI.Components.Top;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {
    JPanel mainPanel = new JPanel(new BorderLayout());
    JPanel top = new Top();
    JPanel center = new Center();
    Dashboard(){


        this.add(top, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(800, 600));
        this.setTitle("מסך ראשי");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception ignored){}
        //start your application
        new Dashboard();
    }
}
