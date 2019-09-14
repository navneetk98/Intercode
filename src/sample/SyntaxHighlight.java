package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.StyledDocument;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;




import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import java.awt.event.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;




public class SyntaxHighlight implements DocumentListener {

    static String edit;
    static int numberTabs=0;
    static StyledDocument doc;
    static String before, after;
    static int numberOfTabs=0;
    static Node Trie;


    static HashMap<String,Integer> keywords = new HashMap<String, Integer>();

    public static void  readkeywords() throws IOException {
        String curDir=System.getProperty("user.dir");

        String filePath = "D:\\Java Projects\\Intercode\\src\\sample\\Keywords\\c";
        BufferedReader rd = new BufferedReader(new FileReader(filePath));
        String key;
        Trie = new Node();
        while( (key=rd.readLine()) != null)
        {
            keywords.put(key,1);
            Trie.addWord(key);
            System.out.println(key);
        }

        doc.addDocumentListener(new SyntaxHighlight());

    }



    public static void setChange(DocumentEvent e)
    {
        System.out.println("Inside set change ");
        int offset = e.getOffset();
        String last,later;
        last=later="";

        try{


            int i=offset-1;

            while(i>=0)
            {
                String cur = doc.getText(i,1);
                i--;
                char curchar=cur.charAt(0);
                System.out.println(curchar);
                if( (curchar>='a' && curchar<='z') || (curchar>='A' && curchar<='Z') )
                {
                    last = curchar+last;
                }else{
                    break;
                }
            }

            i=offset;

            while(true)
            {
                try {
                    String t = doc.getText(i, 1);
                    char temp = t.charAt(0);


                    if( (temp>='a' && temp<='z') || (temp>='A' && temp<='Z') )
                    {
                        later+=temp;
                    }else{
                        break;
                    }

                }catch(BadLocationException ex) {
                    System.out.println("Reached end of the file....");
                    break;
                }
                i++;
            }


            System.out.println(last);
            System.out.println(later);
            System.out.println(offset);

            before=last;
            after=later;

        }catch (BadLocationException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void insertUpdate(DocumentEvent e) {


        setChange(e);

        int offset = e.getOffset();
        int len = e.getLength();


        if(len>=2)
        {
            changeColor(e);
            return ;
        }

        String temp="";

        try {
             temp = doc.getText(offset,1);
        } catch (BadLocationException e1) {
            e1.printStackTrace();
        }

        if(temp.compareTo("\n")==0)
        {
            numberTabs=0;
        } else if(temp.compareTo("\t")==0)
        {
            numberTabs++;
        }else if(temp.compareTo("(")==0){




            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        doc.insertString(offset+1,")",new SimpleAttributeSet() );
                    } catch (BadLocationException ex) {
                        ex.printStackTrace();
                    }
                }
            });



        }else if(temp.compareTo("{")==0)
        {


            String tab="";
            for(int i=0; i<numberTabs; i++)
            {
                tab+="\t";
            }


            String finalTab = tab;
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        doc.insertString(offset+1,"\n\n"+ finalTab +"}",new SimpleAttributeSet() );
                    } catch (BadLocationException ex) {
                        ex.printStackTrace();
                    }
                }
            });



        }else{

            String search = before+after;

            List<String> autoComplete;

            autoComplete = Trie.getWords(search);


//Pass it over in this manner

            ObservableList<String> observableList = FXCollections.observableList(autoComplete);
          //  StaticClass.combo = new ComboBox<String>();
            StaticClass.list_id.setItems(observableList);
          // StaticClass.combo.setItems(observableList);

            SimpleAttributeSet redColor = new SimpleAttributeSet();
            SimpleAttributeSet blackColor = new SimpleAttributeSet();
            StyleConstants.setItalic(redColor,true);
            StyleConstants.setForeground(redColor,Color.red);
            StyleConstants.setForeground(blackColor,Color.black);


            if(keywords.containsKey(search))
            {

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        doc.setCharacterAttributes(offset-before.length(),search.length(),redColor,true);
                    }
                });

            }else{

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        doc.setCharacterAttributes(offset-before.length(),search.length(),blackColor,true);
                    }
                });
            }


        }

    }

    @Override
    public void removeUpdate(DocumentEvent e) {

        setChange(e);
        int offset = e.getOffset();
        String last = before;
        String later = after;

            // if(keywords.containsKey(last))
            {
                SimpleAttributeSet redColor = new SimpleAttributeSet();
                SimpleAttributeSet blackColor = new SimpleAttributeSet();
                StyleConstants.setItalic(redColor,true);
                StyleConstants.setForeground(redColor,Color.red);
                StyleConstants.setForeground(blackColor,Color.black);
                String search = last+later;

                if(keywords.containsKey(search))
                {

                    String finalLast = last;
                    String finalLater = later;
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            doc.setCharacterAttributes(offset- finalLast.length(), finalLast.length()+finalLater.length(),redColor,true);
                        }
                    });

                }else{
                    String finalLast = last;
                    String finalLater = later;
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            doc.setCharacterAttributes(offset- finalLast.length(), finalLast.length()+finalLater.length(),blackColor,true);
                        }
                    });
                }
            }

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }



    public void changeColor(DocumentEvent e)
    {
        int offset = e.getOffset();
        int len = e.getLength();

        String cpyText="";

        try{
            cpyText = doc.getText(offset,len);

        }catch(BadLocationException ex){
            System.out.println(ex.getMessage());
        }

        String buffer="";

        for(int i=0; i<cpyText.length(); i++)
        {
            if( (cpyText.charAt(i)>='a' && cpyText.charAt(i)<='z') ||  (cpyText.charAt(i)>='A' && cpyText.charAt(i)<='Z'))
            {
                buffer+=cpyText.charAt(i);
            }else{
                buffer="";
            }

            if(keywords.containsKey(buffer))
            {
                String finalBuffer = buffer;
                int finalI = i;
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
                        StyleConstants.setItalic(attributeSet, true);
                        StyleConstants.setForeground(attributeSet, Color.red);
                        doc.setCharacterAttributes(offset+ finalI -(finalBuffer.length())+1, finalBuffer.length(), attributeSet, true);
                    }
                });
            }

        }


    }
}
