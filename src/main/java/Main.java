import controller.ServerController;

public class Main {

    public static final int PORT = 2005;

    public static void main(String[] args) {

        System.out.println("Initializing server...");
        new ServerController(PORT);
    }


}
