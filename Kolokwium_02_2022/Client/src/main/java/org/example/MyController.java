package org.example;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;
import java.util.*;

public class MyController {
    public ListView<String> listView;
    @FXML
    private TextField filterField;
    @FXML
    private Label wordCountLabel;
    private List<String> messageList = new ArrayList<>();
    private Client client;


    public void receive(String message) {
        message = parseMessage(message);
        putIntoList(message);
        Platform.runLater(() -> listView.getItems().clear());
        String filter = filterField.getText().strip();

        for(String text : messageList) {
            if(filteredOut(text, filter) && !filter.isEmpty()) continue;
            Platform.runLater(() -> listView.getItems().add(text));
        }

        Platform.runLater(() -> wordCountLabel.setText(String.valueOf(messageList.size())));
    }

    public boolean filteredOut(String text, String filter) {
        for(int i=0; i<filter.length(); i++) {
            if(filter.strip().charAt(i) != text.split(" ")[1].charAt(i)) return true;
        }
        return false;
    }

    public String parseMessage(String message) {
        String prefix;
        LocalDateTime now = LocalDateTime.now();
        String hour = now.getHour() <= 9 ? String.format("0%d", now.getHour()) : Integer.toString(now.getHour());
        String minute = now.getMinute() <= 9 ? String.format("0%d", now.getMinute()) : Integer.toString(now.getMinute());
        String second = now.getSecond() <= 9 ? String.format("0%d", now.getSecond()) : Integer.toString(now.getSecond());
        prefix = String.format("%s:%s:%s ", hour, minute, second);
        return prefix + message;
    }

    public void putIntoList(String message) {
        String split = message.split(" ")[1];
        int i = 0;
        for(String text : messageList) {
            if(split.compareTo(text.split(" ")[1]) < 0) break;
            i++;
        }
        messageList.add(i, message);
    }

    public void bindClient(Client client) {
        this.client = client;
        client.setController(this);
    }

}
