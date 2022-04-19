import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class LogWriter {
    private static boolean working=false;
    private static FileWriter fw;

    public static void init() {
        try {
            fw = new FileWriter("Log.log", false);
            working = true;
        } catch (IOException e) {
            working = false;
            e.printStackTrace();
        }
    }

    public void println(String str) {
        if(!working) {
            System.out.println(str);
            return;
        }
        try {
            fw.write(str + "\n");
            fw.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            working = false;
            e.printStackTrace();
        }
    }

    protected void finalize() {
        try {
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void printResult(String str, List<List<Integer>> result) {
        if(!working) {
            System.out.println(str + result);
            return;
        }
        try {
            fw.write(str + "[\n");
            fw.flush();
            for(List<Integer> i: result) {
                fw.write(i+",\n");
                fw.flush();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            working = false;
            e.printStackTrace();
        }
    }
}
