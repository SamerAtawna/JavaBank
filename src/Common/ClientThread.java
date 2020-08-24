package Common;

import Classes.Client;
import Classes.Enums.Enums;
import Classes.Transaction;
import Classes.User;
import com.bank.GUI.Components.ClientsTable;
import com.bank.GUI.Components.State;
import com.bank.GUI.Components.Transactions;
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
    public  void run() {

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
                    this.login.dispose();
                    com.bank.GUI.Components.State.getInstance().setLoggedUser(currUser);
                    Dashboard ds = Dashboard.getInstance();

                }
                if (rsp.getType().equals(Enums.ResponseType.ALL_CLIENTS)){
                    System.out.println("ALL_CLIENS ClientThread");
                    ArrayList<Client> allClients = (ArrayList<Client>) rsp.getContent();
                    System.out.println(allClients.size());
//                    ClientsTable.getInstance().setData(allClients);
                    com.bank.GUI.Components.State.getInstance().setClients(allClients);
                    com.bank.GUI.Components.State.getInstance().setClientsNumber(allClients.size());
                    com.bank.GUI.Components.State.getInstance().getClientsTableRef().getNewData();

                }

                if (rsp.getType().equals(Enums.ResponseType.CREATE_ACCOUNT)) {
                    JOptionPane.showMessageDialog(null,"  החשבון נוצר בהצלחה","הודעה", JOptionPane.INFORMATION_MESSAGE);
                    this.getClients();

                }
                if (rsp.getType().equals(Enums.ResponseType.NEW_USER)) {
                    JOptionPane.showMessageDialog(null,"  המשתמש נוצר בהצלחה","הודעה", JOptionPane.INFORMATION_MESSAGE);
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

                if (rsp.getType().equals(Enums.ResponseType.TRANSACTIONS)){
                    System.out.println("ALL_CLIENS ClientThread");
                    ArrayList<Transaction> transList = (ArrayList<Transaction>) rsp.getContent();
                    Transactions.getInstance().setTransData(transList);
                    //store selected user transaction data on state
                    com.bank.GUI.Components.State.getInstance().setTransList(transList);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
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
    public void getTrans(int client) throws IOException {
        System.out.println("ClientThread getClients");
        this.cnt.getTrans(client);
    }
    public void createAccount(String name, Integer ID, Integer accountNum, float balance, Enums.Status status, Integer cardCode) throws IOException {
        this.cnt.createAccount(name,ID,accountNum,balance,status,cardCode);
    }

    public void createUser(String userName, String password, String permission) throws IOException {

            System.out.println("Creating user... username:"+userName+" pass : "+password+" permissin "+permission);

        switch (permission){
            case "Admin": this.cnt.createUser(new User(userName, password, Enums.Permission.ADMIN));
            break;
            case "User": this.cnt.createUser(new User(userName, password, Enums.Permission.USER));
        }
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
        cnt.withDraw(id, amount, "BANK");
    }


}
