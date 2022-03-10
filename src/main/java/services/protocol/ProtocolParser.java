package services.protocol;

import java.util.ArrayList;
import java.util.List;

class ProtocolParser {

    Command parseMessage(String msg) {
        String trimmed = msg.trim();
        StringBuilder sb = new StringBuilder();

        String command = null;
        List<String> argsList = new ArrayList<>();

        for (char c : trimmed.toCharArray()) {
            if (c == ' ') {
                continue;
            }

            if (c == ':' && command == null) {
                command = sb.toString();
                sb.setLength(0);
                continue;
            }

            if (c == ',') {
                argsList.add(sb.toString());
                sb.setLength(0);
                continue;
            }

            sb.append(c);
        }

        // If command missing arguments and ":", add the remaining characters as command,
        // else add the remaining characters as an argument.
        if (command == null) {
            command = sb.toString();
        } else {
            argsList.add(sb.toString());
        }

        String[] args = new String[argsList.size()];
        for (int i = 0; i < argsList.size(); i++) {
            args[i] = argsList.get(i);
        }

        return new Command(command, args);
    }


    String parseCommand(Command cmd) {
        StringBuilder sb = new StringBuilder();
        sb.append(cmd.getCommand()).append(":");
        for (String arg : cmd.getArgs()) {
            sb.append(arg).append(",");
        }

        sb.setLength(sb.length()-1);

        return sb.toString();
    }


}
