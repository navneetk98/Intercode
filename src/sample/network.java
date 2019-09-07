package sample;

import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class network implements Runnable {
    ObjectInputStream objectInputStream;

    public void run() {
        TextArea tf = StaticClass.tf;
        String check;

            try {
                System.out.println("done");
                Socket socket = new Socket("127.0.0.1", 25001);
                objectInputStream= new ObjectInputStream(socket.getInputStream());
            }
            catch (Exception e) {
                e.printStackTrace();
            }

                while (true) {
                    try {
                        check = (String) objectInputStream.readObject();
                        System.out.println(check);
                        tf.setText(check);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();

//                while (true) {
//
//                    System.out.println(tf.getText());
//                }

            }

        }
    }
        }
