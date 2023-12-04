package edu.hw8.task1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final int threadsCount = 5;
    private final ExecutorService executorService = Executors.newFixedThreadPool(threadsCount);
    private final QuotesDB quotesDB = new QuotesDB();
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public void start(int port) throws IOException {
        quotesDB.loadBase();
        serverSocket = new ServerSocket(port);
//        Callable<String> callable = (String word) -> quotesDB.getQuote(word);
        while (true) {
            clientSocket = serverSocket.accept();
            in = new ObjectInputStream(clientSocket.getInputStream());
            out = new ObjectOutputStream(clientSocket.getOutputStream());

//            Future<String> future = executorService.submit(callable("123"));

        }
    }
}
