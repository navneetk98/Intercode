package Client;

import Server.DatabaseHandler;
import Server.Verify;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;



public class ClientLoginController {


    @FXML private TextField uidfield;
    @FXML private TextField passwordfield;
    @FXML private TextField togglefield;
    @FXML private Button continuebtn;
    @FXML public  Label statuslabel;


    public  void onContinueButtonClicked()
    {
        Socket socket = MainClient.socket;
        ObjectOutputStream objectOutputStream = MainClient.objectOutputStream;
        ObjectInputStream objectInputStream = MainClient.objectInputStream;

        System.out.println(socket);
        System.out.println(objectInputStream);
        System.out.println(objectOutputStream);

        String uid=uidfield.getText();
        String password=passwordfield.getText();
        String toggle=togglefield.getText();
        System.out.println(uid+" "+password+" "+toggle);
        Credentials credentials=new Credentials(uid,password,toggle,"l");
        try {
            objectOutputStream.writeObject(credentials);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Verify verify=(Verify)objectInputStream.readObject();
            System.out.println(verify);
            if(verify.isVerify())
            {
                System.out.println("login successful");
                if(toggle.compareTo("1")==0)
                    statuslabel.setText("LOGGED IN AS INTERVIEWER");
                else
                    statuslabel.setText("LOGGED IN AS CANDIDATE");
            }
            else
            {
                System.out.println("FAILED LOGIN");
                statuslabel.setText("LOGIN FAILED !! RETRY!");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}