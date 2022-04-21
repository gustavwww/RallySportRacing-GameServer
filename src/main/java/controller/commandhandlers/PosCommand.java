package controller.commandhandlers;

import controller.ClientController;
import data.Tuple;
import data.Vector3;
import data.Vector4;
import services.protocol.Command;
import services.protocol.ServerProtocol;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

            if (args.length > 7 && args.length % 7 == 0) {

                List<Tuple<Vector3<Float>, Vector4<Float>>> objects = new ArrayList<>();

                for (int i = 7; i < args.length; i += 7) {

                    posX = Float.parseFloat(args[i]);
                    posY = Float.parseFloat(args[i + 1]);
                    posZ = Float.parseFloat(args[i + 2]);

                    quX = Float.parseFloat(args[i + 3]);
                    quY = Float.parseFloat(args[i + 4]);
                    quZ = Float.parseFloat(args[i + 5]);
                    quW = Float.parseFloat(args[i + 6]);

                    objects.add(new Tuple<>(new Vector3<>(posX, posY, posZ), new Vector4<>(quX, quY, quZ, quW)));
                    client.getPlayer().setObjects(objects);
                }

            }

        } catch (NumberFormatException ignored) {}

    }


}
