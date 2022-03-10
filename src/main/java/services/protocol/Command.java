package services.protocol;

public class Command {

    private final String command;
    private final String[] args;

    public Command(String command, String... args) {
        this.command = command;
        this.args = args;
    }

    public String getCommand() {
        return command;
    }

    public String[] getArgs() {
        return args;
    }

}
