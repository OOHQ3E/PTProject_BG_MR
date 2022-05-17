package Classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static int port;
    private static String ip;
    public static boolean loadConfiguration()
    {
        try {
            FileInputStream fis = new FileInputStream("app.config");
            Properties prop = new Properties();
            prop.load(fis);
            ip = prop.getProperty("ip");
            port = Integer.parseInt(prop.getProperty("port"));
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

    public static int getPort() {
        return port;
    }

    public static String getIp() {
        return ip;
    }
}
