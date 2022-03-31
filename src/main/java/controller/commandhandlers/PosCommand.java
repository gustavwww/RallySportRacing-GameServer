package controller.commandhandlers;

import controller.ClientController;
import data.Vector3;
import data.Vector4;
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
        if (args.length < 7) {
            return;
        }

        try {
            float posX = Float.parseFloat(args[0]);
            float posY = Float.parseFloat(args[1]);
            float posZ = Float.parseFloat(args[2]);

            float quX = Float.parseFloat(args[3]);
            float quY = Float.parseFloat(args[4]);
            float quZ = Float.parseFloat(args[5]);
            float quW = Float.parseFloat(args[6]);

            client.getPlayer().setPosition(new Vector3<>(posX,posY,posZ));
            client.getPlayer().setQuaternion(new Vector4<>(quX, quY, quZ, quW));
        } catch (NumberFormatException ignored) {}

    }


}
