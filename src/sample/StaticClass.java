package sample;
//pbx62522@bcaoo.com

import com.jfoenix.controls.JFXTextArea;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import javax.swing.*;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class StaticClass {



    public static TextArea tf;
    public static ComboBox combo;
    public static String ip_address="172.31.129.22";
    public static String name;
    public static ObjectOutputStream objectOutput;
    public static ObjectInputStream objectInput;
    public static Socket socket_doc;
    public static ObjectOutputStream chat_objectOutput;
    public static ObjectInputStream chat_objectInput;
    public static TextArea chat_text;
    public static JFXTextArea chat_input;
    public static JTextPane jpane;
    public static boolean interviewer;
    public static String regno;
    public static File file;
    public static ListView list_id;
}
