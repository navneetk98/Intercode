package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

// JavaFX Scene


public class main {
@FXML
public TextField name;
public PasswordField pass;
public TextField regno;
public TextField cpi;

    public void login(ActionEvent click) {
        try {
            StaticClass.name = name.getText();
            StaticClass.regno = regno.getText();
            Parent root = FXMLLoader.load(getClass().getResource("try3.fxml"));
            Stage primaryStage = (Stage) ((Node)(click.getSource())).getScene().getWindow();
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
//        try {
//            Thread t1 = new Thread(new network());
//            t1.start();
//           // t1.sleep(1000);
//        } catch (Exception exc) {
//            {
//                exc.printStackTrace();
//            }
//
//        }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        public static void main (String[]args){
//            launch(args);
//        }
    }
}
