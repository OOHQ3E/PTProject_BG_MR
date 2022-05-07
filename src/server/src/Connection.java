import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Connection{
    class ReaderThread extends Thread {
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
                    if (Objects.equals(line, "EXIT")) {
                        this.Exiting();
                    }
                }
                catch (IOException ex) { ex.printStackTrace();}
            }
            System.out.println("Session closed!");
            System.out.println("Connection closed!");
        }
        public void Exiting() {
            connection.Exit();

        }
    }


    private ReaderThread readerThread;

    public Connection(ClientSession session) {
        readerThread = new ReaderThread(this);
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
