package services;

import java.net.Socket;

public interface TCPListener {
    void gotConnection(Socket socket);
}
