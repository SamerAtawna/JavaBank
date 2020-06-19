package com.bank.GUI.Components;

import Classes.Client;
import Classes.Enums.Enums;
import Classes.User;
import Common.ClientThread;
import com.bank.GUI.Dashboard;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class ClientsTable extends JPanel {
    private static ClientsTable instance = null;
    ClientThread clientThread;
    JTable clientsTable;
    DefaultTableModel model;

     ClientsTable() throws IOException {

        this.clientThread = clientThread;

        clientsTable = new JTable();
        clientsTable.setPreferredSize(new Dimension(600, 300));
        model = new DefaultTableModel(new String[]{"קוד אשראי", "מזהה", "ת.ז.", "סטטוס", "יתרה", "מספר חשבון", "שם"}, 0){
            public boolean isCellEditable(int row, int column)
            {
                return false;//This causes all cells to be not editable
            }
        };
        clientsTable.setModel(model);


        JScrollPane sp = new JScrollPane(clientsTable);
        sp.setPreferredSize(new Dimension(700, 300));

        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.add(sp);
         clientsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
             public void valueChanged(ListSelectionEvent event) {
                 System.out.println("##FIREC ACTION");
                 // print first column value from selected row
               if(clientsTable.getRowCount()>0){
                   String id = clientsTable.getValueAt(clientsTable.getSelectedRow(), 1).toString();
                   try {
                       State.getInstance().setTransClient(Integer.parseInt(id));

                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }

             }
         });

    }

    public void setData(ArrayList<Client> data) throws IOException {
        System.out.println("SETDATA ClientsTable");
        System.out.println("SETDATA ClientsTable data length "+ data.size());
        State.getInstance().setClients(data);
        model.setRowCount(0);
        for (int i = 0; i < data.size(); i++) {

            String userName = data.get(i).getFullName();
            Integer accountID = data.get(i).getAccountNumber();
            Integer clientAccountID = data.get(i).getClientAccountID();
            Float balance = data.get(i).getBalance();
            Enums.Status status = data.get(i).getStatus();
            Integer clientID = data.get(i).getClientID();
            Integer cardCode = data.get(i).getCardCode();

            Object[] dt = {cardCode,clientAccountID, clientID, status, balance, accountID, userName};
            model.addRow(dt);
//            State.getInstance().notifyTables();
        }

        this.repaint();
        System.out.println("##Row counts" + model.getRowCount());
        model.fireTableDataChanged();
        State.getInstance().setClientsNumber(model.getRowCount());//Update clients counter
        Dashboard.getInstance().repaint();



    }

    public static ClientsTable getInstance() throws IOException {
        if (instance == null) {
            instance = new ClientsTable();
        }
        return instance;
    }
}
