package services;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UDPServer implements Runnable {

    private final DatagramSocket socket;

    private final Map<Integer, PacketListener> listeners = Collections.synchronizedMap(new HashMap<>());

    public void addListener(int clientID, PacketListener listener) {
        listeners.put(clientID, listener);
    }

    public void removeListener(int clientID) {
        listeners.remove(clientID);
    }

    public UDPServer(int port) throws SocketException {
        this.socket = new DatagramSocket(port);
    }

    @Override
    public void run() {
        try {

            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                String message = new String(request.getData(), 0, request.getLength());
                sendToListener(request.getAddress(), request.getPort(), message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sendToListener(InetAddress address, int port, String message) {

        int index = message.indexOf('-');
        if (index == -1) return;
        int clientID = Integer.parseInt(message.substring(0, index));
        String cmd = message.substring(index + 1);

        synchronized (listeners) {
            if (listeners.containsKey(clientID)) {
                listeners.get(clientID).gotPacket(address, port, cmd);
            }
        }
    }

    public void sendPacket(InetAddress address, int port, String message) throws IOException {
        String msgLine = message + "\n";
        byte[] bytes = msgLine.getBytes();
        DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length, address, port);
        socket.send(packet);
    }


}
