package edu.hw8.task1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private final Socket clientSocket;
    private final ObjectInputStream in;
    private final ObjectOutputStream out;

    public Client(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());
    }

    public String sendMessage(String msg) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        out.writeBytes(msg);
        while (in.available() > 0) {
            stringBuilder.append(in.readAllBytes());
        }
        return stringBuilder.toString();
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}
