package controller;

import services.TCPListener;
import services.TCPServer;

import java.io.IOException;
import java.net.Socket;

public class ServerController implements TCPListener {

    private TCPServer tcpServer;

    private int currentClientID = 2000;

    public ServerController(int port) {
        try {
            tcpServer = new TCPServer(port);
            tcpServer.addListener(this);

            tcpServer.listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void gotConnection(Socket socket) {
        System.out.println("Client connected to Server with IP Address: " + socket.getInetAddress().getHostAddress());

        ClientController client = new ClientController(socket, currentClientID);
        new Thread(client).start();

        currentClientID++;
    }
}
