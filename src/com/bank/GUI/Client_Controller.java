package com.bank.GUI;

import Common.ClientThread;

import java.io.IOException;

public class Client_Controller {
    public static void main(String[] args) throws IOException {
        Thread client = ClientThread.getInstance();
        client.run();
    }
}
