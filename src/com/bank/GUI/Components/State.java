package com.bank.GUI.Components;

import Classes.Client;
import Classes.User;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class State {
    int clientsNumber;
    String currentView;
    ArrayList<Client> clients = new ArrayList<>();
    String disposeTo;
    public ArrayList<ClientsTable_Helper> tableHelperList = new ArrayList<>();
    public  JTextField disposeToRef;
    public  JTextField withDrawRef;
    int transClient;
    User loggedUser;

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public int getTransClient() {
        return transClient;
    }

    public void setTransClient(int transClient) {
        this.transClient = transClient;
    }

    public String getCurrentView() {
        return currentView;
    }

    public void setCurrentView(String currentView) {
        this.currentView = currentView;
    }

    public JTextField getWithDrawRef() {
        return withDrawRef;
    }

    public void setWithDrawRef(JTextField withDrawRef) {
        this.withDrawRef = withDrawRef;
    }

    private static State instance = null;

    private State() {
        this.clientsNumber = clientsNumber;
    }

    public JTextField getDisposeToRef() {
        return disposeToRef;
    }

    public void setDisposeToRef(JTextField disposeToRef) {
        this.disposeToRef = disposeToRef;
    }

    public String getClientsNumber() {
        return  String.valueOf(clientsNumber);
    }

    public void setClientsNumber(int clientsNumber) {
        this.clientsNumber = clientsNumber;
    }

    public static State getInstance() throws IOException {
        if (instance == null) {
            instance = new State();
        }
        return instance;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
        System.out.println("State CLientsSet");
    }

    public String getDisposeTo() {
        return disposeTo;
    }

    public void setDisposeTo(String disposeTo) {
        this.disposeTo = disposeTo;
    }

    public void notifyTables() throws IOException {
        for (int i = 0; i < tableHelperList.size() ; i++) {
            tableHelperList.get(i).setData(getClients());
            tableHelperList.get(i).repaint();
        }

    }
}
