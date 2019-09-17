package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;

public class MainServer {
        static Connection connection;
        public static void main(String args[]) {

            System.out.println("connecting to db ");
            DatabaseConnectivity.connect();
            //System.out.println("connnected to database");
            ServerSocket serverSocket;
            Socket socket;
            System.out.println("Server started");
            try {
                serverSocket = new ServerSocket(3000);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            while (true) {
                try {
                    socket = serverSocket.accept();
                    System.out.println("Client connnected at "+socket);
                    Thread t = new Thread(new HandleClient(socket));
                    System.out.println("starting this client thread");
                    t.start();


                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }
