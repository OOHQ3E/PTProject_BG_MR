import ClientConnection.ClientSession;
import ClientConnection.Connection;
import Database.DBConnection;
import Observer.Observer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Program {
    private static String port;
    private static DBConnection dbConnection;
    private static Observer observer = new Observer();
    private static Logger mainLogger = LoggerFactory.getLogger("Main");
    public static void main(String[] args) {
        if (loadConfiguration()) {
            for (int i = 0; i < args.length; i++) {
                if (Objects.equals(args[i], "-r") && i + 1 < args.length ) {
                    int number = Integer.parseInt(args[i+1]);
                    dbConnection.ResetPixelCanvas(number);
                    mainLogger.info("Reseted canvas with width " + number);
                    return;
                }
            }
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
            mainLogger.info("Server started");
            while (true) {
                Socket s = ss.accept();
                mainLogger.info("New client connected");

                Connection c = new Connection(new ClientSession(s), observer, dbConnection);
                c.Start();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
