package Common;

import Classes.Client;
import Classes.Enums.Enums;
import Classes.User;
import com.bank.GUI.Components.ClientsTable;
import com.bank.GUI.Components.State;
import com.bank.GUI.Dashboard;
import com.bank.GUI.Login;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class ClientThread extends Thread  {

    ServerConnection cnt = new ServerConnection("localhost", 5555);
    private static ClientThread instance = null;

    Login login = new Login(this);

    private ClientThread() throws IOException {

    }

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
                if (rsp.getType().equals(Enums.ResponseType.AUTH_INVALID)) {
                    JOptionPane.showMessageDialog(null,"פרטי כניסה שגויים","שגיאה", JOptionPane.ERROR_MESSAGE);

                }else if ( rsp.getType().equals(Enums.ResponseType.AUTH_VALID)){
                    User currUser = (User) rsp.getContent();
                    new Dashboard(currUser, this);

                }
                if (rsp.getType().equals(Enums.ResponseType.ALL_CLIENTS)){
                    System.out.println("ALL_CLIENS ClientThread");
                    ArrayList<Client> allClients = (ArrayList<Client>) rsp.getContent();
                    System.out.println(allClients.size());
                    ClientsTable.getInstance().setData(allClients);
                    com.bank.GUI.Components.State.getInstance().setClients(allClients);
                }

                if (rsp.getType().equals(Enums.ResponseType.CREATE_ACCOUNT)) {
                    JOptionPane.showMessageDialog(null,"  החשבון נוצר בהצלחה","הודעה", JOptionPane.INFORMATION_MESSAGE);
                    this.getClients();

                }

                if (rsp.getType().equals(Enums.ResponseType.DISPOSE)) {
                    JOptionPane.showMessageDialog(null,"  הפקדה בוצעה","הודעה", JOptionPane.INFORMATION_MESSAGE);
                    this.getClients();

                }

                if (rsp.getType().equals(Enums.ResponseType.WITHDRAW)) {
                    System.out.println("Repose Cline tWITHDRAW");
                    JOptionPane.showMessageDialog(null,"משיכה בוצעה בהצלחה","הודעה", JOptionPane.INFORMATION_MESSAGE);
                    this.getClients();

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void auth(String userName, String password) throws IOException {
        System.out.println("AUTH ClientThread");
        this.cnt.auth(userName, password);
    }

    public void getClients() throws IOException {
        System.out.println("ClientThread getClients");
        this.cnt.getClients();
    }

    public void createAccount(String name, Integer ID, Integer accountNum, float balance, Enums.Status status, Integer cardCode) throws IOException {
        this.cnt.createAccount(name,ID,accountNum,balance,status,cardCode);
    }

    public static ClientThread getInstance() throws IOException {
        if (instance == null) {
            instance = new ClientThread();
        }
        return instance;
    }

    public void dispose(String id, float amount) throws IOException {
        cnt.dispose(id, amount);
    }
    public void withDraw(String id, float amount) throws IOException {
        cnt.withDraw(id, amount);
    }


}
