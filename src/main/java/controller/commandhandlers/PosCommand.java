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
        if (args.length < 6) {
            return;
        }

        try {
            float posX = Float.parseFloat(args[0]);
            float posY = Float.parseFloat(args[1]);
            float posZ = Float.parseFloat(args[2]);

            float orX = Float.parseFloat(args[3]);
            float orY = Float.parseFloat(args[4]);
            float orZ = Float.parseFloat(args[5]);

            client.getPlayer().setPosition(new Vector3<>(posX,posY,posZ));
            client.getPlayer().setOrientation(new Vector3<>(orX, orY, orZ));
        } catch (NumberFormatException ignored) {}

    }


}
