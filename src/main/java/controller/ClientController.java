package controller;

import model.Game;
import model.Player;
import services.protocol.ServerProtocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientController implements Runnable {

    private final Socket socket;
    private final int clientID;

    private BufferedReader reader;
    private PrintWriter writer;
    ServerProtocol protocol;

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

            CommandHandler commandHandler = new CommandHandler(this);

            sendTCP("connect:" + clientID);

            String input;
            while((input = reader.readLine()) != null) {

                commandHandler.handleCommand(protocol.parseMessage(input));
            }

            disconnect();

        } catch (IOException e) {
            disconnect();
        }

    }

    private void disconnect() {
        try {
            System.out.println("Client disconnected from Server: " + socket.getInetAddress().getHostAddress());
            socket.close();

            leaveGame();

        } catch (IOException ignored){};
    }

    public Player getPlayer() {
        return player;
    }
}
