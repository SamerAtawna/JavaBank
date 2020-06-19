package com.bank.GUI.Components.ATM;

import Classes.Client;
import Classes.Enums.Enums;
import Common.Response;

import javax.swing.*;
import java.io.IOException;

public class ATM_Thread extends Thread {
    private static ATM_Thread instance = null;

    ATM_Connection cnt = new ATM_Connection("localhost", 5555);
    ATM atm = new ATM();
    @Override
    public void run() {
        try {
            cnt.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            while (true) {
                Response rsp = cnt.getRespone();
                if (rsp.getType().equals(Enums.ResponseType.ATM_IDENTIFY)) {
                    Client currUser = (Client) rsp.getContent();
                    System.out.println("reponse atm ");
                    if (currUser.getFullName().equals("")) {
                        System.out.println("true");
                        atm.setServerMessage("<html><font color=red size=30>קוד שגוי</font></html>");
                    }else{
                        System.out.println("currUserID "+ currUser.getAccountNumber());
                        atm.next(currUser);
                    }


                }
                if (rsp.getType().equals(Enums.ResponseType.ATM_CHARGE)) {
                    atm.setServerMessage("<html><font color=green size=30>המשיכה בוצעה</font></html>");

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ATM_Thread getInstance() throws IOException {
        if (instance == null) {
            instance = new ATM_Thread();
        }
        return instance;
    }

    public void checkCard(int password) throws IOException {
    this.cnt. checkCard(password);
    }

    public void withDraw(String id, float amount) throws IOException {
        cnt.withDraw(id, amount, "ATM");
    }
}