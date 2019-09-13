package sample;

import java.io.File;
import java.util.*;
public class Codecompilation {
    public Codecompilation()
    {
        String filename="";
        String cmd_c="gcc "+filename+"\n./a.out";
        String cmd_java="javac "+filename+"\n java "+filename;
        String cmd_cpp
        ProcessBuilder processBuilder=new ProcessBuilder("");
        // File where error logs should be written
        File error = new File("FILES/error.txt");

        // File where output.txt should be written
        File output = new File("FILES/output.txt");


        processBuilder.redirectOutput(output);
        processBuilder.redirectError(error);

    }
}
