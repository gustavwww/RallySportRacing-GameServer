package controller.commandhandlers;

import controller.ClientController;
import controller.GameManager;
import services.protocol.Command;
import services.protocol.ServerProtocol;

public class JoinCommand extends AbstractCommandHandler {

    public JoinCommand() {
        super("join");
    }

    @Override
    protected void concreteHandle(Command cmd, ClientController client, ServerProtocol protocol) {
        if (cmd.getArgs().length == 2) {
            String id = cmd.getArgs()[0];
            String name = cmd.getArgs()[1];

            if (id.equalsIgnoreCase("hub")) {
                client.joinGame(GameManager.getHubGame(), name);
            }

        }
    }


}
