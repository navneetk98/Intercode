package sample;

import java.io.File;
public class Codecompilation {
    public Codecompilation()  {
        try {

            String filename = "test1.c";
            String cmd_c = "gcc " + filename + "\n./a.out";
            String cmd_java = "javac " + filename + "\n java " + filename;
            String cmd_cpp = "g++ " + filename + "\n./a.out";
            String[] compileCommand = new String[]{"g++", StaticClass.file.getAbsolutePath() + StaticClass.file.getName(), "-o", StaticClass.file.getAbsolutePath() + "exec"};
            ProcessBuilder processBuilder;
            processBuilder = new ProcessBuilder("cmd", "/C", "gcc " + "\"" + filepath2 + "\\" + name + "\"" + " -o \"" + name2 + "\"");
            File commands=new File("D:\\Java Projects\\Intercode\\src\\FILES\\commands.txt");
            // File where error logs should be written
            File error = new File("D:\\Java Projects\\Intercode\\src\\FILES\\error.txt");

            // File where output.txt should be written
            File output = new File("D:\\Java Projects\\Intercode\\src\\FILES\\output.txt");
            processBuilder.redirectInput(commands);
            processBuilder.redirectOutput(output);
            processBuilder.redirectError(error);
            processBuilder.start();

            }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}