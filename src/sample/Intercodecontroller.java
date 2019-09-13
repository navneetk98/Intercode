package sample;

// JavaFX All

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.embed.swing.SwingNode;
import javax.swing.event.DocumentListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
// JavaFX beans
// JavaFX fxml
// JavaFX scene
// JavaFX stage
// JavaFX IO
// initialize


public class Intercodecontroller implements Initializable, Runnable, DocumentListener {
    private double ftz = 24; // Font Text Size
    private String ff = "Ubuntu"; // Font Family
    private String tic = "white"; // Text Inner Color
    private int themess = 0; // Theme S selected
    private ToggleGroup themer = new ToggleGroup(); // The group that contains the radio buttons
    private File savedFile; // The file has been saved
    private Boolean savedFileB = false; // If is to do the simple save
    private String savedFileExt; // The extension of the file has been saved
    private Boolean saveAs = false; // If to do the save as
    private BooleanProperty controlKey = new SimpleBooleanProperty(false);
    private BooleanProperty nKey = new SimpleBooleanProperty(false);
    private BooleanProperty oKey = new SimpleBooleanProperty(false);
    private BooleanProperty sKey = new SimpleBooleanProperty(false);
    private final BooleanBinding newShortcut = controlKey.and(nKey);
    private final BooleanBinding openShortcut = controlKey.and(oKey);
    private final BooleanBinding saveShortcut = controlKey.and(sKey);

    public JTextPane jpane;






    @FXML
    public TextArea tftype; // The text typed for the USER
    public RadioMenuItem lightbt; // Button to change to light theme (themer)
    public RadioMenuItem darkbt; // Button to change to dark theme (themer)
    public Parent Vboxmain; // The head of all
    public SwingNode swingNode;

    public Document doc;

    public ComboBox combo;
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

      //  swingNode.resize(1000,1000);
      // tftype.setText("Hello122satvik");

        swingNode.setContent(scroller);
       // swingNode.setContent(scroller);

       jpane.setText("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
     //   jpane.setSize(500,500);
        System.out.println(jpane.getText());
       // jpane.setBounds(5,5,15,15);
//        tftype.setWrapText(true); // Set the tftype can be used like a text editor
        lightbt.setToggleGroup(themer); // Sets the ToogleGroup of the lightbt (Every Radio Button needs a ToogleGroup)
        darkbt.setToggleGroup(themer); // Sets the ToogleGroup of the darkbt (Every Radio Button needs a ToogleGroup)
        darkbt.setSelected(true); // Sets the darkbt button bes selected or as default option
        StaticClass.combo=combo;
//        tftype.setText("HEllo World noobies");

    }


    public void save()
    {
System.out.println("Chacha method called");

        String text="";

        try{
            int len =SyntaxHighlight.doc .getLength();
             text = SyntaxHighlight.doc.getText(0,len);


            System.out.println(text);
        }catch (Exception ex){
            System.out.println("Error");
            System.out.println(ex.getMessage());
        }

    try{
        File file = new File("test1.txt");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(text);
        fileWriter.flush();
    }
    catch(Exception ex){
        System.out.println(ex.getMessage());
    }


    }


    // Archive (Save) System
   /* public void save() throws IOException, java.lang.NullPointerException { // Save what was typed for USER
        if (!savedFileB || saveAs) {
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Text document (*.txt)", "*.txt"); // Creates a filter with the description "Text Document (*.txt)" (The description is what shows when you selects the extension) and with the extension "*txt" or "*txy" or "*.txt"
            FileChooser.ExtensionFilter filter2 = new FileChooser.ExtensionFilter("All Files (.*.)", "*"); // Creates a filter with the description ""All Files (.*.)" (The description is what shows when you selects the extension) and with the extension "*.*"
            if(jpane == null){
                System.out.println("null");
            }
            FileChooser escoger = new FileChooser(); // Creates a FileChooser (It's in the name)
            if(saveAs){escoger.setTitle("Save As - Intercode");}
            else{escoger.setTitle("Save - Intercode");}
            escoger.getExtensionFilters().addAll(filter, filter2); // Adds the extension created before
            Stage vista = (Stage) Vboxmain.getScene().getWindow(); // Creates a Stage variable using the Vboxmain as window.
            File file = escoger.showSaveDialog(vista); // Start the fileChooser (escoger)
            String extension = ""; // creates a variable to be used to the file be saved with the right extension (gonna be sense)
            if (file != null) { // If file is null is because has a error
                if (!file.toString().contains(".txt")) { // If the

                    if (file.getCanonicalPath().endsWith("txt")) {
                        extension = "*.txt";
                    } else {
                        extension = null;
                    }
                }
                FileWriter f = new FileWriter(file + extension);
                //f.write(txtSaving);
                f.close();
                savedFileB = true;
                savedFile = file;
                savedFileExt = extension;
                saveAs = false;
            }
            try {
                if (file != null) {
                    ((Stage) Vboxmain.getScene().getWindow()).setTitle(file.getName() + "Intercode");
                }
            } catch (java.lang.NullPointerException e) {
                e.printStackTrace();
            }

        } else {
            FileWriter f = new FileWriter(savedFile + savedFileExt);
            f.write(jpane.getText());
            f.close();
        }
    }*/
public void onkey()
{

    if (combo.getValue() != null &&
            !combo.getValue().toString().isEmpty()){

        System.out.println(combo.getValue().toString());
}
//public void liste()
//{
//    //System.out.println("Chchcahibfabafjadf");
}
    public void load() throws IOException { // Load a archive
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Text document (*.txt)", "*.txt");
        FileChooser.ExtensionFilter filter2 = new FileChooser.ExtensionFilter("All Files (.*.)", "*");
        FileChooser escoger = new FileChooser();
        escoger.getExtensionFilters().addAll(filter, filter2);
        Stage vista = (Stage) Vboxmain.getScene().getWindow();
        File file = escoger.showOpenDialog(vista);

        if (file != null) {
            FileReader reader = new FileReader(file);
            BufferedReader readerl = new BufferedReader(reader);
            String linha = readerl.readLine();
            jpane.setText("");
            while (linha != null) {
                jpane.setText(jpane.getText() + linha + "\n");
                linha = readerl.readLine();
            }
            reader.close();
        }
        try {
            if (file != null) {
                ((Stage) Vboxmain.getScene().getWindow()).setTitle(file.getName() + " - SSG Note Block");
            }

        } catch (java.lang.NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void saveAs() {
        saveAs = true;
//        try {
//            save();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void news() {
        jpane.setText(null);
    }

    private void timerKeyPress(BooleanProperty x){
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
//                try {
//                    save();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        });
    }

    // Updates System
    private void update() { // Updates the settings and personalization of the program.
        String theme = "null";
        if(themess == 0){theme = "black";tic = "white";
       // jpane.setFont();
        jpane.setBackground(Color.gray);
        } // Dark Theme
        if(themess == 1){theme = "white";tic = "black";
            jpane.setBackground(Color.white);} // Light Theme

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
        themess = 0; // Number of the theme; 0 = dark theme
        update();
    }

    public void lightTheme() { // Define LightTheme
        themess = 1; // Number of the theme; 1 = light theme
        update();

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
        exitAlert.setTitle("SSC Note Block");
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
        if(Os.compareTo("Linux")==0){
            bottom_left.setText("Detected os : Linux");









//                Runtime runtime=Runtime.getRuntime();
//                try{
//                    String command="";
//                   // if(cfilter.accept(file))
//                        command="gcc"+" "+file.getAbsolutePath();
//                   // if(cppfilter.accept(file))
//                     //   command="g++"+" " +file.getAbsolutePath();
//                   // if(javafilter.accept(file))
//                       // command="javac"+" "+file.getAbsolutePath();
//                    Process proc=runtime.exec(command,null,file.getParentFile());
//                    proc.waitFor();
//                    Scanner scan=new Scanner(proc.getErrorStream());
//                    StyledDocument doc = OutputArea.getStyledDocument();
//                    if(scan.hasNext()){
//                        OutputArea.setText("");
//                        doc.insertString(doc.getLength(),"Compilation Error \n",failure);
//                        while(scan.hasNext())
//                            doc.insertString(doc.getLength()," "+scan.nextLine()+"\n",null);
//                        return 0;
//                    }
//                    else{
//                        OutputArea.setText("");
//                        doc.insertString(doc.getLength(),"Code compiled successfully!!!",success);
//                        return 1;
//                    }
//                }
//                catch(IOException e){
//                    JOptionPane.showMessageDialog(dialog,"Input Output Exception Occurred");
//                    return 0;
//                }
//                catch(InterruptedException e){
//                    JOptionPane.showMessageDialog(dialog,"Process Interrupted");
//                    return 0;
//                } catch (BadLocationException ex) {
//                    System.out.println("Internal Error In Output Area !!!!!!!!");
//                    return 0;
//                }
            }
        if (Os.compareTo("Windows 10")==0) {
            bottom_left.setText("Detected os : Windows 10");


        }
            else{
            bottom_left.setText("Unsupported Operating System");

            }
        }
    }


//    private int compile(){
////        int index=TabbedPane.getSelectedIndex();
////        String filepath=Eureka.FilePath[index];
////        if(filepath==null){
////            JOptionPane.showMessageDialog(dialog,"File cannot be compiled without saving please first save file and then try to compile");
////            return 0;
////        }
////        else{
//        save();
//
//
//            String Os=System.getProperty("os.name");
//            if(Os.compareTo("Linux")==0){
//                Runtime runtime=Runtime.getRuntime();
//                try{
//                    String command="";
//                   // if(cfilter.accept(file))
//                        command="gcc"+" "+file.getAbsolutePath();
//                   // if(cppfilter.accept(file))
//                     //   command="g++"+" " +file.getAbsolutePath();
//                   // if(javafilter.accept(file))
//                       // command="javac"+" "+file.getAbsolutePath();
//                    Process proc=runtime.exec(command,null,file.getParentFile());
//                    proc.waitFor();
//                    Scanner scan=new Scanner(proc.getErrorStream());
//                    StyledDocument doc = OutputArea.getStyledDocument();
//                    if(scan.hasNext()){
//                        OutputArea.setText("");
//                        doc.insertString(doc.getLength(),"Compilation Error \n",failure);
//                        while(scan.hasNext())
//                            doc.insertString(doc.getLength()," "+scan.nextLine()+"\n",null);
//                        return 0;
//                    }
//                    else{
//                        OutputArea.setText("");
//                        doc.insertString(doc.getLength(),"Code compiled successfully!!!",success);
//                        return 1;
//                    }
//                }
//                catch(IOException e){
//                    JOptionPane.showMessageDialog(dialog,"Input Output Exception Occurred");
//                    return 0;
//                }
//                catch(InterruptedException e){
//                    JOptionPane.showMessageDialog(dialog,"Process Interrupted");
//                    return 0;
//                } catch (BadLocationException ex) {
//                    System.out.println("Internal Error In Output Area !!!!!!!!");
//                    return 0;
//                }
//            }
//            else{
//                JOptionPane.showMessageDialog(dialog,"Unsupported Operating System");
//                return 0;
//            }
//        }
//    }





