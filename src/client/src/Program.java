import Classes.Pixel;

import java.io.*;
import java.util.Properties;

public class Program {
    private static String port;
    private static String ip;
    public static void main(String[] args) throws Exception {
        loadConfiguration();

        ServerSession session = new ServerSession(ip, Integer.parseInt(port));
        Connection connection = new Connection(session);
        connection.Start();
        connection.sendLoginRequest("admin", "admin");
        Thread.sleep(5000);
        connection.sendLogoutRequest();
        connection.Exit();
    }
    private static boolean loadConfiguration()
    {
        try {
            FileInputStream fis = new FileInputStream("app.config");
            Properties prop = new Properties();
            prop.load(fis);
            port = prop.getProperty("port");
            ip = prop.getProperty("ip");
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
