package controller;

import services.protocol.ServerProtocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientController implements Runnable {

    private final Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public ClientController(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream(), true);

            ServerProtocol protocol = ServerProtocol.getInstance();
            CommandHandler commandHandler = new CommandHandler(this);

            String input;
            while((input = reader.readLine()) != null) {

                commandHandler.handleCommand(protocol.parseMessage(input));

            }

            disconnect();

        } catch (IOException e) {
            e.printStackTrace();
            disconnect();
        }


    }

    private void disconnect() {
        try {
            System.out.println("Client disconnected from Server: " + socket.getInetAddress().getHostAddress());
            socket.close();

        } catch (IOException ignored){};
    }


}
