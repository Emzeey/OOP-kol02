package org.example;

import java.io.*;
import java.net.Socket;

public class Client implements Runnable{
    Socket socket;
    private MyController controller;
    private BufferedReader reader;

    public Client(int port) throws IOException {
        this.socket = new Socket("localhost", port);
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void setController(MyController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        String message;
        while (true) {
            try {
                if((message = reader.readLine()) == null) break;
                System.out.println(message);
                controller.receive(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
