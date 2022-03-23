package services.protocol;

import model.Game;
import model.Player;

import java.util.List;

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
        // game:player,name,x,y,z,player,name,x,y,z....
        StringBuilder sb = new StringBuilder();
        sb.append("game:");
        for (Player p : game.getPlayers()) {
            sb.append("player,").append(p.getName()).append(",")
                    .append(p.getPosition().x).append(",")
                    .append(p.getPosition().y).append(",")
                    .append(p.getPosition().z).append(",");
        }
        sb.setLength(sb.length()-1);

        return sb.toString();
    }

    public String writeError(String msg) {
        return "error:" + msg;
    }

}
