package Classes;

import loginForm.LoginForm;
import userWindow.Draw;
import userWindow.mainwindow;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
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
                    interpret(line);
                    System.out.println(line);
                    if (Objects.equals(line, "EXIT")) {
                        this.Exiting();
                    }
                }
                catch (IOException ex) { this.Exiting(); }
            }
            try {
                session.writeLine("EXIT");
            }
            catch (IOException ex) {}
            session.close();
            System.out.println("Session closed!");
            System.out.println("Classes.Connection closed!");
        }
        private void interpret(String line) {
            String[] data = line.split(";");
            if (Objects.equals(data[0], "Pixel")) {
                Pixel p = Pixel.convertStringToPixel(line);
                Draw.updateBlock(p);
                Draw.paletteRedraw();
                mainwindow.mainWindow.draw.repaint();
                mainwindow.mainWindow.drawSquare();

            }
            else if (Objects.equals(data[0], "Message")) {
                String message = data[1];
                LoginForm.showMessage(message);
            }
            else if (Objects.equals(data[0], "Error")) {
                String error = data[1];
                LoginForm.showError(error);
            }
            else if (Objects.equals(data[0], "User")) {
                User u = User.convertStringToUser(line);
                LoginForm.LoginAttempt(u);

                // valamit ezzel kezdeni idk
            }
            else {
                ArrayList<User> users = User.convertStringToUserList(line);
                if (users != null) {
                    // kiíratni a táblázatba
                }
            }
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
    private void sendCommand(String command) {
        try {
            session.writeLine(command);
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }
    public void sendLoginRequest(String username, String password) {
        sendCommand("Login;"+username+";"+password);
    }
    public void sendLogoutRequest() {
        sendCommand("Logout");
    }
    public void getAllUsers() {
        sendCommand("GetAllUser");
    }
    public void sendNewUser(String username, String password, int authLevel) {
        sendCommand("NewUser;"+username+";"+password+";"+authLevel);
    }
    public void deleteUser(int id) {
        sendCommand("DeleteUser;"+id);
    }
    public void updateUser(User user) {
        sendCommand("Update"+user);
    }
    public void showUser(int id) {
        sendCommand("ShowUser;"+id);
    }

    public void getAllPixel() { sendCommand("GetAllPixel"); }
    public void updatePixel(Pixel pixel) {
        sendCommand(pixel.toString());
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
