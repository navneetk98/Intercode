package Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainClient extends Application
{
    public static Socket socket;
    public static ObjectInputStream objectInputStream;
    public static ObjectOutputStream objectOutputStream;


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root;
        root = FXMLLoader.load(getClass().getResource("ClientRegisterForm.fxml"));
        primaryStage.setTitle("Intercode Register");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        try
        {
            socket=new Socket("192.168.2.5",3000);
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
