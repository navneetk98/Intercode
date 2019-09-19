package Chat;

import sample.StaticClass;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ReceiveMessage implements Runnable {
    private ObjectInputStream objectInputStream;
    public ReceiveMessage(ObjectInputStream objectInputStream) {
        this.objectInputStream=objectInputStream;
    }

    @Override
    public void run() {

        while(true) {

            try {
                Message message = (Message) objectInputStream.readObject();
                StaticClass.chat_text.setText(StaticClass.chat_text.getText()+"\n"+message.getMessage());
                System.out.println(message);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return;
            }
        }

    }
}
