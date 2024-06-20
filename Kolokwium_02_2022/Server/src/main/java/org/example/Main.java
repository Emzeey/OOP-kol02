package org.example;

public class Main {
    public static void main(String[] args) {
        WordBag wordBag = new WordBag();
        wordBag.populate();
        Server server = new Server(2137, wordBag);
        server.start();
        server.startSending();
    }
}