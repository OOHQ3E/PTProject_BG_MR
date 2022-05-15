package ClientConnection;

import java.io.*;
import java.net.Socket;

public class ClientSession {
    private InputStreamReader isr;
    private BufferedReader incoming;
    private OutputStream os;
    private PrintWriter outgoing;
    private Socket socket;
    public ClientSession(Socket newSocket) throws IOException {
        socket = newSocket;
        isr = new InputStreamReader(socket.getInputStream());
        incoming = new BufferedReader(isr);

        os = socket.getOutputStream();
        outgoing = new PrintWriter(os);
    }
    public String readLine() throws IOException {
        return incoming.readLine();
    }

    public boolean hasNewLine() throws IOException {
        return isr.ready();
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
