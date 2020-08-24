package com.bank.GUI.Components;

import Common.ClientThread;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Center_Clients extends JPanel {

    ClientThread clientThread;
    JLabel numOfClientsLabel = new JLabel("מספר לקוחות");
    Integer numofClients = 0;
    JLabel clientsNum = new JLabel(numofClients.toString());
    JPanel numClientsPanel = new JPanel(new FlowLayout());
    JPanel searchPanel = new JPanel(new FlowLayout());
    JLabel searchLabel = new JLabel("חיפוש");

    JButton refresh = new JButton("רענן");
    JPanel topPanel = new JPanel(new GridLayout(1,2));
    JTextField searchTextField = new JTextField(10);
    ClientsTable clientsTable = new ClientsTable();


    Center_Clients() throws IOException, InterruptedException {

        this.clientThread = clientThread;
        State.getInstance().setClientsTableRef(clientsTable);
        this.setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
        numClientsPanel.add(numOfClientsLabel);
        numClientsPanel.add(clientsNum);
        searchPanel.add(searchTextField);
        searchPanel.add(searchLabel);
        searchPanel.add(refresh);
        topPanel.add(numClientsPanel);
        topPanel.add(searchPanel);
        this.add(topPanel);
        ClientThread.getInstance().getClients();
        clientsTable.getNewData();
        this.add(clientsTable);
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    clientsNum.setText(State.getInstance().getClientsNumber());
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        timer.setRepeats(false);
        timer.start();
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ClientThread.getInstance().getClients();
                    clientsTable.getNewData();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        searchTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                String text = searchTextField.getText();
                clientsTable.sortTable(text);
            }
        });
    }
}
