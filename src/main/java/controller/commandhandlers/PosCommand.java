package controller.commandhandlers;

import controller.ClientController;
import data.Vector3;
import services.protocol.Command;
import services.protocol.ServerProtocol;

public class PosCommand extends AbstractCommandHandler {

    public PosCommand() {
        super("pos");
    }

    @Override
    protected void concreteHandle(Command cmd, ClientController client, ServerProtocol protocol) {
        if (client.getPlayer() == null) {
            return;
        }

        String[] args = cmd.getArgs();
        if (args.length < 3) {
            return;
        }

        try {
            float x = Float.parseFloat(args[0]);
            float y = Float.parseFloat(args[1]);
            float z = Float.parseFloat(args[2]);

            client.getPlayer().setPosition(new Vector3<>(x,y,z));

        } catch (NumberFormatException ignored) {}

    }


}
