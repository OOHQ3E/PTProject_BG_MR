import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Program {
    private static String port;
    public static void main(String[] args) {
        loadConfiguration();
        System.out.println(port);
    }
    private static boolean loadConfiguration()
    {
        try {
            FileInputStream fis = new FileInputStream("app.config");
            Properties prop = new Properties();
            prop.load(fis);
            port = prop.getProperty("port");
            fis.close();
            return true;
        }
        catch (FileNotFoundException ex) {
            System.out.println("File app.config could not be found!");
            ex.printStackTrace();
        }
        catch (IOException ex) {
            System.out.println("Unexpected IO error!");
            ex.printStackTrace();
        }
        return false;
    }
}
