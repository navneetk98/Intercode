package sample;

// JavaFX All

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

// JavaFX Scene


public class Intercode extends Application {
    public static Socket socket;
    public static ObjectInputStream objectInputStream;
    public static ObjectOutputStream objectOutputStream;
    public ChoiceBox login_as;

    @Override
    public void start(Stage primaryStage) throws IOException, java.lang.OutOfMemoryError, java.lang.NullPointerException {
        Parent root = FXMLLoader.load(getClass().getResource("Login_page.fxml"));
    //    final SwingNode swingNode = new SwingNode();
      //  createSwingContent(swingNode);

   //     StackPane pane = new StackPane();
  //      pane.getChildren().add(swingNode);

        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 1280, 1024));
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

        }
    public static void main(String[] args)
    {
        try
        {
            System.out.println("Trying here");
             socket=new Socket(StaticClass.ip_address,3009);
            System.out.println("Connected to Server ");
            objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.flush();
            objectInputStream=new ObjectInputStream(socket.getInputStream());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        launch(args);
    }
}
