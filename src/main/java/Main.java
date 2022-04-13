import controller.ServerController;

public class Main {

    public static void main(String[] args) {

        System.out.println("Initializing server...");
        ServerController.getInstance().begin();
    }


}
