package sample;


import Server.Verify;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    public void onRegisterbtnClicked(ActionEvent click)
    {
        Socket socket = Intercode.socket;
        ObjectOutputStream objectOutputStream = Intercode.objectOutputStream;
        ObjectInputStream objectInputStream = Intercode.objectInputStream;

        System.out.println(socket);
        System.out.println(objectInputStream);
        System.out.println(objectOutputStream);

        String uid=uidfield.getText();
        String password=passwordfield.getText();
        String toggle=togglefield.getText();
        String name=namefield.getText();
        String branch=branchfield.getText();
        String cpi=cpifield.getText();
        sample.Credentials credentials=new Credentials(uid,password,toggle,name,branch,cpi,"r");
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
                Parent root = FXMLLoader.load(getClass().getResource("Login_page.fxml"));
                Stage primaryStage = (Stage) ((Node) (click.getSource())).getScene().getWindow();
                primaryStage.setTitle("Register");
                primaryStage.setScene(new Scene(root, 1920, 1080));
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
