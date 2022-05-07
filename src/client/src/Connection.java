import java.io.IOException;
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
                catch (IOException ex) { this.setStop(true); }
            }
            try {
                session.writeLine("EXIT");
            }
            catch (IOException ex) {}
            session.close();
            System.out.println("Session closed!");
            System.out.println("Connection closed!");
        }
        public void Exiting() {
            connection.setStop(true);
        }
    }


    private ReaderThread readerThread;

    public Connection(ServerSession session) {
        readerThread = new ReaderThread(this);
        this.session = session;
        setStop(false);
    }
    private final ServerSession session;

    public boolean isStoped() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
        readerThread.setStop(stop);
    }

    private boolean stop;


    public void Session() {
        System.out.println("Connected and session started!");
        readerThread.start();
        while (!stop) {
            try {
                session.writeLine("Commands"); Thread.sleep(100);

            }
            catch (Exception ex) {
                System.out.println("Exception!");
                setStop(true);
            }
        }
        while (readerThread.isAlive()) {}
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
    public void Start() {
        System.out.println("Connected and session started!");
        readerThread.start();
    }
    public void Exit() {
        sendCommand("EXIT");
        session.close();
        setStop(true);
        while (readerThread.isAlive()) { }
    }
}
