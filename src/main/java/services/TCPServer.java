package services;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TCPServer {

    private final int port;
    private final ServerSocket serverSocket;

    private final List<TCPListener> listeners = new ArrayList<>();

    public void addListener(TCPListener listener) {
        listeners.add(listener);
    }

    public TCPServer(int port) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
    }

    public void listen() {
        System.out.println("Server started listening on port " + port);

        while(true) {
            try {
                Socket client = serverSocket.accept();
                informGotConnection(client);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void informGotConnection(Socket socket) {
        for (TCPListener l : listeners) {
            l.gotConnection(socket);
        }
    }


}
