package sample;



import Server.Verify;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

// JavaFX Scene


public class main  implements Initializable {

    private Socket socket2;
    private Socket socket3;



    @FXML
    public TextField name;
    public PasswordField pass;
    public TextField regno;
    public TextField cpi;
    public ChoiceBox login_as;
 @FXML
    public Label statuslabel;
    public Button backup;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        login_as.getItems().add("Interviewer");
        login_as.getItems().add("Interviewee");
    }

    public void login(ActionEvent click) {
        try {
            Socket socket = Intercode.socket;
            ObjectOutputStream objectOutputStream = Intercode.objectOutputStream;
            ObjectInputStream objectInputStream = Intercode.objectInputStream;
            String toggle;
            System.out.println(socket);
            System.out.println(objectInputStream);
            System.out.println(objectOutputStream);
            String uid = regno.getText();
            String password = pass.getText();
            if (login_as.getValue().equals("Interviewer")) {
                StaticClass.interviewer=true;
                toggle = "1";
            }
            else {
                toggle = "0";
                StaticClass.interviewer=false;
            }
            System.out.println(uid + " " + password + " " + toggle);
            sample.Credentials credentials = new sample.Credentials(uid, password, toggle, "l");

            try {
                objectOutputStream.writeObject(credentials);
                objectOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Verify verify = (Verify) objectInputStream.readObject();
                System.out.println(verify);
                if (verify.isVerify()) {
                    socket2=new Socket(StaticClass.ip_address,3001);
                    socket3=new Socket(StaticClass.ip_address,3002);
                    System.out.println("connected to server for chat at 3002 and editor at 3001");
                    System.out.println("calling chatClientHandler class");
                    new ChatHandlerClient(socket3);

                    System.out.println("login successful");
                    if (toggle.compareTo("1") == 0)
                        statuslabel.setText("LOGGED IN AS INTERVIEWER");
                    else
                        statuslabel.setText("LOGGED IN AS CANDIDATE");

                    Parent root = FXMLLoader.load(getClass().getResource("Intercode_ide_final.fxml"));
                    Stage primaryStage = (Stage) ((Node) (click.getSource())).getScene().getWindow();
                    primaryStage.setTitle("Intercode");
                    primaryStage.setScene(new Scene(root, 1920, 1080));
                    primaryStage.setMaximized(true);
                    primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/sbi.png")));


                    primaryStage.setOnCloseRequest(e -> {
                        e.consume();
                        Intercodecontroller incd = new Intercodecontroller();
                        incd.exiter();
                    });
                    primaryStage.show();

                } else {
                    System.out.println("FAILED LOGIN");
                    statuslabel.setText("LOGIN FAILED !! RETRY!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void register(ActionEvent click) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ClientRegisterForm.fxml"));
            Stage primaryStage = (Stage) ((Node) (click.getSource())).getScene().getWindow();
            primaryStage.setTitle("Login");
            primaryStage.setScene(new Scene(root, 1920, 1080));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void back(ActionEvent click) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Intercode_ide_final.fxml"));
            Stage primaryStage = (Stage) ((Node) (click.getSource())).getScene().getWindow();
            primaryStage.setTitle("Intercode");
            primaryStage.setScene(new Scene(root, 1920, 1080));
            primaryStage.setMaximized(true);
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/sbi.png")));


            primaryStage.setOnCloseRequest(e -> {
                e.consume();
                Intercodecontroller incd = new Intercodecontroller();
                incd.exiter();
            });
            primaryStage.show();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}










