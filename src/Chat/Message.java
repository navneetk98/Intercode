package Chat;

import java.io.Serializable;

public class Message implements Serializable {
    private String message;
    //private String from;
    private String to;

    public Message(String message, String to) {
        this.message = message;
        //this.from = from;
        this.to = to;
    }

    public String getMessage()
    {
        return this.message;
    }


    public String getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        final String format = String.format("Message : %s\nTo : %s", message, to);
        return format;
    }
}