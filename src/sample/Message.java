package sample;

import java.io.Serializable;

public class Message implements Serializable {

    String edit;
    boolean isInterviewer;

    Message(String edit, boolean isInterviewer)
    {

        this.edit=edit;
        this.isInterviewer=isInterviewer;

    }
}
