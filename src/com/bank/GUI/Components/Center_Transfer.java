package com.bank.GUI.Components;

import Common.ClientThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Center_Transfer extends JPanel {
    private static Center_Transfer instance = null;
    JPanel top = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    String clientsList[] = new String[100];
    JLabel fromLabel = new JLabel("העברה מ");
    JLabel toLabel = new JLabel(" אל");
    JLabel amountLabel = new JLabel("סכום");
    JTextField fromField = new JTextField(10);
    JTextField toField = new JTextField(10);
    JTextField amountField = new JTextField(10);
    JComboBox fromDropDown = new JComboBox();
    JButton transferButton = new JButton("העברה");
    ClientThread clientThread;
    int index = 0;
    int clientCount = State.getInstance().getClients().size();


    Center_Transfer() throws IOException {
        System.out.println(")))))))))))))))PAINT");
        this.clientThread = clientThread;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        System.out.println("size-------- " + State.getInstance().getClients().size());

        if (State.getInstance().getClients().size() > 0) {
            this.clientsList = new String[State.getInstance().getClients().size()];
            State.getInstance().getClients().forEach(el -> {
                fromDropDown.addItem("asd");
                System.out.println("el.Fullname " + el.getFullName());
            });

        }


        top.add(amountField);
        top.add(amountLabel);
        top.add(toField);
        top.add(toLabel);
//        top.add(fromField);
        top.add(fromDropDown);
        fromDropDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(fromDropDown.getSelectedItem());
                System.out.println(clientsList.length);
                for (var i = 0; i < clientsList.length; i++) {
                    System.out.println(clientsList[i]);
                }

            }
        });

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (var i = 0; i < clientsList.length; i++) {
                    System.out.println(clientsList[i]);
                }

            }
        });
        top.add(fromLabel);
        this.add(top);
//        this.add(ClientsTable.getInstance());
        this.add(transferButton);

    }

    public static Center_Transfer getInstance() throws IOException {
        if (instance == null) {
            instance = new Center_Transfer();
        }
        return instance;
    }
}
