package ClientConnection;

import Database.DBConnection;
import Database.Pixel;
import Database.User;
import Observer.IUpdatable;
import Observer.Observer;

import java.io.IOException;
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
                    System.out.println(line);
                    interpret(line);
                    if (Objects.equals(line, "EXIT")) {
                        this.Exiting();
                    }
                }
                catch (IOException ex) { ex.printStackTrace();}
            }
            connection.observer.RemoveUpdatable(this);
            System.out.println("Session closed!");
        }
        public void Exiting() {
            connection.Exit();
        }

        private void interpret(String s) {
            String[] data = s.split(";");
            if (Objects.equals(data[0], "Pixel")) {
                connection.observer.Update(s);
                Pixel p = new Pixel(
                        Integer.parseInt(data[1]),
                        Integer.parseInt(data[2]),
                        Integer.parseInt(data[3]),
                        Integer.parseInt(data[4]),
                        Integer.parseInt(data[5])
                );
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
                connection.currentUser = null;
                connection.sendCommand("Message;Logged out!");
            }
            else if (Objects.equals(data[0], "ShowUser")) {
                User u = connection.dbConnection.GetUser(Integer.parseInt(data[1]));
                connection.sendCommand("Show"+u);
            }
            else if (Objects.equals(data[0], "NewUser")) {
                connection.dbConnection.AddNewUser(data[1],data[2], Integer.parseInt(data[3]));
                connection.sendCommand("Message;New user added!");
            }
            else if (Objects.equals(data[0], "DeleteUser")) {
                connection.dbConnection.DeleteUser(Integer.parseInt(data[1]));
                connection.sendCommand("Message;User deleted!");
            }
            else if (Objects.equals(data[0], "UpdateUser")) {
                connection.dbConnection.UpdateUser(Integer.parseInt(data[1]), data[2], data[3], Integer.parseInt(data[4]));
                connection.sendCommand("Message;User updated!");
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
        System.out.println("Session started!");
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
