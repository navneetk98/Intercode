package sample;

import Chat.ReceiveMessage;
import Chat.SendMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ChatHandlerClient {
    private Socket socket3;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public ChatHandlerClient(Socket socket3)
    {
        this.socket3=socket3;
        try {
            objectOutputStream=new ObjectOutputStream(socket3.getOutputStream());
            System.out.println(objectOutputStream);
            objectInputStream=new ObjectInputStream(socket3.getInputStream());
StaticClass.chat_objectInput=objectInputStream;
StaticClass.chat_objectOutput=objectOutputStream;
//            Thread sendThread=new Thread(new SendMessage(objectOutputStream));
            Thread receiveThread=new Thread(new ReceiveMessage(objectInputStream));
            System.out.println("starting threads for sendMsg and Receive Thread");
//            sendThread.start();
            receiveThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }


}
