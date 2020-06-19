package Classes;

import java.io.Serializable;

public class DisposeBucket implements Serializable {
    int id;
    float amount;
    String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public DisposeBucket(int id, float amount, String location) {
        this.id = id;
        this.amount = amount;
        this.location = location;
    }

    public DisposeBucket(int id, float amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
