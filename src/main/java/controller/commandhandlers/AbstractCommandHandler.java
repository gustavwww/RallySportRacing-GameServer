package controller.commandhandlers;

import controller.ClientController;
import services.protocol.Command;
import services.protocol.ServerProtocol;

abstract class AbstractCommandHandler implements ICommandHandler {

    private ICommandHandler next;
    private final String command;

    protected AbstractCommandHandler(String command) {
        this.command = command;
    }

    @Override
    public ICommandHandler setNext(ICommandHandler next) {
        this.next = next;
        return next;
    }

    @Override
    public void handle(Command cmd, ClientController client, ServerProtocol protocol) {
        if (!cmd.getCommand().equalsIgnoreCase(command)) {
            if (next != null) {
                next.handle(cmd, client, protocol);
            }
            return;
        }

        concreteHandle(cmd, client, protocol);
    }

    protected abstract void concreteHandle(Command cmd, ClientController client, ServerProtocol protocol);
}
