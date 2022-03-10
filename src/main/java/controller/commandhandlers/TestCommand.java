package controller.commandhandlers;

import controller.ClientController;
import services.protocol.Command;
import services.protocol.ServerProtocol;

public class TestCommand extends AbstractCommandHandler {

    public TestCommand() {
        super("test");
    }

    @Override
    protected void concreteHandle(Command cmd, ClientController client, ServerProtocol protocol) {
        // Do something to client
        System.out.println("Test command received from client!");
    }

}
