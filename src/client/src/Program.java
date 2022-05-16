import Classes.Pixel;
import Classes.User;
import userWindow.mainwindow;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class Program {
    public static void main(String[] args) throws Exception {
        mainwindow.main(args);
        if (Config.loadConfiguration()) {
            ServerSession session = new ServerSession(Config.getIp(), Config.getPort());
            Connection connection = new Connection(session);
            connection.Start();
            connection.sendLoginRequest("admin", "admin");
            Thread.sleep(500);
            connection.sendLogoutRequest();
            connection.Exit();
        }
    }
}
