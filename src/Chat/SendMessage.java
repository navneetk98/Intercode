package Chat;

import sample.StaticClass;

import java.io.*;
import java.util.StringTokenizer;

public class SendMessage {
    private static ObjectOutputStream objectOutputStream;

    public SendMessage(ObjectOutputStream objectOutputStream) {
        this.objectOutputStream = objectOutputStream;
    }


//    @Override
//    public void run() {
        public static void on_button(String str) {
         //   BufferedReader bufferedReader = new BufferedReader(str);
            StringTokenizer st = new StringTokenizer(str,"#");
            String msg=st.nextToken();
            String to=st.nextToken();
//            try {
//                System.out.println("enter message");
//                msg = bufferedReader.readLine();
//            }
//            catch (IOException e) {
//                e.printStackTrace();
//            }
//            System.out.println("enter the recipient");
//            try {
//                to = bufferedReader.readLine();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            Message message = new Message(msg, to);
            try {
               StaticClass.chat_objectOutput.writeObject(message);
                StaticClass.chat_objectOutput.flush();
                StaticClass.chat_input.clear();
                StaticClass.chat_text.setText(StaticClass.chat_text.getText()+"\n"+"Me: "+msg);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

//    }
}
