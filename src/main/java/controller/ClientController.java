package controller;

import data.Address;
import model.Game;
import model.Player;
import services.PacketListener;
import services.protocol.ServerProtocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;

public class ClientController implements Runnable, PacketListener {

    private final Socket socket;
    private final int clientID;

    private Address udpAddress = null;

    private BufferedReader reader;
    private PrintWriter writer;
    private final ServerProtocol protocol;
    private CommandHandler commandHandler;

    private Game game = null;
    private Player player = null;

    public ClientController(Socket socket, int clientID) {
        this.socket = socket;
        this.clientID = clientID;
        protocol = ServerProtocol.getInstance();
    }

    public void joinGame(Game game, String name) {
        if (this.game != null) {
            sendTCP(protocol.writeError("Already in a game!"));
            return;
        }

        this.game = game;
        player = new Player(clientID, name, this);
        game.addPlayer(player);

        sendTCP(protocol.writeSuccess("joined"));
    }

    public void leaveGame() {
        if (game == null || player == null) {
            return;
        }

        game.removePlayer(player);
        game = null;
        player = null;
    }

    public void sendTCP(String msg) {
        if (writer == null) return;
        writer.println(msg);
    }

    @Override
    public void run() {

        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream(), true);

            commandHandler = new CommandHandler(this);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            if (!socket.isConnected()) {
                                disconnect();
                                break;
                            }
                            Thread.sleep(20000);
                        } catch (Exception ignored) {}
                    }
                }
            }).start();

            sendTCP("connect:" + clientID);

            String input;
            while((input = reader.readLine()) != null) {
                commandHandler.handleCommand(protocol.parseMessage(input));
            }

        } catch (IOException ignored) {}

        disconnect();
    }

    @Override
    public void gotPacket(InetAddress address, int port, String message) {
        if (udpAddress == null) {
            udpAddress = new Address(address, port);
        }

        commandHandler.handleCommand(protocol.parseMessage(message));
    }

    private void disconnect() {
        try {
            socket.close();
        } catch (IOException ignored){};

        leaveGame();
        ServerController.getInstance().disconnectClient(clientID);
        System.out.println("Client disconnected from Server: " + socket.getInetAddress().getHostAddress());
    }

    public Player getPlayer() {
        return player;
    }

    public Address getAddress() {
        return udpAddress;
    }

}
