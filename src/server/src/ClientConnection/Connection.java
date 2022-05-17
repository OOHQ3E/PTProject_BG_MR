package ClientConnection;

import Database.DBConnection;
import Database.Pixel;
import Database.User;
import Observer.IUpdatable;
import Observer.Observer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;



public class Connection{
    class ReaderThread extends Thread implements IUpdatable {
        Connection connection;

        public ReaderThread(Connection connection) {
            this.connection = connection;
            setStop(false);
        }
        private boolean stop;
        public boolean isStopping() {
            return stop;
        }
        public void setStop(boolean value) {
            stop = value;
        }
        public void run() {
            while (!stop) {
                try {
                    String line = session.readLine();
                    interpret(line);
                    if (Objects.equals(line, "EXIT")) {
                        this.Exiting();
                    }
                }
                catch (IOException ex) {
                    logger.error("Error occurred while reading client input", ex);
                    this.Exiting();
                }
            }
            connection.observer.RemoveUpdatable(this);
            logger.info("Session closed");
        }
        public void Exiting() {
            connection.Exit();
        }
        private boolean isAdminLoggedIn() {
            return connection.currentUser != null && connection.dbConnection.IsAdmin(currentUser.getId());
        }

        private void interpret(String s) {
            String[] data = s.split(";");
            if (Objects.equals(data[0], "Pixel")) {
                connection.observer.Update(s);
                Pixel p = Pixel.convertStringToPixel(s);
                connection.dbConnection.UpdatePixel(p.getX(), p.getY(), p.getR(), p.getG(), p.getB());
            }
            else if (Objects.equals(data[0], "Login")) {
                User u = dbConnection.UserExists(data[1], data[2]);
                connection.currentUser = u;
                if (u == null) {
                    connection.sendCommand("Error;User does not exists!");
                }
                else {
                    connection.sendCommand(u.toString());
                }
            }
            else if (Objects.equals(data[0], "Logout")) {
                if (connection.currentUser != null) {
                    connection.currentUser = null;
                    connection.sendCommand("Message;Logged out!");
                }
            }
            else if (Objects.equals(data[0], "ShowUser")) {
                if (isAdminLoggedIn()) {
                    User u = connection.dbConnection.GetUser(Integer.parseInt(data[1]));
                    connection.sendCommand(u.toString());
                }
                else {
                    connection.sendCommand("Error;You must be an admin to use this feature!");
                    logger.warn("Non admin user tried to access GetUser");
                }
            }
            else if (Objects.equals(data[0], "GetAllUser")) {
                if (isAdminLoggedIn()) {
                    ArrayList<User> users = connection.dbConnection.GetAllUsers();
                    String messageString = "";
                    int count = users.size();
                    for (int index = 0; index < count - 1; index++) {
                        messageString += users.get(index).toString() + ":";
                    }
                    if (count > 1) {
                        messageString += users.get(count - 1);
                    }
                    connection.sendCommand("UserList:" + messageString);
                }
                else {
                    connection.sendCommand("Error;You must be an admin to use this feature!");
                    logger.warn("Non admin user tried to access GetAllUser");
                }
            }
            else if (Objects.equals(data[0], "NewUser")) {
                if (isAdminLoggedIn()) {
                    connection.dbConnection.AddNewUser(data[1], data[2], Integer.parseInt(data[3]));
                    connection.sendCommand("Message;New user added!");
                }
                else {
                    connection.sendCommand("Error;You must be an admin to use this feature!");
                    logger.warn("Non admin user tried to access NewUser");
                }
            }
            else if (Objects.equals(data[0], "DeleteUser")) {
                if (isAdminLoggedIn()) {
                    connection.dbConnection.DeleteUser(Integer.parseInt(data[1]));
                    connection.sendCommand("Message;User deleted!");
                }
                else {
                    connection.sendCommand("Error;You must be an admin to use this feature!");
                    logger.warn("Non admin user tried to access DeleteUser");
                }
            }
            else if (Objects.equals(data[0], "UpdateUser")) {
                if (isAdminLoggedIn()) {
                    connection.dbConnection.UpdateUser(Integer.parseInt(data[1]), data[2], data[3], Integer.parseInt(data[4]));
                    connection.sendCommand("Message;User updated!");
                }
                else {
                    connection.sendCommand("Error;You must be an admin to use this feature!");
                    logger.warn("Non admin user tried to access UpdateUser");
                }
            }
        }

        @Override
        public void Update(String updateString) {
            connection.sendCommand(updateString);
        }
    }


    private ReaderThread readerThread;
    private Observer observer;
    private DBConnection dbConnection;
    private User currentUser = null;
    private Logger logger = LoggerFactory.getLogger("Connection");

    public Connection(ClientSession session, Observer observer, DBConnection dbConnection) {
        readerThread = new ReaderThread(this);
        this.dbConnection = dbConnection;
        this.observer = observer;
        this.session = session;
        setStop(false);
    }
    private final ClientSession session;

    public boolean isStopping() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
        readerThread.setStop(stop);
    }

    private boolean stop;
    public void Start() {
        logger.info("New session started!");
        readerThread.start();
        observer.AddUpdatable(readerThread);
    }
    public void sendCommand(String command) {
        try {
            session.writeLine(command);
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }
    public void Exit() {
        sendCommand("EXIT");
        session.close();
        setStop(true);
    }
}
