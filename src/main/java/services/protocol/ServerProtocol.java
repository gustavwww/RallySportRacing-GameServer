package services.protocol;

import data.Tuple;
import data.Vector3;
import data.Vector4;
import model.Game;
import model.Player;

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
            sb.append("player,").append(p.getId()).append(",")
                    .append(p.getName()).append(",")
                    .append(p.getPosition().x).append(",")
                    .append(p.getPosition().y).append(",")
                    .append(p.getPosition().z).append(",")
                    .append(p.getQuaternion().x).append(",")
                    .append(p.getQuaternion().y).append(",")
                    .append(p.getQuaternion().z).append(",")
                    .append(p.getQuaternion().w).append(",")
                    .append(p.getSoundString()).append(",");

            for (Tuple<Vector3<Float>, Vector4<Float>> o : p.getObjects()) {

                sb.append(o.getFirst().x).append(",")
                        .append(o.getFirst().y).append(",")
                        .append(o.getFirst().z).append(",")
                        .append(o.getSecond().x).append(",")
                        .append(o.getSecond().y).append(",")
                        .append(o.getSecond().z).append(",")
                        .append(o.getSecond().w).append(",");

            }

        }
        sb.setLength(sb.length()-1);

        return sb.toString();
    }

    public String writeError(String msg) {
        return "error:" + msg;
    }

    public String writeSuccess(String key) {
        return "success:" + key;
    }

}
