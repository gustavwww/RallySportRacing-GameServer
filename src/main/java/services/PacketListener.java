package services;

import java.net.InetAddress;

public interface PacketListener {

    void gotPacket(InetAddress address, int port, String message);

}
