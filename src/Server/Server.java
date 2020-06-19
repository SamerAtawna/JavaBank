package Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Server {
    public  static  void  main(String[] args) throws IOException, SQLException, ClassNotFoundException {

        System.out.println(	"Starting  server at " + InetAddress.getLocalHost());

        // ServerSocket  knows how to wait for clients to connct:
        ServerSocket listening =new ServerSocket(5555);
        ServerSocket listeningATM =new ServerSocket(1111);

        while(true){
            // wait for a client to connect (return socket to that client):
            Socket client = listening.accept();
            Socket clientATM = listeningATM.accept();

            // launch thread to handle client :
            ServerThread thread= new ServerThread(client);
            thread.start();
            ServerThread thread2= new ServerThread(clientATM);
            thread2.start();
        }
    }
}
