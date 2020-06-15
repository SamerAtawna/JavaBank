package com.bank.GUI;

import Common.ClientThread;

public class Client_Controller {
    public static void main(String[] args) {
        Thread client = new ClientThread();
        client.start();
    }
}
