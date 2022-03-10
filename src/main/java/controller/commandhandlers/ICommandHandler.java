package controller.commandhandlers;

import controller.ClientController;
import services.protocol.Command;
import services.protocol.ServerProtocol;

public interface ICommandHandler {

    void handle(Command cmd, ClientController client, ServerProtocol protocol);
    ICommandHandler setNext(ICommandHandler next);

}
