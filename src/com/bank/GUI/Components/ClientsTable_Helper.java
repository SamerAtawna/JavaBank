package com.bank.GUI.Components;

import Classes.Client;
import Classes.Enums.Enums;
import Classes.User;
import Common.ClientThread;
import com.bank.GUI.Dashboard;
import javax.swing.Timer;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.StreamSupport;

public class ClientsTable_Helper extends JPanel {
    ClientThread clientThread;
    JTable clientsTable;
    DefaultTableModel model;

    ClientsTable_Helper() throws IOException {
        //add helper table to table list
        State.getInstance().tableHelperList.add(this);

        this.clientThread = clientThread;

        clientsTable = new JTable();
        clientsTable.setPreferredSize(new Dimension(600, 300));
        model = new DefaultTableModel(new String[]{"קוד אשראי", "מזהה", "ת.ז.", "סטטוס", "יתרה", "מספר חשבון", "שם"}, 0);
        clientsTable.setModel(model);
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    setData(State.getInstance().getClients());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Reading SMTP Info.");
            }
        };

        JScrollPane sp = new JScrollPane(clientsTable);
        sp.setPreferredSize(new Dimension(700, 300));

        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.add(sp);
        Timer timer = new Timer(2000 , taskPerformer);
        timer.setRepeats(false);
        timer.start();

        clientsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                // print first column value from selected row
                String id = clientsTable.getValueAt(clientsTable.getSelectedRow(), 1).toString();
                try {
                    if (State.getInstance().getCurrentView().equals("הפקדה")){
                        State.getInstance().disposeToRef.setText(id);
                    }else{
                        State.getInstance().withDrawRef.setText(id);

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setData(ArrayList<Client> data) throws IOException {
        System.out.println("SETDATA ClientsTableHelper");
        model.setRowCount(0);
        for (int i = 0; i < data.size(); i++) {

            String userName = data.get(i).getFullName();
            Integer accountID = data.get(i).getAccountNumber();
            Integer clientAccountID = data.get(i).getClientAccountID();
            Float balance = data.get(i).getBalance();
            Enums.Status status = data.get(i).getStatus();
            Integer clientID = data.get(i).getClientID();
            Integer cardCode = data.get(i).getCardCode();

            Object[] dt = {cardCode, clientAccountID, clientID, status, balance, accountID, userName};
            model.addRow(dt);
        }

        this.repaint();
        System.out.println("##Row counts" + model.getRowCount());
        model.fireTableDataChanged();


    }

}
