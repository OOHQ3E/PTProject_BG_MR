import java.io.*;
import java.util.Properties;

public class Program {
    private static String port;
    private static String ip;
    public static void main(String[] args) throws Exception {
        loadConfiguration();
        System.out.println(port);
        System.out.println(ip);

        ServerSession session = new ServerSession(ip, Integer.parseInt(port));
        Connection connection = new Connection(session);
        connection.Start();
        connection.sendCommand("Command1");
        connection.sendCommand("Command2");

        connection.sendCommand("Command3");
        Thread.sleep(5000);
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
