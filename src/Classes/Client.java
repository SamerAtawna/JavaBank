package Classes;

import Classes.Enums.Enums;

import java.io.Serializable;

public class Client implements Serializable {
    private String fullName;
    private Integer accountNumber;
    private Integer clientAccountID;
    private float balance;
    private Enums.Status status;
    private Integer clientID;
    private Integer cardCode;


    public Client(String fullName, Integer accountNumber, Integer clientAccountID, float balance, Enums.Status status, Integer clientID, Integer cardCode) {
        this.fullName = fullName;
        this.accountNumber = accountNumber;
        this.clientAccountID = clientAccountID;
        this.balance = balance;
        this.status = status;
        this.clientID = clientID;
        this.cardCode = cardCode;
    }
    public Client(String userName) {
        this.fullName = userName;
    }

    public Client(Integer cardCode) {
        this.cardCode = cardCode;
    }
    public Client(int id, int password){
        this.clientID = id;
        this.cardCode=password;
    }



    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getClientAccountID() {
        return clientAccountID;
    }

    public void setClientAccountID(Integer clientAccountID) {
        this.clientAccountID = clientAccountID;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public Enums.Status getStatus() {
        return status;
    }

    public void setStatus(Enums.Status status) {
        this.status = status;
    }

    public Integer getClientID() {
        return clientID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public Integer getCardCode() {
        return cardCode;
    }

    public void setCardCode(Integer cardCode) {
        this.cardCode = cardCode;
    }

}
