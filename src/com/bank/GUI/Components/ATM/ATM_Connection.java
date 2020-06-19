package com.bank.GUI.Components.ATM;

import Classes.Client;
import Classes.Enums.Enums;
import Classes.User;
import Classes.WithdrawBucket;
import Common.ClientThread;
import Common.Request;
import Common.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ATM_Connection {
    private Socket socket;
    private String host;
    private int port;
    private ObjectOutputStream writer;
    private ObjectInputStream reader;
    private Request req = null;

    public ATM_Connection (String host,int port)
    {
        this.host = host;
        this.port = port;
    }

    public void connect() throws UnknownHostException, IOException
    {
        socket = new Socket(host,port);
        reader= new  ObjectInputStream(socket.getInputStream());
        writer= new  ObjectOutputStream(socket.getOutputStream());
        System.out.println("CENNECTED");

    }

    public Response getRespone()throws IOException, ClassNotFoundException
    {
        return (Response) reader.readObject();
    }

    public void checkCard( int password) throws IOException {
        System.out.println("AUTH ServerConnection");
        req = new Request(Enums.RequestType.ATM_IDENTIFY, new Client(0,password));
        writer.writeObject(req);
        writer.flush();
    }
    public void withDraw(String id, float amount, String location) throws IOException {
        System.out.println("WITHDRAW ServerConnection");
        req = new Request(Enums.RequestType.ATM_CHARGE, new WithdrawBucket(Integer.parseInt(id),amount, location));
        writer.writeObject(req);
        writer.flush();
    }
}
