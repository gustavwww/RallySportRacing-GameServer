package controller.commandhandlers;

import controller.ClientController;
import services.protocol.Command;
import services.protocol.ServerProtocol;

public class GetCommand extends AbstractCommandHandler {

    public GetCommand() {
        super("get");
    }

    @Override
    protected void concreteHandle(Command cmd, ClientController client, ServerProtocol protocol) {
        String[] args = cmd.getArgs();

        if (args.length < 1) {
            return;
        }

        if (args[0].equalsIgnoreCase("times")) {
            if (client.getGame() == null) return;

            client.sendTCP(protocol.parseTimes(client.getGame()));
        }

    }


}
