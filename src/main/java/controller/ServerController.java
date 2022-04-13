package controller;

import services.TCPListener;
import services.TCPServer;
import services.UDPServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ServerController implements TCPListener {

    private static final int PORT = 2005;

    private static ServerController instance = null;

    private UDPServer udpServer;

    private int currentClientID = 2000;

    private ServerController() {}

    public void begin() {
        try {
            udpServer = new UDPServer(PORT);
            new Thread(udpServer).start();

            TCPServer tcpServer = new TCPServer(PORT);
            tcpServer.addListener(this);

            tcpServer.listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void disconnectClient(int clientID) {
        udpServer.removeListener(clientID);
    }

    public synchronized void sendUDPPacket(InetAddress address, int port, String message) {
        try {
            udpServer.sendPacket(address, port, message);
        } catch (IOException ignored) {}
    }

    @Override
    public void gotConnection(Socket socket) {
        System.out.println("Client connected to Server with IP Address: " + socket.getInetAddress().getHostAddress());

        ClientController client = new ClientController(socket, currentClientID);
        new Thread(client).start();

        udpServer.addListener(currentClientID, client);

        currentClientID++;
    }

    public static synchronized ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }

        return instance;
    }

}
