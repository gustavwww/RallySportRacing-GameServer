package controller.commandhandlers;

import controller.ClientController;
import model.Player;
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

        if (client.getPlayer() == null || client.getGame() == null) {
            return;
        }

        Player p = client.getPlayer();

        try {

            int colorIndex = Integer.parseInt(args[0]);
            p.setColor(colorIndex);
            client.getGame().sendPlayerUpdate(p);

        } catch (NumberFormatException ignored){}

    }

}
