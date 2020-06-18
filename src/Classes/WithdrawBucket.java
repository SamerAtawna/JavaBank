
package Classes;

        import java.io.Serializable;

public class WithdrawBucket implements Serializable {
    int id;
    float amount;

    public WithdrawBucket(int id, float amount) {
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
