import ClientConnection.ClientSession;
import ClientConnection.Connection;
import Config.Config;
import Database.DBConnection;
import Observer.Observer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Program {
    private static int port;
    private static DBConnection dbConnection;
    private static Observer observer = new Observer();
    private static Logger mainLogger = LoggerFactory.getLogger("Main");
    public static void main(String[] args) {
        if (Config.loadConfiguration()) {
            dbConnection = Config.dbConnection;
            port = Config.port;
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
    private static void server() {
        try {
            ServerSocket ss = new ServerSocket(port);
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
