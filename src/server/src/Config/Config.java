package Config;

import Database.DBConnection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {
    public static DBConnection dbConnection;
    public static int port;
    public static boolean loadConfiguration() {
        try {
            FileInputStream fis = new FileInputStream("app.config");
            Properties prop = new Properties();
            prop.load(fis);
            port = Integer.parseInt(prop.getProperty("port"));
            String DBHost = prop.getProperty("db_host");
            String DBName = prop.getProperty("db_name");
            String DBUserName = prop.getProperty("db_username");
            String DBPassword = prop.getProperty("db_password");
            dbConnection = new DBConnection(DBHost, DBName, DBUserName,DBPassword);
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
