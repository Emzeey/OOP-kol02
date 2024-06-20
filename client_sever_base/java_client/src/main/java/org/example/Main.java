package org.example;
import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        new Thread(client).start();
        while(true){
            client.send(reader.readLine());
        }

    }
}