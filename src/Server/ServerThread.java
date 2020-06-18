package Server;

import Classes.Client;
import Classes.DisposeBucket;
import Classes.Enums.Enums;
import Classes.User;
import Classes.WithdrawBucket;
import Common.Request;
import Common.Response;
import Server.Database.DB;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerThread extends Thread {

    private Socket client;
    protected static List<Socket> sockets = new ArrayList<>();
    private ObjectOutputStream writer;
    private ObjectInputStream reader;
    private static Map<Socket, ObjectOutputStream> connectionList = new HashMap<>();
    private DB dataBase = new DB();


    public ServerThread(Socket client) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Const....");
        this.client = client;
        this.writer = new ObjectOutputStream(client.getOutputStream());
        sockets.add(client);
        connectionList.put(client, writer);
        ArrayList<User> clients = dataBase.getUsers();
    }

    @Override
    public void run() {
        try {
            reader = new ObjectInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            while(true){
                Request req = (Request) reader.readObject();
                if (req.getType().equals(Enums.RequestType.AUTHENTICATION)) {
                    System.out.println("AUTH Request");
                    User user = (User) req.getContent();
                    User tryUser = this.dataBase.authenticate(user.getUserName(), user.getPassword());
                   if(tryUser.getUserName().equals("")){
                       writer.writeObject(new Response(Enums.ResponseType.AUTH_INVALID, false));
                   }else if(!(tryUser.getUserName().equals(""))){
                       writer.writeObject(new Response(Enums.ResponseType.AUTH_VALID, tryUser));

                   }
                }

                if (req.getType().equals(Enums.RequestType.ALL_CLIENTS)){
                    System.out.println("ALL_Clients Request");
                    ArrayList<Client> allClients = this.dataBase.getClients();
                    System.out.println(allClients.size());
                    writer.writeObject(new Response(Enums.ResponseType.ALL_CLIENTS, allClients));
                }

                if (req.getType().equals(Enums.RequestType.CREATE_ACCOUNT)){
                    Client client = (Client) req.getContent();
                    this.dataBase.createNewAccount(client);
                    writer.writeObject(new Response(Enums.ResponseType.CREATE_ACCOUNT, true));

                }
                if (req.getType().equals(Enums.RequestType.DISPOSE)){
                    DisposeBucket bucket = (DisposeBucket) req.getContent();
                    this.dataBase.disponse(bucket.getId(),bucket.getAmount());
                    writer.writeObject(new Response(Enums.ResponseType.DISPOSE, true));

                }
                if (req.getType().equals(Enums.RequestType.WITHDRAW)){
                    WithdrawBucket bucket = (WithdrawBucket) req.getContent();
                    this.dataBase.withdraw(bucket.getId(),bucket.getAmount());
                    writer.writeObject(new Response(Enums.ResponseType.WITHDRAW, true));

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
