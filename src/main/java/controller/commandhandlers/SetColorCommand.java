package controller.commandhandlers;

import controller.ClientController;
import services.protocol.Command;
import services.protocol.ServerProtocol;

public class SetColorCommand extends AbstractCommandHandler {

    public SetColorCommand() {
        super("setcolor");
    }

    @Override
    protected void concreteHandle(Command cmd, ClientController client, ServerProtocol protocol) {
        String[] args = cmd.getArgs();
        if (args.length < 1) {
            return;
        }

        if (client.getPlayer() == null) {
            return;
        }

        try {

            int colorIndex = Integer.parseInt(args[0]);
            client.getPlayer().setColor(colorIndex);

        } catch (NumberFormatException ignored){}

    }

}
