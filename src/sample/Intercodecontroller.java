package sample;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;


public class Intercodecontroller implements Initializable, Runnable, DocumentListener {
    private double ftz = 24; // Font Text Size
    private String ff = "Ubuntu"; // Font Family
    private String tic = "white"; // Text Inner Color
    private int themess = 0; // Theme S selected
    private ToggleGroup themer = new ToggleGroup(); // The group that contains the radio buttons
    private Boolean saveAs = false; // If to do the save as
    private BooleanProperty controlKey = new SimpleBooleanProperty(false);
    private BooleanProperty nKey = new SimpleBooleanProperty(false);
    private BooleanProperty oKey = new SimpleBooleanProperty(false);
    private BooleanProperty sKey = new SimpleBooleanProperty(false);
    private File savedFile; // The file has been saved
    private String savedFileExt; // The extension of the file has been saved
    private final BooleanBinding newShortcut = controlKey.and(nKey);
    private final BooleanBinding openShortcut = controlKey.and(oKey);
    private final BooleanBinding saveShortcut = controlKey.and(sKey);
    private Boolean savedFileB = false; // If is to do the simple save

    public JTextPane jpane;

    @FXML
    public TextArea tftype; // The text typed for the USER
    public RadioMenuItem lightbt; // Button to change to light theme (themer)
    public RadioMenuItem darkbt; // Button to change to dark theme (themer)
    public Parent Vboxmain; // The head of all
    public SwingNode swingNode;
    public ComboBox combo;
    public ListView list_id;
    public Label bottom_left;
    public Label top_left_label;
    @Override
    public void initialize(URL url, ResourceBundle rb) { // Be activates when the program opens.
//        update();
        jpane = new JTextPane();
        top_left_label.setText("Welcome : "+StaticClass.name+"   "+StaticClass.regno);
      SyntaxHighlight.doc = jpane.getStyledDocument();
        jpane.setBounds(10,10,1000,1000);

       try{
           SyntaxHighlight.readkeywords();
       }catch(IOException ex)
       {
           System.out.println(ex.getMessage());
       }
       JScrollPane scroller = new JScrollPane(jpane);
       swingNode.setContent(scroller);


       jpane.setText("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println(jpane.getText());
        lightbt.setToggleGroup(themer); // Sets the ToogleGroup of the lightbt (Every Radio Button needs a ToogleGroup)
        darkbt.setToggleGroup(themer); // Sets the ToogleGroup of the darkbt (Every Radio Button needs a ToogleGroup)
        darkbt.setSelected(true); // Sets the darkbt button bes selected or as default option
        StaticClass.combo=combo;
        StaticClass.list_id=list_id;
    }


    public void save() {

        if (!savedFileB || saveAs) {
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Text document (*.txt)", "*.txt");
            FileChooser.ExtensionFilter filter3 = new FileChooser.ExtensionFilter("C code", "*.c");
            FileChooser.ExtensionFilter filter2 = new FileChooser.ExtensionFilter("All Files (.*.)", "*");
            if (tftype == null) {
                System.out.println("null");
            }
            FileChooser choose = new FileChooser();
            if (saveAs) {
                choose.setTitle("Save As - Intercode");
            } else {
                choose.setTitle("Save - Intercode");
            }
            choose.getExtensionFilters().addAll(filter, filter2, filter3);
            Stage vista = (Stage) Vboxmain.getScene().getWindow();
            File file = choose.showSaveDialog(vista);
            String extension = "";
            try {
                if (file != null) { // If file is null is because has a error
                    if (!file.toString().contains(".txt")) { // If the

                        if (file.getCanonicalPath().endsWith("txt")) {
                            extension = "*.txt";
                        } else if (!file.toString().contains(".c")) { // If the

                            if (file.getCanonicalPath().endsWith("c")) {
                                extension = "*.c";
                            } else {
                                extension = null;
                            }
                        }
                    }
                }

                FileWriter f = new FileWriter(file + extension);
                String text="";
                int len =SyntaxHighlight.doc .getLength();
                text = SyntaxHighlight.doc.getText(0,len);
                f.write(text);
                f.close();
                savedFileB = true;
                savedFile = file;
                StaticClass.file = file;
                savedFileExt = extension;
                saveAs = false;


                if (file != null) {
                    ((Stage) Vboxmain.getScene().getWindow()).setTitle(file.getName() + " - Intercode");
                } else {
                    System.out.println("save called");
                    FileWriter f1 = new FileWriter(savedFile + savedFileExt);
                    String text1 = "";
                    int len1 = SyntaxHighlight.doc.getLength();
                    text1 = SyntaxHighlight.doc.getText(0, len1);
                    f1.write(text1);
                    f1.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


public void onkey()
{
//    System.out.println("hiiiiiiiii");

    if (list_id.getSelectionModel().getSelectedItems() != null &&
            !list_id.getSelectionModel().getSelectedItems().isEmpty()){

        System.out.println(list_id.getSelectionModel().getSelectedItems().toString());
}

}

    public void load() throws IOException {
        try {
            System.out.println("Load called");
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Text document (*.txt)", "*.txt");
            FileChooser.ExtensionFilter filter2 = new FileChooser.ExtensionFilter("All Files (.*.)", "*");
            FileChooser choose = new FileChooser();
            choose.getExtensionFilters().addAll(filter, filter2);
            Stage vista = (Stage) Vboxmain.getScene().getWindow();
            File file = choose.showOpenDialog(vista);

            if (file != null) {
                FileReader reader = new FileReader(file);
                BufferedReader readerl = new BufferedReader(reader);
                String hahaha = readerl.readLine();
                jpane.setText("");
                while (hahaha != null) {
                    jpane.setText(jpane.getText() + hahaha + "\n");
                    hahaha = readerl.readLine();
                }
                reader.close();
            }
            try {
                if (file != null) {
                    ((Stage) Vboxmain.getScene().getWindow()).setTitle(file.getName() + " - Intercode");
                }

            } catch (java.lang.NullPointerException e) {
                e.printStackTrace();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


    public void saveAs() {
        saveAs = true;
        try {
            save();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void news() {
        jpane.setText(null);
    }

    private void timerKeyPress(BooleanProperty x)
    {
        Timeline time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        KeyFrame frame = new KeyFrame(Duration.seconds(0.2), event -> {
           x.set(false);
           time.stop();
        });
        time.getKeyFrames().add(frame);
        time.playFromStart();
    }

    public void keyPress(KeyEvent keyEvent) {
        Timeline time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        if (keyEvent.getCode() == KeyCode.CONTROL) {
            controlKey.set(true);
            timerKeyPress(controlKey);
        } else if (keyEvent.getCode() == KeyCode.N) {
            nKey.set(true);
            timerKeyPress(nKey);
        } else if (keyEvent.getCode() == KeyCode.O) {
            oKey.set(true);
            timerKeyPress(oKey);
        } else if (keyEvent.getCode() == KeyCode.S) {
            sKey.set(true);
            timerKeyPress(sKey);
        }
        newShortcut.addListener((observable, oldValue, newValue) -> {
            if (nKey.get() && controlKey.get()) {
                nKey.set(false);
                controlKey.set(false);
                news();
            }

        });
        openShortcut.addListener((observable, oldValue, newValue) -> {
            if (oKey.get() && controlKey.get()) {
                oKey.set(false);
                controlKey.set(false);
                try {
                    load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        saveShortcut.addListener((observable, oldValue, newValue) -> {
            if (sKey.get() && controlKey.get()) {
                sKey.set(false);
                controlKey.set(false);
                try {
                    save();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Updates System
    private void update() { // Updates the settings and personalization of the program.
        Font font = new Font(ff, Font.BOLD, (int)ftz);
        jpane.setFont(font);
        String theme = "null";

//        if(themess == 0){theme = "black";tic = "white";
//       //. Font.getFont(ff)
//        jpane.setFont(Font.getFont(ff));
//      //  jpane.setFont(ff);
//        jpane.setBackground(Color.gray);
//        } // Dark Theme
//        if(themess == 1){theme = "white";tic = "black";
//            jpane.setBackground(Color.white);} // Light The

    }

    // Fonts
    public void scp(){ // Source Code Pro
        ff = "'Source Code Pro'";
        update();
    }

    public void arial() { // Arial
        ff = "Arial";
       update();
    }

    public void ubuntu() { // Ubuntu
        ff = "Ubuntu";
        update();
    }

    public void exit() { // Exits from the program (Close)
        Platform.exit(); // Closes the program

    }

    // The fonts sizes
    public void n6() {
        ftz = 6; // Font Text Size
        update();
    }

    public void n7() {
        ftz = 7;
        update();
    }

    public void n8() {
        ftz = 8;
        update();
    }

    public void n10() {
        ftz = 10;
        update();
    }

    public void n9() {
        ftz = 9;
        update();
    }

    public void n10p5() {
        ftz = 10.5;
        update();
    }

    public void n11() {
        ftz = 11;
        update();
    }

    public void n12() {
        ftz = 12;
        update();
    }

    public void n13() {
        ftz = 13;
        update();
    }

    public void n14() {
        ftz = 14;
        update();
    }

    public void n15() {
        ftz = 15;
        update();
    }

    public void n16() {
        ftz = 16;
        update();
    }

    public void n18() {
        ftz = 18;
        update();
    }

    public void n20() {
        ftz = 20;
        update();
    }

    public void n22() {
        ftz = 22;
        update();
    }

    public void n24() {
        ftz = 24;
        update();
    }

    public void n26() {
        ftz = 26;
        update();
    }

    public void n28() {
        ftz = 28;
        update();
    }

    public void n32() {
        ftz = 32;
        update();
    }

    public void n36() {
        ftz = 36;
        update();
    }

    public void n40() {
        ftz = 40;
        update();
    }

    public void n44() {
        ftz = 44;
        update();
    }

    public void n48() {
        ftz = 48;
        update();
    }

    public void n54() {
        ftz = 54;
        update();
    }

    public void n60() {
        ftz = 60;
        update();
    }

    public void n66() {
        ftz = 66;
        update();
    }

    public void n72() {
        ftz = 72;
        update();
    }


    public void darkTheme() { // Define DarkTheme
        jpane.setBackground(Color.gray);
    }

    public void lightTheme() { // Define LightTheme
        jpane.setBackground(Color.white);
    }
public String ss="";
    @Override
    public void run() {
        for(int i=0;i<5;i++) {
            ss = jpane.getText();
            System.out.print(ss);

        }
    }


    public void exiter() {
        Alert exitAlert = new Alert(Alert.AlertType.NONE);
        exitAlert.setTitle("Intercode");
        exitAlert.setHeaderText("Do you want to save the changes?");
        exitAlert.setContentText(null);
        ButtonType buttonCancel = new ButtonType("Cancel");
        ButtonType buttonDntSave = new ButtonType("Don't Save");

        exitAlert.getButtonTypes().setAll(buttonDntSave, buttonCancel);
        Optional<ButtonType> result = exitAlert.showAndWait();
        if (buttonDntSave == result.get()) {
            System.exit(0);
        }
        }

    @Override
    public void insertUpdate(DocumentEvent e) {

    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
    public void compile()
    {
        String Os=System.getProperty("os.name");
        tftype.setText(Os);
        if (Os.compareTo("Linux") == 0) {
            Runtime runtime = Runtime.getRuntime();
            try {
                String command = "";

                command = "gcc" + " " + StaticClass.file.getAbsolutePath();

//                command="g++"+" " +file.getAbsolutePath();
//
//                command="javac"+" "+file.getAbsolutePath();
                Process proc = runtime.exec(command, null, StaticClass.file.getParentFile());
                proc.waitFor();
                Scanner scan = new Scanner(proc.getErrorStream());

                if (scan.hasNext()) {
                    tftype.setText("Compilation Failure");


                    while (scan.hasNext())
                        tftype.setText(tftype.getText() + scan.nextLine() + "\n");

                } else {
                    tftype.setText("Code compiled successfully!!!");

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (Os.compareTo("Windows 10")==0) {
            try {
                bottom_left.setText("Detected os : Windows 10");
                new Codecompilation();
//                System.out.println(StaticClass.file.getAbsolutePath());
//                String[] compileCommand = new String[]{"g++", StaticClass.file.getAbsolutePath() + StaticClass.file.getName(), "-o", StaticClass.file.getAbsolutePath() + "exec"};
//                ProcessBuilder builder = new ProcessBuilder(compileCommand);
//                Process comp = builder.start();
//
//                int compileResult = comp.waitFor();
//
//                if(compileResult != 0)
//                    System.out.println(comp.getErrorStream().toString());
//                else
//                    System.out.println("Passed");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
            else{
            bottom_left.setText("Unsupported Operating System");

            }
        }
    }

