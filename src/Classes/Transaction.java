package Classes;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {
    int clientID;
    String Action;
    float amount;
    Date date;
    String location;

    public Transaction(int clientID, String action, float amount, Date date, String location) {
        this.clientID = clientID;
        Action = action;
        this.amount = amount;
        this.date = date;
        this.location = location;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
