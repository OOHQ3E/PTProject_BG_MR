import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class Program {
    private static String port;
    public static void main(String[] args) throws Exception {
        if (loadConfiguration()) {
            ServerSocket ss = new ServerSocket(Integer.parseInt(port));
            System.out.println("Server started!");
            while (true) {
                Socket s = ss.accept();
                System.out.println("New client!");

                Connection c = new Connection(new ClientSession(s));
                c.Start();
            }
        }
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
