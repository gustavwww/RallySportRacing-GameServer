package data;

import java.net.InetAddress;

public class Address {

    private final InetAddress address;
    private final int port;

    public Address(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

}
