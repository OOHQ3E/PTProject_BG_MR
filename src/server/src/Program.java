import ClientConnection.ClientSession;
import ClientConnection.Connection;
import Database.DBConnection;
import Database.Pixel;
import Database.User;
import Observer.Observer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

public class Program {
    private static String port;
    private static DBConnection dbConnection;
    private static Observer observer = new Observer();
    public static void main(String[] args) {
        if (loadConfiguration()) {
            server();
        }
    }
    private static boolean loadConfiguration() {
        try {
            FileInputStream fis = new FileInputStream("app.config");
            Properties prop = new Properties();
            prop.load(fis);
            port = prop.getProperty("port");
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
    private static void server() {
        try {
            ServerSocket ss = new ServerSocket(Integer.parseInt(port));
            System.out.println("Server started!");
            while (true) {
                Socket s = ss.accept();
                System.out.println("New client!");

                Connection c = new Connection(new ClientSession(s), observer, dbConnection);
                c.Start();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
