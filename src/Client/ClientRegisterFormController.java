package Client;

import Server.Verify;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientRegisterFormController {
    @FXML private TextField uidfield;
    @FXML private TextField passwordfield;
    @FXML private TextField togglefield;
    @FXML private Button registerbtn;
    @FXML public Label statuslabel;
    @FXML private TextField namefield;
    @FXML private TextField branchfield;
    @FXML private TextField cpifield;

    public void onRegisterbtnClicked()
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
        String name=namefield.getText();
        String branch=branchfield.getText();
        String cpi=cpifield.getText();
        Credentials credentials=new Credentials(uid,password,toggle,name,branch,cpi,"r");
        try
        {
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
                System.out.println("successfully Registered");
                if(toggle.compareTo("1")==0)
                    statuslabel.setText("Registered as Interviewer");
                else
                    statuslabel.setText("Registered AS CANDIDATE");
            }
            else
            {
                System.out.println("Registration Failed");
                statuslabel.setText("Registration FAILED !! RETRY!");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }



    }
}
