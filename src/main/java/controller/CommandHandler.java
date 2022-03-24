package controller;

import controller.commandhandlers.ICommandHandler;
import controller.commandhandlers.JoinCommand;
import controller.commandhandlers.PosCommand;
import controller.commandhandlers.TestCommand;
import services.protocol.Command;
import services.protocol.ServerProtocol;

public class CommandHandler {

    private final ClientController client;
    private final ServerProtocol protocol;

    private ICommandHandler firstHandler;

    public CommandHandler(ClientController client) {
        this.client = client;
        protocol = ServerProtocol.getInstance();

        setupHandlers();
    }

    private void setupHandlers() {

        firstHandler = new TestCommand();
        firstHandler.setNext(new JoinCommand()).setNext(new PosCommand());
    }

    public void handleCommand(Command cmd) {
        firstHandler.handle(cmd, client, protocol);
    }

}
