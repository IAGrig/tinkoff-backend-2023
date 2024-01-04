package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final int threadsCount;
    private final ExecutorService executorService;
    private final QuotesDB quotesDB;
    private ServerSocket serverSocket;

    public Server(int maxConnections) {
        this.threadsCount = maxConnections;
        this.executorService = Executors.newFixedThreadPool(threadsCount);
        this.quotesDB = new QuotesDB();
    }

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            executorService.execute(() -> handleClient(clientSocket));
        }
    }

    public void stop() throws IOException {
        executorService.shutdown();
        serverSocket.close();
    }

    private void handleClient(Socket clientSocket) {
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
        ) {
            String query = reader.readLine();
            String answer = quotesDB.getQuote(query);
            writer.write(answer);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
