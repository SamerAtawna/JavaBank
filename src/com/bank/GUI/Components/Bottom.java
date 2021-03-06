package com.bank.GUI.Components;

import Classes.Transaction;
import Common.ClientThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Bottom extends JPanel {

    final ImageIcon icon = new ImageIcon ("src\\com\\bank\\GUI\\Assets\\trans.png");
    final ImageIcon icon2 = new ImageIcon ("src\\com\\bank\\GUI\\Assets\\calc.png");
    final ImageIcon icon3 = new ImageIcon ("src\\com\\bank\\GUI\\Assets\\pdf.png");
    JButton trans = new JButton("עו\"ש", icon);
    JButton calc = new JButton("מחשבון", icon2);
    JButton pdf = new JButton("יצא ל PDF", icon3);
    ExportToPDF export = new ExportToPDF();
    public Bottom() throws IOException {
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        trans.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ClientThread.getInstance().getTrans(State.getInstance().getTransClient());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                try {
                    Transactions ts = Transactions.getInstance();
                    ts.setVisible(true);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        this.add(trans);
        calc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    try {
                        Runtime.getRuntime().exec("calc");
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

            }
        });
        pdf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    export.export();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        this.add(calc);
        this.add(pdf);

    }
}
