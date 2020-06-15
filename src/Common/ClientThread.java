package Common;

import Classes.Client;
import Classes.Enums.Enums;
import Classes.User;
import com.bank.GUI.Components.ClientsTable;
import com.bank.GUI.Dashboard;
import com.bank.GUI.Login;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class ClientThread extends Thread  {

    ServerConnection cnt = new ServerConnection("localhost", 5555);

    Login login = new Login(this);

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
                }

                if (rsp.getType().equals(Enums.ResponseType.CREATE_ACCOUNT)) {
                    JOptionPane.showMessageDialog(null,"  החשבון נוצר בהצלחה","הודעה", JOptionPane.INFORMATION_MESSAGE);

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


}
