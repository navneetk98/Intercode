package Server;

import Client.Credentials;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HandleClient implements Runnable {
    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    public boolean verify;

    public HandleClient(Socket socket)
    {
        this.socket=socket;
        System.out.println(socket);
        try {
            InputStream isr=socket.getInputStream();
            objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.flush();
            System.out.println(objectOutputStream);
            objectInputStream=new ObjectInputStream(isr);
            System.out.println(objectInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("HANDLECLIENT.JAVA: "+ objectInputStream+" "+objectOutputStream);
        //System.out.println("zxvcc");
    }

    @Override
    public void run() {
        System.out.println("ClientHANDLER");


        Credentials credentials = null;
        while (true) {
            try {
                credentials = (Credentials) objectInputStream.readObject();
                System.out.println("credentials received from client");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (credentials != null && credentials.getRol().compareTo("l")==0)
                verify = DatabaseHandler.verifyCredentials(credentials);
            else if(credentials !=null && credentials.getRol().compareTo("r")==0)
                verify=DatabaseHandler.registerCredentials(credentials);

            Verify v = new Verify(verify);
            try {
                objectOutputStream.writeObject(v);
                objectOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
