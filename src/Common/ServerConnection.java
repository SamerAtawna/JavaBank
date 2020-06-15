package Common;

import Classes.Client;
import Classes.Enums.Enums;
import Classes.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerConnection {
    private Socket socket;
    private String host;
    private int port;
    private ObjectOutputStream writer;
    private ObjectInputStream reader;
    private Request req = null;

    public ServerConnection (String host,int port)
    {
        this.host = host;
        this.port = port;
    }

    public void connect() throws UnknownHostException, IOException
    {
        socket = new Socket(host,port);
        reader= new  ObjectInputStream(socket.getInputStream());
        writer= new  ObjectOutputStream(socket.getOutputStream());

    }

    public Response getRespone()throws IOException, ClassNotFoundException
    {
        return (Response) reader.readObject();
    }

    public void auth(String userName, String password) throws IOException {
        System.out.println("AUTH ServerConnection");
        req = new Request(Enums.RequestType.AUTHENTICATION, new User(userName, password,"0",Enums.Permission.USER));
        writer.writeObject(req);
        writer.flush();
    }

    public void getClients() throws IOException {
        System.out.println("getClients ServerConnection");
        req = new Request(Enums.RequestType.ALL_CLIENTS, true);
        writer.writeObject(req);
        writer.flush();

    }
    public void createAccount(String name, Integer ID, Integer accountNum, float balance, Enums.Status status , Integer cardCode) throws IOException {
        req = new Request(Enums.RequestType.CREATE_ACCOUNT, new Client(name,accountNum,0,balance,status,ID,cardCode));
        writer.writeObject(req);
        writer.flush();
    }

}
