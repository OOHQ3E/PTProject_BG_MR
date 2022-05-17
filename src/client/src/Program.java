import Classes.Config;
import Classes.Connection;
import Classes.ServerSession;
import loginForm.LoginForm;

public class Program {
    public static void main(String[] args) throws Exception {
        if (Config.loadConfiguration()) {
            ServerSession session = new ServerSession(Config.getIp(), Config.getPort());
            Connection connection = new Connection(session);
            connection.Start();
            LoginForm.main(connection);
            //connection.sendLoginRequest("admin", "admin");
        }
    }
}
