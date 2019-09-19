package sample;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Sync_receive implements Runnable {
//    Socket socket;
//    ObjectInputStream objin;
//    ObjectOutputStream objout;
//    Sync_receive()
//    {
//
//    }
    @Override
    public void run() {
        String s="";
        while (true)
        {
            try {
              s=  StaticClass.objectInput.readUTF();
              StaticClass.jpane.setText(s);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
