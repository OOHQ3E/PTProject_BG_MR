package Classes;

import Classes.Pixel;
import Classes.User;

import java.io.*;
import java.net.Socket;

public class ServerSession {
    private InputStreamReader isr;
    private BufferedReader incoming;

    private OutputStream os;
    private PrintWriter outgoing;

    private Socket socket;
    public ServerSession(String ip, int port) throws IOException {
        Socket newSocket = new Socket(ip, port);
        this.socket = newSocket;
        isr = new InputStreamReader(newSocket.getInputStream());
        incoming = new BufferedReader(isr);

        os = newSocket.getOutputStream();
        outgoing = new PrintWriter(os);
    }

    public String readLine() throws IOException {
        return incoming.readLine();
    }

    public void writeLine(String value) throws IOException {
        outgoing.println(value);
        outgoing.flush();
    }




    public void close() {
        try {
            incoming.close();
        }
        catch (IOException ex) {}
        try {
            isr.close();
        }
        catch (IOException ex) {}
        try {
            os.close();
        }
        catch (IOException ex) {}
        try {
            outgoing.close();
        }
        catch (Exception ex) {}
        try {
            socket.close();
        }
        catch (IOException ex) {}
    }
}
