package controller.commandhandlers;

import controller.ClientController;
import services.protocol.Command;
import services.protocol.ServerProtocol;

public class SetTimeCommand extends AbstractCommandHandler {

    public SetTimeCommand() {
        super("settime");
    }

    @Override
    protected void concreteHandle(Command cmd, ClientController client, ServerProtocol protocol) {
        String[] args = cmd.getArgs();
        if (args.length < 1) return;

        try {

            float time = Float.parseFloat(args[0]);
            client.updatePlayerTime(time);

        } catch (NumberFormatException ignored) {}

    }
}
