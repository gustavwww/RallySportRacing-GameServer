package services.protocol;

import model.Game;

public class ServerProtocol {

    private static ServerProtocol instance = null;

    public static ServerProtocol getInstance() {
        if (instance == null) {
            instance = new ServerProtocol();
        }
        return instance;
    }

    private final ProtocolParser parser;

    private ServerProtocol() {
        parser = new ProtocolParser();
    }

    public Command parseMessage(String msg) {
        return parser.parseMessage(msg);
    }

    public String parseCommand(Command cmd) {
        return parser.parseCommand(cmd);
    }

    public String parseGame(Game game) {
        //TODO
        // game:player,name,x,y,z,player,name,x,y,z....
        return "";
    }

}
