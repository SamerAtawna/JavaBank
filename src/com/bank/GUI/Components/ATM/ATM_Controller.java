package com.bank.GUI.Components.ATM;

import Common.ClientThread;

import java.io.IOException;

public class ATM_Controller {
    public static void main(String[] args) throws IOException {
        Thread client = ATM_Thread.getInstance();
        client.start();
    }
}
